/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.states;

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
import kanipeli.ui.Screen;

/**
 * Renders the field screen on canvas using Level.
 * @see Level
 * Calls for GameStateManager to change music and GameState.
 * @see GameStateManager
 * Creates battle states and battles.
 * @see Battle
 * @see BattleState
 * Calls for field logic when needed (e.g. certain buttons are pressed).
 * @see Field
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

    /**
     *The constructor
     * @param canvas the canvas upon which the pixels are rendered.
     * @param screen knows the pixels for the graphics.
     * @param game has information of all the creatures and fields.
     * @param gsm it manages the game states.
     * @param image a Buffered image used to draw the graphics.
     */
    public FieldState(Canvas canvas, Screen screen, Game game, GameStateManager gsm, BufferedImage image) {
        this.canvas = canvas;
        this.screen = screen;
        this.game = game;
        this.gsm = gsm;
        this.image = image;
        this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        this.field = game.getCurrentField();
    }

    /**
     *
     * @param game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     *Renders the field on the canvas.
     * Checks for movement of the player.
     * If the player has moved, changes the game's current field as this field
     * and checks whether a random encounter has occurred. 
     * In such a case starts a fight with the encountered fiend.
     * Checks if the player has stumbled upon a creature on a field i.e. a boss.
     */
    public void run() {
        render();
        if (field.getPlayer().moved) { 
            checkRandomEncounter();
            checkSpot();
            this.field = game.getCurrentField();
            field.getPlayer().moved = false;
        }
    }

    private void render() {
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

    /**
     *Moves the player on the field according to correct button presses.
     * @param keyCode the code of a key that was pressed
     */
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
            gsm.setState(0);
        }
    }

    private void checkSpot() {
        CreatureOnField cof = field.checkSpot();
        if (cof != null) {
            gsm.setMusic(gsm.getBossBattleMusic());
            fight(cof);
        }
        field.checkEdge();
    }

    private void checkRandomEncounter() {
        if (field.randomEncounter()) {
            Creature foe = field.createRandomEncounter();
            gsm.setMusic(gsm.getBattleMusic());
            fight(foe);
        }
    }

    private void fight(Creature foe) {
        Battle battle = new Battle(field.getPlayer(), foe);
        BattleState bs = new BattleState(canvas, screen, battle, gsm, image);
        gsm.addState(2, bs);
        gsm.setState(2);
    }
}
