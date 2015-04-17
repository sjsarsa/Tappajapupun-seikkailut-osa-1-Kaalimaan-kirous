/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui;

import kanipeli.ui.states.GameStateManager;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *The panel for the game, displaying graphics and such.
 * Also listens to keys being pressed.
 * @author Sami
 */
public class GamePanel  implements KeyListener{
    
    private GameStateManager gsm;

    /**
     *Creates a frame for the game and also works as a keyListener.
     */
    public JFrame frame;
    
    /**
     *
     * @param width
     * @param height
     * @param scale
     * @param canvas
     * @param gsm
     */
    public GamePanel(int width, int height, int scale, Canvas canvas, GameStateManager gsm) {     
        this.gsm = gsm;
        canvas.setPreferredSize(new Dimension(width * scale, height * scale));
        frame = new JFrame();
        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        
        canvas.addKeyListener(this);
    }

    /**
     * : P
     * @param ke
     */
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    /**
     *Sends keyCode of pressed key to GameStateManager
     * @see GameStateManager
     * @param ke
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        gsm.keyPressed(ke.getKeyCode());
    }

    /**
     * Doesn't do a thing.
     * @param ke
     */
    @Override
    public void keyReleased(KeyEvent ke) {
    }
}
