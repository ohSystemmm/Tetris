package org.example.tetris;


import com.almasb.fxgl.dsl.components.AutoRotationComponent;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;
import static com.almasb.fxgl.dsl.FXGLForKtKt.texture;

public class GameFactory implements EntityFactory {
    @Spawns("background")
    public Entity spawnBackground(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.BACKGROUND)
                .view(new Rectangle(data.<Integer>get("width"),
                        data.<Integer>get("height"), Color.YELLOWGREEN))
                .with(new IrremovableComponent())
                .zIndex(-100)
                .build();
    }
}
