/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import kanipeli.peli.Game;

/**
 *
 * @author Sami
 */
public class MenuState implements GameState, Runnable {

    private static int HEIGHT = 256, WIDTH = 256, scale = 3;
    private static BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private static int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private Canvas canvas;
    private Screen screen;
    private GameStateManager gsm;
    private String[] options = new String[]{
        "New Game",
        "Continue",
        "Status",
        "Exit"
    };
    private int currentChoice = 0;
    private boolean exit = false;
    private boolean actionSelected = false;

    public MenuState(Canvas canvas, Screen screen, GameStateManager gsm) {
        this.canvas = canvas;
        this.screen = screen;
        this.gsm = gsm;
    }

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
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        g.setColor(Color.BLACK);
        g.fillRect(30 * scale, 40 * scale, 40 * options.length, 120);
        
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.RED);
            }
            g.drawString(options[i], 45 * scale, 50 * scale + i * 25);
        }
        g.dispose();
        bs.show();
    }

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
        if (keyCode == KeyEvent.VK_ESCAPE) ;
    }

    private void actionSelected() {
        if (currentChoice == 0) {
            newGame();
        }
        if (currentChoice == 1) {
            if (gsm.getPreviousState() != 0) gsm.setState(gsm.getPreviousState());
        }
        if (currentChoice == 3) {
            exit = true;
        }
    }

    private void newGame() {
        Game game = new Game();
        FieldState fs = new FieldState(canvas, screen, game, gsm, image);
        gsm.addState(1, fs);
        gsm.setState(1);
    }
}
