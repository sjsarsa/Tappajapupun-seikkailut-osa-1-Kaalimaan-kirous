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
 *Contains and manages game states and music to be played.
 * Sends info of keys being pressed to game states.
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
     *
     * @param index
     * @param state
     */
    public void addState(int index, GameState state) {
        gameStates[index] = state;
    }

    public void setMusic(String song) {
        music.stop();
        try {
            Thread.sleep(100);        
        music = new AudioPlayer(song);
        Thread.sleep(50);
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
     *
     * @param index
     */
    public void setState(int index) {
        previousState = currentState;
        currentState = index;

        try {
            for (int i = 0; i < 40; i++) {
                gameStates[currentState].run();
                Thread.sleep(2);

            }
        } catch (InterruptedException e) {
            System.out.println("ui interrupted");
        }
    }

    /**
     *
     */
    public void init() {
        music = new AudioPlayer(menuMusic);
        music.play();
        setState(0);
    }

    /**
     *
     * @param k
     */
    public void keyPressed(int k) {
        gameStates[currentState].keyPressed(k);
        for (int i = 0; i < 5; i++) {
            gameStates[currentState].run();
        }
    }

    /**
     *
     * @return
     */
    public int getState() {
        return currentState;
    }

    /**
     *
     * @return
     */
    public int getPreviousState() {
        return previousState;
    }
}
