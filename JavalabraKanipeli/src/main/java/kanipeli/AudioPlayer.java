/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Sami
 */
public class AudioPlayer {
    
    private Clip clip;
    public AudioPlayer(String s) {
        
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));
//            File file = new File(s);
//            AudioInputStream in = AudioSystem.getAudioInputStream(file);
            AudioFormat baseFormat = in.getFormat();
            AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(),
                    16, 2, baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
            AudioInputStream din = AudioSystem.getAudioInputStream(decodedFormat, in);
            clip = AudioSystem.getClip();
            clip.open(din);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void play() {
        if (clip == null) return;
        stop();
        clip.setFramePosition(0);
        clip.start();
    }
    
    public void stop() {
        if(clip.isRunning()) clip.stop();
    }
    
    public void close() {
        stop();
        clip.close();
    }
    

    
    
}
