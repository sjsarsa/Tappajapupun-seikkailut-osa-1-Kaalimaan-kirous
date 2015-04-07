/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui;

import java.util.ArrayList;

/**
 *
 * @author sjsarsa
 */
public class GameStateManager {

    private GameState[] gameStates = new GameState[3];
    private int currentState = 0;
    private int previousState = 0;
    public boolean stop;
 
    public static final int MENUSTATE = 0;
    public static final int FIELDSTATE = 1;
    public static final int BATTLESTATE = 2;

    public void addState(int index, GameState state) {
        gameStates[index] = state;
    }

    public void setState(int index) {
        previousState = currentState;
        currentState = index;
        for (int i = 0; i < 30; i++) gameStates[currentState].run();
    }

    public void runState(int index) {
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
