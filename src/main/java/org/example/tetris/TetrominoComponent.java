package org.example.tetris;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;


public class TetrominoComponent extends Component {

    private static final int MOVE_STEP = 30;

    public void moveDown() {
        if (!checkCollision(0, MOVE_STEP))
            entity.setPosition(entity.getX(), entity.getY() + MOVE_STEP);
        else {
            FXGL.getWorldProperties().increment("score", 10); // Add scoring
            entity.removeFromWorld();
        }
    }

    public void moveLeft() {
        if (!checkCollision(-MOVE_STEP, 0))
            entity.setPosition(entity.getX() - MOVE_STEP, entity.getY());
    }

    public void moveRight() {
        if (!checkCollision(MOVE_STEP, 0))
            entity.setPosition(entity.getX() + MOVE_STEP, entity.getY());
    }

    public void rotate() {
        entity.rotateBy(90);
    }

    private boolean checkCollision(int dx, int dy) {
        return false; // TODO
    }
}