
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli;
 
import java.awt.Canvas;
import kanipeli.ui.GamePanel;
import kanipeli.ui.states.GameStateManager;
import kanipeli.ui.states.MenuState;
import kanipeli.ui.Screen;

/**
 *
 * @author Sami
 */
public class main {

    public static void main(String[] args) {      
        int width = 256, height = 256, scale = 3;
        
        Canvas canvas = new Canvas();
        Screen screen = new Screen(width, height);
        
        GameStateManager gsm = new GameStateManager();
        GamePanel frame = new  GamePanel(width, height, scale, canvas, gsm);
        
        MenuState ms = new MenuState(canvas, screen, gsm);
        gsm.addState(0, ms);
        gsm.init();
    }
}
