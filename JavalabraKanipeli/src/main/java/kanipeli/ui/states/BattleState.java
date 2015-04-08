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
import kanipeli.AudioPlayer;
import kanipeli.domain.Creature;
import kanipeli.peli.Battle;
import kanipeli.ui.Screen;
import kanipeli.ui.level.Tile;

/**
 *
 * @author Sami
 */
public class BattleState implements GameState {

    public Canvas canvas;
    private static int height, width, scale = 3;
    private BufferStrategy bs;
    private static int HEIGHT = 256, WIDTH = 256, scaler = 3;
    private static BufferedImage image;
    private static int[] pixels;
    private Screen screen;
    private Battle battle;
    private Graphics g;
    private GameStateManager gsm;
    private AudioPlayer battleMusic;
    private AudioPlayer fieldMusic;
    private AudioPlayer gameOverMusic = new AudioPlayer("/audio/gameOver.wav");
    private boolean actionSelected;

    private String[] options = new String[]{
        "Attack",
        "Use item",
        "Flee"
    };
    private String[] items;
    private int currentChoice = 0;
    private int currentItem = 0;

    public BattleState(Canvas canvas, Screen screen, Battle battle, GameStateManager gsm, BufferedImage image) {
        this.canvas = canvas;
        this.height = HEIGHT;
        this.width = WIDTH;
        this.image = image;
        this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        this.screen = screen;
        this.battle = battle;
        this.items = items;
        this.bs = canvas.getBufferStrategy();
        this.gsm = gsm;
    }

    public void run() {
        if (battle.getPlayer().getCurrentHp() == 0) return;
        if (battle.escaped && !battle.lost) {
            gsm.setState(1);
            gsm.music.stop();
            gsm.music = new AudioPlayer("/audio/field.wav");
            gsm.music.play();
        }
        render();
        drawOptions();
        bs.show();
        if (actionSelected) {
            if (playerTurn()) {
                battle.checkLevelUp();
                gsm.music.stop();
                gsm.music = new AudioPlayer("/audio/field.wav");
                gsm.music.play();             
                gsm.setState(1);
            }
            else if (enemyTurn()) {
                gsm.music.stop();
                gsm.music = new AudioPlayer("/audio/gameOver.wav");
                gsm.music.play();
                for (int i = 0; i < 2; i++) {
                    battleLost();
                }
            }
        }
    }

    public boolean playerTurn() {
        selectAction();
        actionSelected = false;
        if (!battle.alive(battle.getFoe())) {
            return true;
        }
        return false;
    }

    public boolean enemyTurn() {
        int damage = battle.attack(battle.getFoe(), battle.getPlayer()); //enemy turn
//        for (int i = 0; i < 30; i++) {
            try {
            drawDamage(damage, 2);
            drawDamage(damage, 2);
            Thread.sleep(700);
            } catch (InterruptedException e) {
                System.out.println("lol");
            }
//        }
        if (!battle.alive(battle.getPlayer())) {
            battle.lost = true;
            return true;
        }
        return false;
    }

    public void selectAction() {
        if (currentChoice == 0) {
            int damage = battle.attack(battle.getPlayer(), battle.getFoe());
            try {
                drawDamage(damage, 0);
                drawDamage(damage, 0);
                Thread.sleep(700);
            } catch (InterruptedException e) {
                System.out.println("lol");
            }
        }
        if (currentChoice == 1) ;
        if (currentChoice == 2) {
            battle.escaped = true;
        }
    }

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
            if (battle.lost) {
                gsm.music.stop();
                gsm.music = new AudioPlayer("/audio/menu.wav");
                gsm.music.play();
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
