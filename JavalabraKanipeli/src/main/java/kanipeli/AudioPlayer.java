/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli;

import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import kanipeli.ui.states.GameStateManager;
import sun.org.mozilla.javascript.tools.jsc.Main;

/**
 *
 * @author Sami
 */
public class AudioPlayer implements Runnable {

    private Clip clip;
    private SourceDataLine sdl;
    private AudioInputStream in;
    private Thread t;
    private boolean stop = false;

    /**
     *
     *
     * @param s File name
     */
    public AudioPlayer(String s) {
        try {
            in = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));
//            File file = new File("src/main/resources" + s);
//            AudioInputStream in = AudioSystem.getAudioInputStream(file);
            AudioFormat baseFormat = in.getFormat();
            AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(),
                    16, 2, baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
            AudioInputStream din = AudioSystem.getAudioInputStream(decodedFormat, in);
//            DataLine.Info info = new DataLine.Info(Clip.class, baseFormat);
//            clip = AudioSystem.getClip();
//            clip = (Clip)AudioSystem.getLine(info);
//            clip.open(din);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, baseFormat);
            sdl = (SourceDataLine) AudioSystem.getLine(info);
            sdl.open(decodedFormat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void play() {
        if (t == null) {
            t = new Thread(this);
            t.setPriority(7);
            t.start();
        } else {
            this.notify();
            begin();
        }
//        if (clip == null) return;
//        stop();
//        clip.setFramePosition(0);
//        clip.start();
    }

    /**
     *
     */
    public void begin() {
        try {
            if (sdl == null) {
                return;
            }
//            stop();
            stop = false;
            sdl.start();
            int BUFFER_SIZE = 4096;

            byte[] bytesBuffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;

            while ((bytesRead = in.read(bytesBuffer)) != -1) {
                sdl.write(bytesBuffer, 0, bytesRead);
//                concurrentQueue
                if (stop) {
                    break;
                }
                Thread.sleep(20);
            }
            Thread.sleep(1000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ie) {
            System.out.println("audio interrupted");
        }
    }

    /**
     *
     */
    public void stop() {
//        if(clip.isRunning()) clip.stop();
        stop = true;
        if (sdl.isRunning()) {
            sdl.flush();
//            sdl.stop();
        }

        System.out.println(t.isAlive());
//        t.interrupt();

//        try {
//        t.wait();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(t.getThreadGroup());
//            t.interrupt();
//        }
    }

//    public void close() {
//        stop();
//        clip.close();
//
//    }
    /**
     *
     */
    @Override
    public void run() {

        try {
            synchronized (this) {
                Main.class.wait();

                begin();

                Thread.sleep(1000);
                this.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
