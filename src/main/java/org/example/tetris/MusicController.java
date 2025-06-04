package org.example.tetris;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class MusicController {
    String audioFilePath;
    Media media;
    MediaPlayer mediaPlayer;

    //Currently the Music is in main/resources/assets/music
    public MusicController(String audioFilePath) {
        this.audioFilePath = audioFilePath;
        this.media = new Media(new File(this.audioFilePath).toURI().toString());
        this.mediaPlayer = new MediaPlayer(media);
    }

    public void play() {
        mediaPlayer.play();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }
    public void pause() {
        mediaPlayer.pause();
    }
    public void stop() {
        mediaPlayer.stop();
    }
}
