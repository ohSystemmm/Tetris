package org.example.tetris;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TetrominoFactory implements EntityFactory {

    @Spawns("Tetromino")
    public Entity newTetromino(SpawnData data) {
        Color color = data.get("color");

        return FXGL.entityBuilder()
                .type(EntityType.TETROMINO)
                .at(180, 0)
                .view(new Rectangle(30, 30, color))
                .with(new TetrominoComponent())
                .build();
    }
}
