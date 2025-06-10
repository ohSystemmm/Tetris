package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;

public class Sound {
    private Clip musicClip;
    private URL[] url = new URL[10];

    public Sound() {
        url[0] = getClass().getResource("../se/bgm.wav"); // background music
        url[1] = getClass().getResource("../se/rotation.wav");
        url[2] = getClass().getResource("../se/delete line.wav");
        url[3] = getClass().getResource("../se/gameover.wav");
        url[4] = getClass().getResource("../se/touchfloor.wav");
    }

    public void play(int i, boolean music) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(url[i]);
            Clip clip = AudioSystem.getClip();

            if (music) {
                musicClip = clip;
            }

            clip.open(ais);
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == Type.STOP) {
                        clip.close();
                    }
                }
            });
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loop() {
        musicClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        if (musicClip != null) {
            musicClip.stop();
            musicClip.close();
        }
    }
}
