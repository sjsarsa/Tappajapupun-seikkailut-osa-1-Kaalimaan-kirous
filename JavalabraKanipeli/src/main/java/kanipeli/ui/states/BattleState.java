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
import kanipeli.domain.Creature;
import kanipeli.peli.Battle;
import kanipeli.ui.Screen;
import kanipeli.ui.level.Tile;

/**
 *Renders the battle screen and draws its events on canvas.
 * Calls for GameStateManager to change music and GameState.
 * @see GameStateManager
 * Calls for battle logic when needed (e.g. certain buttons are pressed).
 * @see Battle
 * 
 * @author Sami
 */
public class BattleState implements GameState {

    /**
     *
     */
    private Canvas canvas;
    private static int height, width, scale = 3;
    private BufferStrategy bs;
    private static BufferedImage image;
    private static int[] pixels;
    private Screen screen;
    private Battle battle;
    private Graphics g;
    private GameStateManager gsm;
    private boolean actionSelected;

    private String[] options = new String[]{
        "Attack",
        "Use item",
        "Flee"
    };
    private String[] items;
    private int currentChoice = 0;
    private int currentItem = 0;

    /**
     *
     * @param canvas
     * @param screen
     * @param battle
     * @param gsm
     * @param image
     */
    public BattleState(Canvas canvas, Screen screen, Battle battle, GameStateManager gsm, BufferedImage image) {
        this.canvas = canvas;
        this.height = screen.h;
        this.width = screen.w;
        this.image = image;
        this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        this.screen = screen;
        this.battle = battle;
        this.items = items;
        this.bs = canvas.getBufferStrategy();
        this.gsm = gsm;
    }
    
    private void checkEscaped() {
        if (battle.getEscaped() && !battle.getLost()) {
            gsm.setState(1);
            gsm.setMusic(gsm.getFieldMusic());
        }
    }
    
    private boolean playerTurn() {
        selectAction();
        actionSelected = false;
        if (!battle.alive(battle.getFoe())) {
            return true;
        }
        return false;
    }

    private boolean enemyTurn() {
        int damage = battle.attack(battle.getFoe(), battle.getPlayer()); //enemy turn
            try {
            drawDamage(damage, 2);
            drawDamage(damage, 2);
            Thread.sleep(700);
            } catch (InterruptedException e) {
                System.out.println("lol");
            }
        if (!battle.alive(battle.getPlayer())) {
            return true;
        }
        return false;
    }
    
    private void fight() {
        if (actionSelected) {
            if (playerTurn()) {
                battle.checkLevelUp();
                gsm.setMusic(gsm.getFieldMusic());
                gsm.setState(1);
            }
            else if (enemyTurn()) {
                gsm.setMusic(gsm.getGameOverMusic());
                for (int i = 0; i < 2; i++) {
                    battleLost();
                }
            }
        }
    }

    /**
     *Does a whole lotta stuff (divided in private methods ofc).
     * Checks if player is dead and if that is the case it doesn't do squat.
     * Checks if the player escaped from the battle.
     * Renders the battle screen on the canvas.
     * Receives input from keyboard and calls for battle logic accordingly.
     * Changes the music and game state when battle is over.
     */
    
    public void run() {
        if (battle.getPlayer().getCurrentHp() == 0) return;
        checkEscaped();
        render();
        drawOptions();
        bs.show();
        fight();
    }

    private void selectAction() {
        if (currentChoice == 0) {
            int damage = battle.attack(battle.getPlayer(), battle.getFoe());
            try {
                drawDamage(damage, 0);
                drawDamage(damage, 0);
                Thread.sleep(700);
            } catch (InterruptedException e) {
                System.out.println("lollol");
            }
        }
        if (currentChoice == 1) ;
        if (currentChoice == 2) {
            battle.setEscaped(true);
        }
    }

    /**
     *
     * @param keyCode
     */
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
            if (battle.getLost()) {
                gsm.setMusic(gsm.getMenuMusic());
                gsm.setState(0);
            }
            gsm.setState(0);
        }
    }

    private void render() {

        if (bs == null) {
            canvas.createBufferStrategy(3);
            canvas.requestFocus();
            return;
        }
        renderBattle(screen);
        for (int y = 0; y < screen.h; y++) {
            for (int x = 0; x < screen.w; x++) {
                pixels[x + y * width] = screen.pixels[x + y * screen.w];
            }
        }
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);

        showCreatureStatus(15 * scale, g, battle.getPlayer());
        showCreatureStatus(160 * scale, g, battle.getFoe());
    }

    private void drawOptions() {
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.RED);
            }
            g.drawString(options[i], 45 * scale, 50 * scale + i * 25);
        }
    }

    private void drawDamage(int amount, int color) {
        render();
        g.setFont(new Font("Arial", Font.BOLD, 65));
        if (color == 0) {
            g.setColor(Color.YELLOW);
        } else if (color == 1) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.RED);
        }
        g.drawString(amount + "!", 110 * scale, 80 * scale);
        bs.show();
    }

    private void showCreatureStatus(int width, Graphics g, Creature cr) {
        g.setFont(new Font("Century Gothic", Font.BOLD, 28));
        g.drawString(cr.getName(), width, 25 * scale);
        String status = "Hp: " + cr.getCurrentHp() + " / " + cr.getMaxHp();
        g.drawString(status, width, 35 * scale);
    }

    private void renderBattle(Screen screen) {

        int w = (screen.w * 15) >> 4;
        int h = (screen.h * 15) >> 4;

        for (int y = 0; y <= h; y++) {
            for (int x = 0; x <= w; x++) {
                Tile.tiles[6].render(x, y, screen); //empty battle tile (white)
            }
        }
        Tile.tiles[battle.getPlayer().getBattleTile()].render(0, 1, screen); //playerBattle
        Tile.tiles[battle.getFoe().getBattleTile()].render(2, 1, screen); //critterBattle     

    }

    private void battleLost() {
        render();
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        g.setColor(Color.RED);
        g.setFont(new Font("Century Gothic", Font.BOLD, 140));
        g.drawString("YOU", 80 * scale, 90 * scale);
        g.drawString("DIED", 80 * scale, 170 * scale);
        g.drawLine(80 * scale, 80 * scale, 0 * scale, 160 * scale);
        g.drawLine(0 * scale, 80 * scale, 80 * scale, 160 * scale);
        bs.show();
    }

}
