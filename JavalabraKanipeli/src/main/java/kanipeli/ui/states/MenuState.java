/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.states;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import kanipeli.domain.PlayableCreature;
import kanipeli.logic.Game;
import kanipeli.ui.Screen;
import kanipeli.ui.level.Tile;

/**
 *Renders the menu, can create a new game, exit the game or continue from
 * previous state. No you cannot save the game, but you can continue a
 * game after you pause it by setting the state to this menu state.
 * @author Sami
 */
public class MenuState implements GameState, Runnable {

    private static int height = 256, width = 256, scale = 3;
    private static BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private static int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private Canvas canvas;
    private Screen screen;
    private GameStateManager gsm;
    private String[] options = new String[]{
        "New Game",
        "Continue",
        "Change State",
        "Exit"
    };
    private Game game;
    private int currentChoice = 0;
    private boolean actionSelected = false;

    /**
     *
     * @param canvas
     * @param screen
     * @param gsm
     */
    public MenuState(Canvas canvas, Screen screen, GameStateManager gsm) {
        this.canvas = canvas;
        this.screen = screen;
        this.gsm = gsm;
    }

    /**
     *Renders the menu screen showing options and the player status if a game
     * has been started.
     * Depending on buttons being pressed creates a new game, goes back to
     * previous game state or exits the game.
     */
    @Override
    public void run() {
        render();
        if (actionSelected) {
            actionSelected();
        }
        actionSelected = false;
    }

    private void render() {
        
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(2);
            canvas.requestFocus();
            bs = canvas.getBufferStrategy();
        }
        int w = (screen.w * 15) >> 4;
        int h = (screen.h * 15) >> 4;

        for (int y = 0; y <= h; y++) {
            for (int x = 0; x <= w; x++) {
                Tile.menuBlack.render(x, y, screen); //empty battle tile (white)
            }
        }
        Tile.playerBattle.render(1, 1, screen);
        for (int y = 0; y < screen.h; y++) {
            for (int x = 0; x < screen.w; x++) {
                pixels[x + y * width] = screen.pixels[x + y * screen.w];
            }
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        g.setColor(Color.BLACK);
        if (game != null) drawStatus(g);
        drawOptions(g);
        bs.show();
    }
    
    private void drawOptions(Graphics g) {
        g.setFont(new Font("Century Gothic", Font.BOLD, 28));
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.RED);
            }
            g.drawString(options[i], 15 * scale, 35 * scale + i * 40);
        }
    }
    
     private void drawStatus(Graphics g) {
        g.setFont(new Font("Century Gothic", Font.BOLD, 28));
        g.setColor(Color.WHITE);
        PlayableCreature pc = game.getCurrentField().getPlayer();
        int w = 180;
        g.drawString(pc.getName(), w / 2 * scale, 50 * scale + 1 * 60);
        g.drawString("Hp: " + pc.getCurrentHp() + "/" + pc.getMaxHp(), w * scale, 50 * scale + 2 * 60);
        g.drawString("Damage: " + pc.getDamage(), w * scale, 50 * scale + 3 * 60);
        g.drawString("Level: " + pc.getLvl(), w * scale, 50 * scale + 4 * 60);
        g.drawString("Exp: " + pc.getExp() + "/" + pc.getRequiredExp(), w * scale, 50 * scale + 5 * 60);  
        int state = game.getCurrentField().getPlayer().getState();
        String status = null;
        if (state == 0) status = "Normal";
        if (state == 1) status = "Frenzied";
        if (state == 2) status = "Calm";
        g.drawString("Status:" + status, w * scale, 50 * scale + 6 * 60);
    }
    
    /**
     *Changes the current selected option or selects that option which is
     * the current selected option.
     * @param keyCode the code of a key that has been pressed
     */
    @Override
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_ENTER) {
            actionSelected = true;
        }
        if (keyCode == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
            
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
           
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gsm.setState(gsm.getPreviousState());
        }
        render();
    }

    private void actionSelected() {
        if (currentChoice == 0) {
            newGame();
        }
        if (currentChoice == 1) {
            if (gsm.getPreviousState() != 0) {
                gsm.setState(gsm.getPreviousState());
            }
        }
        if (currentChoice == 2) {
            if (game != null ) game.getCurrentField().getPlayer().changeState();
            render();
        }
        if (currentChoice == 3) {
            System.exit(0);
        }
    }

    private void newGame() {
        game = new Game();
        FieldState fs = new FieldState(canvas, screen, game, gsm, image);
        gsm.addState(1, fs);
        gsm.setMusic(gsm.getFieldMusic());
        gsm.setState(1);
        
    }
}
