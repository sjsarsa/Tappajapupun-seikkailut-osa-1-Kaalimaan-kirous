/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.states;

import java.util.ArrayList;
import kanipeli.AudioPlayer;

/**
 *
 * @author sjsarsa
 */
public class GameStateManager {

    private GameState[] gameStates = new GameState[3];
    private int currentState = 0;
    private int previousState = 0;
    public AudioPlayer music;
//    public static final int MENUSTATE = 0;
//    public static final int FIELDSTATE = 1;
//    public static final int BATTLESTATE = 2;

    public void addState(int index, GameState state) {
        gameStates[index] = state;
    }

    public void setState(int index) {
        previousState = currentState;                
        currentState = index;
        
        try {
        for (int i = 0; i < 40; i++) {
            gameStates[currentState].run();
            Thread.sleep(2);
        }
        }catch (InterruptedException e) {
            System.out.println("ui interrupted");
        }
    }   
    
    public void init() {
        if (music != null) music.stop();
        music = new AudioPlayer("/audio/menu.wav");
        music.play();
        setState(0);
    }

    public void keyPressed(int k) {
        gameStates[currentState].keyPressed(k);
        for (int i = 0; i < 5; i++) gameStates[currentState].run();
    }

    public int getState() {
        return currentState;
    }

    public int getPreviousState() {
        return previousState;
    }
}
