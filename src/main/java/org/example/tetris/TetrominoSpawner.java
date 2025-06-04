package org.example.tetris;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.AutoRotationComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;
import static com.almasb.fxgl.dsl.FXGLForKtKt.texture;

public class TetrominoSpawner {

    public static Entity spawnTetromino(TetrominoType type) {
        return switch (type) {
            case I -> entityBuilder()
                    .type(EntityType.TETROMINO)
                    .viewWithBBox(texture("Cyan-Block.png", 50, 50))
                    .collidable()
                    .with(new TetrominoComponent())
                    .build();
            case S -> entityBuilder()
                    .type(EntityType.TETROMINO)
                    .viewWithBBox(texture("Green-Block.png", 50, 50))
                    .collidable()
                    .with(new TetrominoComponent())
                    .build();
            case Z -> entityBuilder()
                    .type(EntityType.TETROMINO)
                    .viewWithBBox(texture("Red-Block.png", 50, 50))
                    .collidable()
                    .with(new TetrominoComponent())
                    .build();
            default -> null;
        };
    }
}
