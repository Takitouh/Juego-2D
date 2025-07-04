package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getClassLoader().getResource("sound/BeginningOfAdventure.wav");
        soundURL[1] = getClass().getClassLoader().getResource("sound/Door.wav");
        soundURL[2] = getClass().getClassLoader().getResource("sound/pickupKey.wav");
        soundURL[3] = getClass().getClassLoader().getResource("sound/pickupPowerUp.wav");

    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            System.out.println("Clip get audio");
            clip = AudioSystem.getClip();
            System.out.println("Audio before open");
            clip.open(ais);
        } catch (Exception e) {

        }
    }

    public void play() {
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
