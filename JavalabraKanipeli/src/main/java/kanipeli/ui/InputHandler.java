/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Sami
 */
public class InputHandler implements KeyListener {
    private UI ui;
    public boolean up = false, down = false, left = false, right = false;
    public boolean battle = false, items = false;
    public boolean actionSelected;
    public boolean itemSelected;
    
    public InputHandler(UI ui) {
        this.ui = ui;
        ui.canvas.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        if (!battle) {          
            if(keyCode == KeyEvent.VK_W) ui.field.getPlayer().moveUp();
            if(keyCode == KeyEvent.VK_S) ui.field.getPlayer().moveDown(ui.field.getHeight() - 1);
            if(keyCode == KeyEvent.VK_A) ui.field.getPlayer().moveLeft();
            if(keyCode == KeyEvent.VK_D) ui.field.getPlayer().moveRight(ui.field.getWidth() - 1);
            if(keyCode == KeyEvent.VK_UP) ui.field.getPlayer().moveUp();
            if(keyCode == KeyEvent.VK_DOWN) ui.field.getPlayer().moveDown(ui.field.getHeight() - 1);
            if(keyCode == KeyEvent.VK_LEFT) ui.field.getPlayer().moveLeft();
            if(keyCode == KeyEvent.VK_RIGHT) ui.field.getPlayer().moveRight(ui.field.getWidth() - 1);
        } else if (!items){
            if(keyCode == KeyEvent.VK_ENTER){
		actionSelected = true;
            }
            if(keyCode == KeyEvent.VK_UP) {
		ui.currentChoice--;
                if(ui.currentChoice == -1) {
                    ui.currentChoice = ui.options.length - 1;
                }
            }
            if(keyCode == KeyEvent.VK_DOWN) {
                ui.currentChoice++;
		if(ui.currentChoice == ui.options.length) {
                    ui.currentChoice = 0;
		}
            }
        } else {
            
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
}
