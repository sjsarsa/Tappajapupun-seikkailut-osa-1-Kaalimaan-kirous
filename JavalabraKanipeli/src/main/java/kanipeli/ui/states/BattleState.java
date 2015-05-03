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
import kanipeli.domain.Item;
import kanipeli.logic.Battle;
import kanipeli.ui.Screen;
import kanipeli.ui.level.Tile;

/**
 * Renders the battle screen and draws its events on canvas. Calls for
 * GameStateManager to change music and GameState.
 *
 * @see GameStateManager Calls for battle logic when needed (e.g. certain
 * buttons are pressed).
 * @see Battle
 *
 * @author Sami
 */
public class BattleState implements GameState {

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
    private boolean itemMenu;

    private String[] options = new String[]{
        "Attack",
        "Use item",
        "Flee"
    };
    private int currentChoice = 0;
    private int currentItem = 0;
    private int items = 0;

    /**
     * The constructor
     *
     * @param canvas the canvas upon which the pixels are rendered.
     * @param screen knows the pixels for the graphics.
     * @param battle battle operations and creatures and such.
     * @param gsm it manages the game states.
     * @param image a Buffered image used to draw the graphics.
     */
    public BattleState(Canvas canvas, Screen screen, Battle battle, GameStateManager gsm, BufferedImage image) {
        this.canvas = canvas;
        this.height = screen.h;
        this.width = screen.w;
        this.image = image;
        this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        this.screen = screen;
        this.battle = battle;
        this.bs = canvas.getBufferStrategy();
        this.gsm = gsm;
    }

    /**
     * Checks if player is dead and if that is the case doesn't do squat. Checks
     * if the player escaped from the battle. Renders the battle screen on the
     * canvas. Receives input from keyboard and calls for battle logic
     * accordingly. Changes the music and game state when battle is over.
     */
    public void run() {
        if (battle.getPlayer().getCurrentHp() == 0) return;
        fight();
        checkEscaped(); 
        if (gsm.getState() == 1 || battle.getLost()) return;
        if (itemMenu) {
            items = battle.getPlayer().getItems().size();
            drawItems();
        } else {
            drawOptions();
        }
    }

    private void checkEscaped() {
        if (battle.getEscaped() && !battle.getLost()) {
            gsm.setState(1);
            gsm.setMusic(gsm.getFieldMusic());
        }
    }

    private boolean playerTurn() {
        if (!battle.alive(battle.getFoe())) {
            return true;
        }
        return false;
    }

    private boolean enemyTurn() {
        int damage = battle.attack(battle.getFoe(), battle.getPlayer()); //enemy turn
        try {
            drawDamage(damage, 2);
            Thread.sleep(700);
        } catch (InterruptedException e) {
            System.out.println("lol");
        }
        if (!battle.alive(battle.getPlayer())) {
            return true;
        } else drawOptions();
        return false;
    }

    private void fight() {
        if (actionSelected && selectAction()) {
            if (playerTurn()) {
                int i = battle.victory();
                drawVictory(battle.getDroppedItem(), i);
                try {
                    Thread.sleep(1500);
                } catch (Exception e) {
                }
                gsm.setMusic(gsm.getFieldMusic());
                gsm.setState(1);
            } else if (enemyTurn()) {
                gsm.setMusic(gsm.getGameOverMusic());
                    drawGameOver();
            }
        }
    }

  

    private boolean selectAction() {
        actionSelected = false;
        if (itemMenu) {
            itemMenu = false;
            if (currentItem < items) {
                drawItemUse();
            } else {
                return false;
            }
            return true;
        }
        if (currentChoice == 0) {
            int damage = battle.attack(battle.getPlayer(), battle.getFoe());
            try {
                drawDamage(damage, 0);
                Thread.sleep(700);
            } catch (InterruptedException e) {
                System.out.println("lollol");
            }
        }
        if (currentChoice == 1) {
            actionSelected = false;
            currentItem = 0;
            itemMenu = true;
            return false;
        }
        if (currentChoice == 2) {
            battle.setEscaped(true);
        }
        return true;
    }

    /**
     *If enter has been pressed, tells the battle state that an action has been
     * selected.
     * Scrolls options.
     * @param keyCode
     */
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_ENTER) {
            actionSelected = true;
        }
        if (keyCode == KeyEvent.VK_UP) {
            if (itemMenu) {
                currentItem--;
                if (currentItem == -1) {
                    currentItem = items;
                }
                return;
            }
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            if (itemMenu) {
                currentItem++;
                if (currentItem == items + 1) {
                    currentItem = 0;
                }
                return;
            }
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
        bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(2);
            canvas.requestFocus();
            bs = canvas.getBufferStrategy();
        }
        renderBattle(screen);
        for (int y = 0; y < screen.h; y++) {
            for (int x = 0; x < screen.w; x++) {
                pixels[x + y * width] = screen.pixels[x + y * screen.w];
            }
        }
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);

        drawCreatureStatus(15 * scale, g, battle.getPlayer());
        drawCreatureStatus(160 * scale, g, battle.getFoe());
    }
    
    private void drawItemUse() {
        int dam = battle.useItem(currentItem);
        int col = 0;
        if (dam < 0) {
            dam = Math.abs(dam);
            col = 1;
        }
        drawDamage(dam, col);
        try {
            Thread.sleep(700);
        } catch (Exception e) {
        }
    }

    private void drawOptions() {
        render();
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.RED);
            }
            g.drawString(options[i], 45 * scale, 50 * scale + i * 25);
        }
        bs.show();
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

    private void drawVictory(Item item, int amount) {
        render();
        g.setFont(new Font("Century Gothic", Font.BOLD, 30));
        g.setColor(Color.orange);
        g.drawString("Voitit!", 90 * scale, 130 * scale);
        g.drawString("Sait " + battle.getFoe().getExp() + " exp, jee!", 90 * scale, 150 * scale);
        
        if (amount != 0) {
            g.drawString("Vihulainen tiputti jotain: ", 50 * scale, 200 * scale);
            g.drawString(item.getName() + " x " + amount, 50 * scale, 220 * scale);
        }
        bs.show();
    }

    private void drawItems() {
        render();
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.setColor(Color.blue);

        int i = 1;
        if (currentItem < items) {
            Item item = battle.getPlayer().getItems().get(currentItem);
            g.drawString(item.getName() + ": " + item.getQuantity(), 90 * scale, 50 + 30 * i * scale);
            i++;
            g.setColor(Color.black);
        }
        if (currentItem + 1 < items) {
            Item item = battle.getPlayer().getItems().get(currentItem + 1);
            g.drawString(item.getName() + ": " + item.getQuantity(), 90 * scale, 50 + 30 * i * scale);
            i++;
        }
        g.drawString("cancel", 90 * scale, 50 + 30 * i * scale);
        bs.show();
    }

    private void drawCreatureStatus(int width, Graphics g, Creature cr) {
        g.setFont(new Font("Century Gothic", Font.BOLD, 28));
        g.drawString(cr.getName(), width, 25 * scale);
        String status = "Hp: " + cr.getCurrentHp() + " / " + cr.getMaxHp();
        g.drawString(status, width, 35 * scale);
    }
    
    private void drawGameOver() {
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

    private void renderBattle(Screen screen) {
        int w = (screen.w * 15) >> 4;
        int h = (screen.h * 15) >> 4;

        for (int y = 0; y <= h; y++) {
            for (int x = 0; x <= w; x++) {
                Tile.battleEmpty.render(x, y, screen); //empty battle tile (white)
            }
        }
        Tile.tiles[battle.getPlayer().getBattleTile()].render(0, 1, screen); //playerBattle
        Tile.tiles[battle.getFoe().getBattleTile()].render(2, 1, screen); //critterBattle     
    }

    
}
