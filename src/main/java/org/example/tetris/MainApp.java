package org.example.tetris;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.Random;

import static com.almasb.fxgl.dsl.FXGL.*;

public class MainApp extends GameApplication {

    private Entity currentTetromino;
    private final Random random = new Random();
    private static MusicController musicController;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Tetris FXGL");
        settings.setVersion("1.0");
        settings.setWidth(400);
        settings.setHeight(600);
    }

    @Override
    protected void initGame() {
        musicController = new MusicController("src/main/resources/assets/music/tetris.mp3");
        musicController.play();
        FXGL.getGameWorld().addEntityFactory(new TetrominoFactory());
        spawnNewTetromino();

        FXGL.run(() -> {
            if (currentTetromino != null)
                currentTetromino.getComponent(TetrominoComponent.class).moveDown();
        }, Duration.seconds(0.8));
    }

    private void spawnNewTetromino() {
        TetrominoType type = TetrominoType.values()[random.nextInt(TetrominoType.values().length)];
        currentTetromino = TetrominoSpawner.spawnTetromino(type);
        FXGL.getGameWorld().addEntity(currentTetromino);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initInput(){
        onKey(KeyCode.LEFT, "left2", () -> this.currentTetromino.getComponent(TetrominoComponent.class).moveLeft());
        onKey(KeyCode.RIGHT, "right", () ->  this.currentTetromino.getComponent(TetrominoComponent.class).moveRight());
        onKey(KeyCode.UP, "left", () -> this.currentTetromino.getComponent(TetrominoComponent.class).rotate());
    }
}
