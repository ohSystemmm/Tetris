package org.example.tetris;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;

public class MainApp extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Tetris with FXGL");
        settings.setVersion("1.0");
        settings.setWidth(400);
        settings.setHeight(600);
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new TetrominoFactory());
        spawnTetromino();
    }

    private void spawnTetromino() {
        Entity tetromino = TetrominoSpawner.spawnTetromino(TetrominoType.I);
        FXGL.getGameWorld().addEntity(tetromino);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
