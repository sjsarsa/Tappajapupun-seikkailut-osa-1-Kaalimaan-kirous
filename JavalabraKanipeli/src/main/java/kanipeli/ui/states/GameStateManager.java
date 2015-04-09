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
    private AudioPlayer[] songs = new AudioPlayer[10];
    private AudioPlayer menuMusic = new AudioPlayer("/audio/menu.wav");
    private AudioPlayer fieldMusic = new AudioPlayer("/audio/field.wav");
    private AudioPlayer battleMusic = new AudioPlayer("/audio/battle.wav");
    private AudioPlayer bossBattleMusic = new AudioPlayer("/audio/bossBattle.wav");
    private AudioPlayer gameOverMusic = new AudioPlayer("/audio/gameOver.wav");
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

    public void setMusic(AudioPlayer song) {
        music.stop();
        music = song;
        music.play();
    }

    public AudioPlayer getBattleMusic() {
        return battleMusic;
    }

    public AudioPlayer getBossBattleMusic() {
        return bossBattleMusic;
    }

    public AudioPlayer getFieldMusic() {
        return fieldMusic;
    }

    public AudioPlayer getGameOverMusic() {
        return gameOverMusic;
    }

    public AudioPlayer getMenuMusic() {
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
//        AudioPlayer menuMusic = new AudioPlayer("/audio/menu.wav");
//        AudioPlayer fieldMusic = new AudioPlayer("/audio/field.wav");
//        AudioPlayer battleMusic = new AudioPlayer("/audio/battle.wav");
//        AudioPlayer bossBattleMusic = new AudioPlayer("/audio/bossBattle.wav");
//        AudioPlayer gameOverMusic = new AudioPlayer("/audio/gameOver.wav");
//        songs[0] = menuMusic;
//        songs[1] = fieldMusic;
//        songs[2] = battleMusic;
//        songs[3] = bossBattleMusic;
//        songs[4] = gameOverMusic;
//        if (music != null) music.stop();
//        music = new AudioPlayer("/audio/menu.wav");
        music = menuMusic;
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
