package org.example.tetris;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import javafx.scene.paint.Color;

public class TetrominoSpawner {

    public static Entity spawnTetromino(TetrominoType type) {
        Color color = switch (type) {
            case I -> Color.CYAN;
            case O -> Color.YELLOW;
            case T -> Color.PURPLE;
            case S -> Color.GREEN;
            case Z -> Color.RED;
            case J -> Color.BLUE;
            case L -> Color.ORANGE;
        };

        return FXGL.spawn("Tetromino", new SpawnData().put("color", color));
    }
}
