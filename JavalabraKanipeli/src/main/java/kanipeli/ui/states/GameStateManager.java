/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.states;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import kanipeli.AudioPlayer;

/**
 * Manages game states and music to be played. Sends info of keys
 * being pressed to game states.
 *
 * @author sjsarsa
 */
public class GameStateManager {

    private GameState[] gameStates = new GameState[3];
    private int currentState = 0;
    private int previousState = 0;

    private String menuMusic = "/audio/menu.wav";
    private String fieldMusic = "/audio/field.wav";
    private String battleMusic = "/audio/battle.wav";
    private String bossBattleMusic = "/audio/bossBattle.wav";
    private String gameOverMusic = "/audio/gameOver.wav";
    private AudioPlayer music;
//    public static final int MENUSTATE = 0;
//    public static final int FIELDSTATE = 1;
//    public static final int BATTLESTATE = 2;

    /**
     *Adds a game state or replaces one in the specified index of an array 
     * containing the games states
     * @param index slot of the array
     * @param state state to be put in th slot
     */
    public void addState(int index, GameState state) {
        gameStates[index] = state;
    }

    public void setMusic(String song) {
        music.stop();
        try {
            Thread.sleep(400);
            music = new AudioPlayer(song);
            music.play();
        } catch (InterruptedException ex) {
            Logger.getLogger(GameStateManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getBattleMusic() {
        return battleMusic;
    }

    public String getBossBattleMusic() {
        return bossBattleMusic;
    }

    public String getFieldMusic() {
        return fieldMusic;
    }

    public String getGameOverMusic() {
        return gameOverMusic;
    }

    public String getMenuMusic() {
        return menuMusic;
    }

    /**
     *Sets previous state to be the current state and then changes the current
     * state into a new one. Finally runs the new current state.
     * @param index integer value that tells which state is the new state.
     * (0 = menu, 1 = field, 2 = battle)
     */
    public void setState(int index) {
        previousState = currentState;
        currentState = index;
        gameStates[currentState].run();
    }

    /**
     *Initialises the game state manager.
     * Plays menu music and sets the initial state as menu state.
     */
    public void init() {
        music = new AudioPlayer(menuMusic);
        music.play();
        setState(0);
    }

    /**
     *Sends keyCode of a key pressed to the current game state and runs the
     * state.
     * @param k keyCode
     */
    public void keyPressed(int k) {
        gameStates[currentState].keyPressed(k);
        gameStates[currentState].run();
    }

    public int getState() {
        return currentState;
    }

    public int getPreviousState() {
        return previousState;
    }
}
