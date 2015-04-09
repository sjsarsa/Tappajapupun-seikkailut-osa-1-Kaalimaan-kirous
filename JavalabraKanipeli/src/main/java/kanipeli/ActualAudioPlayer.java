/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sami
 */
public class ActualAudioPlayer implements Runnable {

    private AudioPlayer music;
    private boolean stop = false;

    @Override
    public void run() {
        if (music != null) {
            while (true) {
                if (stop) music.stop();
                else music.begin();
            }
        }
    }

    public void setMusic(AudioPlayer music) {
        stop = true;
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(ActualAudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.music = music;
    }

    public AudioPlayer getMusic() {
        return music;
    }

}
