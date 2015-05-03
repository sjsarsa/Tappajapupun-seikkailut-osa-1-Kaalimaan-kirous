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
public class ItemMenuState implements GameState, Runnable {

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
    public ItemMenuState(Canvas canvas, Screen screen, GameStateManager gsm) {
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
}
