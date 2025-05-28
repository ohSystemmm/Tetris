package org.example.tetris;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import java.util.List;

public class GameManager {

    public void checkLineClear() {
        List<Entity> blocks = FXGL.getGameWorld().getEntitiesByType(EntityType.TETROMINO);

        if (blocks.size() > 20) {
            blocks.forEach(Entity::removeFromWorld);
            FXGL.getWorldProperties().increment("score", 100);
        }
    }

    public void checkGameOver() {
        if (FXGL.getGameWorld().getEntitiesByType(EntityType.TETROMINO).stream()
                .anyMatch(e -> e.getY() < 30)) {
            FXGL.showMessage("Game Over! Score: " + FXGL.getWorldProperties().getInt("score"));
            FXGL.getGameController().exit();
        }
    }
}
