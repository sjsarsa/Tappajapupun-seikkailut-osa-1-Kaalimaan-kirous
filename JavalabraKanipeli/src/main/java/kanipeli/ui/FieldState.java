/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import kanipeli.domain.Creature;
import kanipeli.domain.CreatureOnField;
import kanipeli.peli.Battle;
import kanipeli.peli.Field;
import kanipeli.peli.Game;
import kanipeli.ui.level.Level;

/**
 *
 * @author Sami
 */
public class FieldState implements GameState {

    private Canvas canvas;
    private static int HEIGHT = 256, WIDTH = 256, scale = 3;
    private static BufferedImage image;
    private static int[] pixels;
    private Screen screen;
    private Game game;
    private Field field;
    private int xScroll = 0, yScroll = 0;
    private GameStateManager gsm;

    public FieldState(Canvas canvas, Screen screen, Game game, GameStateManager gsm, BufferedImage image) {
        this.canvas = canvas;
        this.screen = screen;
        this.game = game;
        this.field = game.getCurrentField();
        this.gsm = gsm;
        this.image = image;
        this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    }

    public void run() {
        render();
        checkRandomEncounter();
        checkSpot();
    }

    public void render() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3);
            canvas.requestFocus();
            return;
        }
        field.level.renderField(xScroll, yScroll, screen);
        for (int y = 0; y < screen.h; y++) {
            for (int x = 0; x < screen.w; x++) {
                pixels[x + y * WIDTH] = screen.pixels[x + y * screen.w];
            }
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        g.dispose();
        bs.show();
    }

    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            field.getPlayer().moveUp();
        }
        if (keyCode == KeyEvent.VK_S) {
            field.getPlayer().moveDown(field.getHeight() - 1);
        }
        if (keyCode == KeyEvent.VK_A) {
            field.getPlayer().moveLeft();
        }
        if (keyCode == KeyEvent.VK_D) {
            field.getPlayer().moveRight(field.getWidth() - 1);
        }
        if (keyCode == KeyEvent.VK_UP) {
            field.getPlayer().moveUp();
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            field.getPlayer().moveDown(field.getHeight() - 1);
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            field.getPlayer().moveLeft();
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            field.getPlayer().moveRight(field.getWidth() - 1);
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            MenuState ms = new MenuState(canvas, screen, gsm);
            gsm.setState(0);
        }
    }

    public void checkSpot() {
        CreatureOnField cof = field.checkSpot();
        if (cof != null) {
            fight(cof);
        }
    }

    public void checkRandomEncounter() {
        if (field.getPlayer().moved && field.randomEncounter()) {
            Creature foe = field.createRandomEncounter();
            fight(foe);
        }
        field.getPlayer().moved = false;
    }

    public void fight(Creature foe) {
        Battle battle = new Battle(field.getPlayer(), foe);
        BattleState bs = new BattleState(canvas, screen, battle, gsm, image);
        gsm.addState(2, bs);
        gsm.setState(2);
    }
}
