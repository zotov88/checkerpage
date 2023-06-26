package org.example.helpers;

import org.example.constants.NotifyMessage;
import org.example.constants.PageAnalysis;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Objects;

public class Sound {

    public void sentMessage() {
        play(NotifyMessage.SOUND_MESSAGE_NOTIFY);
    }

    private void play(final String filePath) {
        try (InputStream bis = new BufferedInputStream(Objects.requireNonNull(getClass().getResourceAsStream(filePath)))) {
            AudioInputStream ais = AudioSystem.getAudioInputStream(bis);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
