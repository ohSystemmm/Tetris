package org.example.tetris;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.util.Duration;

import java.util.Random;

public class MainApp extends GameApplication {

    private Entity currentTetromino;
    private final Random random = new Random();

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Tetris FXGL");
        settings.setVersion("1.0");
        settings.setWidth(400);
        settings.setHeight(600);
    }

    @Override
    protected void initGame() {
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
}
