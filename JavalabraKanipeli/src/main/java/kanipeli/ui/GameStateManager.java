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

    public void run() {
        while (!stop) {
            gameStates[currentState].run();
        }
    }

    public void addState(int index, GameState state) {
        gameStates[index] = state;
    }

    public void setState(int index) {
        previousState = currentState;
        currentState = index;
    }

    public void runState(int state) {
        currentState = state;
        gameStates[state].run();
    }

    public void keyPressed(int k) {
        gameStates[currentState].keyPressed(k);
    }

    public int getState() {
        return currentState;
    }

    public int getPreviousState() {
        return previousState;
    }
}
