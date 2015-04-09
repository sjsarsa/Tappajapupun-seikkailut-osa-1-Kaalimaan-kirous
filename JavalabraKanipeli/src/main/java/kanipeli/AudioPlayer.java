/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli;

import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 * Plays friggin' music!
 * @author Sami
 */
public class AudioPlayer implements Runnable {

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
    public synchronized void play() {
        if (t == null) {
            t = new Thread(this);
            t.setPriority(7);
            t.start();
        } else {
            System.out.println("dafuq?");
        }
    }

    /**
     *
     */
    public synchronized void begin() {
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
                if (stop) break;
                sdl.write(bytesBuffer, 0, bytesRead);
                Thread.sleep(20);
            }
//            wait();
//            notify();
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
        stop = true;
        if (sdl.isRunning()) {
            sdl.flush();
            sdl.drain();
            sdl.stop();
        }
    }

    /**
     *
     */
    @Override
    public void run() {
        try {
            synchronized (this) {
                begin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
