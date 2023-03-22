package org.example;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class Sound {

    public void sentMessage() {
        play("/sent.wav");
    }

    private void play(String filePath) {
        try (InputStream bis = new BufferedInputStream(getClass().getResourceAsStream(filePath))) {
            AudioInputStream ais = AudioSystem.getAudioInputStream(bis);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
