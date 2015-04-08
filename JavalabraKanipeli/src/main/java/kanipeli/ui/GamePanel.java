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
import javax.swing.JFrame;

/**
 *
 * @author Sami
 */
public class GamePanel  implements KeyListener{
    
    private GameStateManager gsm;
    
    public GamePanel(int width, int height, int scale, Canvas canvas, GameStateManager gsm) {     
        this.gsm = gsm;
        canvas.setPreferredSize(new Dimension(width * scale, height * scale));
//        canvas.setMinimumSize(new Dimension(WIDTH * scale, HEIGHT * scale));
//        canvas.setMaximumSize(new Dimension(WIDTH * scale, HEIGHT * scale));
        JFrame frame = new JFrame();
        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        
        canvas.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        gsm.keyPressed(ke.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
}
