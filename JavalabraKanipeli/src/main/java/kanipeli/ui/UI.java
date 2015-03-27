/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;
import javax.swing.JFrame;
import kanipeli.domain.Creature;
import kanipeli.peli.Battle;
import kanipeli.peli.Field;
import kanipeli.ui.level.Level;

/**
 *
 * @author Sami
 */
public class UI {
    public Canvas canvas = new Canvas();
    private static int HEIGHT = 256, WIDTH = 256, scale = 3;
    private static BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private static int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    public boolean running;
    private Screen fieldScreen;
    public Field field;
    public Level level;
    public int xScroll = 0, yScroll = 0;
    public String[] options = new String[] {
            "Attack",
            "Use item",
            "Flee"
        };
    public String[] items;
    public int currentChoice = 0;
    public int currentItem = 0;
    
    public UI(Field field) {  
        this.field = field;
        fieldScreen = new Screen(WIDTH, HEIGHT);
        level = field.level;  
        
        canvas.setPreferredSize(new Dimension(WIDTH * scale, HEIGHT * scale));
//        canvas.setMinimumSize(new Dimension(WIDTH * scale, HEIGHT * scale));
//        canvas.setMaximumSize(new Dimension(WIDTH * scale, HEIGHT * scale));
        
        JFrame frame = new JFrame();
        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
 
    public void renderField() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {  
            canvas.createBufferStrategy(3);
            canvas.requestFocus();
            return;
        }
        level.renderField(xScroll, yScroll, fieldScreen);
        for (int y = 0; y < fieldScreen.h; y++) {
            for (int x = 0; x < fieldScreen.w; x++) {
                pixels[x + y * WIDTH] = fieldScreen.pixels[x + y * fieldScreen.w];
            }
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        g.dispose();
        bs.show();
    }
    
    public void renderBattle(Battle battle) {
        
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {  
            canvas.createBufferStrategy(3);
            canvas.requestFocus();
            return;
        }
        level.renderBattle(xScroll, yScroll, fieldScreen);
        for (int y = 0; y < fieldScreen.h; y++) {
            for (int x = 0; x < fieldScreen.w; x++) {
                pixels[x + y * WIDTH] = fieldScreen.pixels[x + y * fieldScreen.w];
            }
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        
        showCreatureStatus(15 * scale, g, battle.getPlayer());
        showCreatureStatus(160 * scale, g, battle.getFoe());
        
        
        for(int i = 0; i < options.length; i++) {
            if(i == currentChoice) {
		g.setColor(Color.BLUE);
            }
            else {
		g.setColor(Color.RED);
            }
            g.drawString(options[i], 45 * scale, 50 * scale + i * 25);
	}
        
        g.dispose();
        bs.show();
    }
    
    public void renderBattleEvent(Battle battle, int amount, int color) {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {  
            canvas.createBufferStrategy(3);
            canvas.requestFocus();
            return;
        }
        level.renderBattle(xScroll, yScroll, fieldScreen);
        for (int y = 0; y < fieldScreen.h; y++) {
            for (int x = 0; x < fieldScreen.w; x++) {
                pixels[x + y * WIDTH] = fieldScreen.pixels[x + y * fieldScreen.w];
            }
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        
        showCreatureStatus(15 * scale, g, battle.getPlayer());
        showCreatureStatus(160 * scale, g, battle.getFoe());
        
        g.setFont(new Font("Arial", Font.BOLD, 65));
        if (color == 0) g.setColor(Color.YELLOW);
        else if (color == 1) g.setColor(Color.GREEN);
        else g.setColor(Color.RED);
        g.drawString(amount + "!", 110 * scale, 80 * scale);
        
        g.dispose();
        bs.show();
    } 
    
    public void renderGameOver() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {  
            canvas.createBufferStrategy(3);
            canvas.requestFocus();
            return;
        }
        level.renderBattle(xScroll, yScroll, fieldScreen);
        for (int y = 0; y < fieldScreen.h; y++) {
            for (int x = 0; x < fieldScreen.w; x++) {
                pixels[x + y * WIDTH] = fieldScreen.pixels[x + y * fieldScreen.w];
            }
        }
       
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        g.setColor(Color.RED);
        g.setFont(new Font("Century Gothic", Font.BOLD, 140));
        g.drawString("YOU", 80 * scale, 90 * scale);
        g.drawString("DIED", 80 * scale, 170 * scale);
        g.drawLine(80 * scale, 80 * scale, 0 * scale, 160 * scale);
        g.drawLine(0 * scale, 80 * scale, 80 * scale, 160 * scale);
        
        
        
        g.dispose();
        bs.show();
    }
    
    public void showCreatureStatus(int width, Graphics g, Creature cr) {
        g.setFont(new Font("Century Gothic", Font.BOLD, 28));
        g.drawString(cr.getName(), width, 25 * scale);
        String status = "Hp: " + cr.getCurrentHp() + " / " + cr.getMaxHp();
        g.drawString(status, width, 35 * scale);
    }
}
