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
import kanipeli.peli.Game;
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
     *
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
            canvas.createBufferStrategy(3);
            canvas.requestFocus();
            return;
        }
        int w = (screen.w * 15) >> 4;
        int h = (screen.h * 15) >> 4;

        for (int y = 0; y <= h; y++) {
            for (int x = 0; x <= w; x++) {
                Tile.MenuBlack.render(x, y, screen); //empty battle tile (white)
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
//        g.fillRect(0, 0, height * scale * 2, width * scale * 2);
        if (game != null) drawStatus(g);
        drawOptions(g);
        g.dispose();
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
            g.drawString(options[i], 25 * scale, 50 * scale + i * 40);
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
        g.drawString("Exp:" + pc.getExp() + "/" + pc.getRequiredExp(), w * scale, 50 * scale + 5 * 60);       
    }

    /**
     *
     * @param keyCode
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
            render();
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
            render();
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gsm.setState(gsm.getPreviousState());
        }
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
