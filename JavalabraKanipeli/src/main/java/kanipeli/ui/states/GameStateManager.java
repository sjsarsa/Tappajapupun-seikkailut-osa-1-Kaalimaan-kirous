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
    private AudioPlayer menuMusic = new AudioPlayer("/audio/menu.wav");
    private AudioPlayer battleMusic = new AudioPlayer("/audio/battle.wav");
    private AudioPlayer fieldMusic = new AudioPlayer("/audio/field.wav");
    public boolean stop;
 
    public static final int MENUSTATE = 0;
    public static final int FIELDSTATE = 1;
    public static final int BATTLESTATE = 2;

    public void addState(int index, GameState state) {
        gameStates[index] = state;
    }

    public void setState(int index) {
        if (index != 0) menuMusic.stop();
        previousState = currentState;
        
        
        currentState = index;
        
        for (int i = 0; i < 30; i++) gameStates[currentState].run();
    }

    public void runState(int index) {
        menuMusic.play();
        previousState = currentState;
        currentState = index;
        for (int i = 0; i < 30; i++) gameStates[currentState].run();
    }

    public void keyPressed(int k) {
//        for (int i = 0; i < 4; i++) gameStates[currentState].run();
        gameStates[currentState].keyPressed(k);
        for (int i = 0; i < 3; i++) gameStates[currentState].run();
    }

    public int getState() {
        return currentState;
    }

    public int getPreviousState() {
        return previousState;
    }
}
