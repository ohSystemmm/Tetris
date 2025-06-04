package org.example.tetris;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
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
            if (currentTetromino != null) {
                currentTetromino.getComponent(TetrominoComponent.class).moveDown();
            }
        }, Duration.seconds(1.5));
    }



    private void spawnNewTetromino() {
        TetrominoType type = TetrominoType.values()[random.nextInt(TetrominoType.values().length)];
        currentTetromino = TetrominoSpawner.spawnTetromino(type);
        FXGL.getGameWorld().addEntity(currentTetromino);
    }

    public static void main(String[] args) {
        launch(args);
    }

//    @Override
//    protected void initInput(){
//        onKey(KeyCode.LEFT, "left", () -> this.currentTetromino.getComponent(TetrominoComponent.class).moveLeft() && time.sleep);
//        onKey(KeyCode.RIGHT, "right", () ->  this.currentTetromino.getComponent(TetrominoComponent.class).moveRight());
//        onKey(KeyCode.UP, "rotate", () -> this.currentTetromino.getComponent(TetrominoComponent.class).rotate());
//    }
    @Override
    protected void initInput() {
        FXGL.onKeyDown(KeyCode.LEFT, () -> {
            if (currentTetromino != null) {
                currentTetromino.getComponent(TetrominoComponent.class).moveLeft();
            }

            FXGL.runOnce(() -> {
            }, Duration.seconds(1.5));
        });
        FXGL.onKeyDown(KeyCode.RIGHT, () -> {
            if (currentTetromino != null) {
                currentTetromino.getComponent(TetrominoComponent.class).moveRight();
            }

            FXGL.runOnce(() -> {
            }, Duration.seconds(1.5));
        });
        FXGL.onKeyDown(KeyCode.UP, () -> {
            if (currentTetromino != null) {
                currentTetromino.getComponent(TetrominoComponent.class).rotate();
            }

            FXGL.runOnce(() -> {
            }, Duration.seconds(1.5));
        });

    }
}
