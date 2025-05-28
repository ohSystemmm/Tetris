package org.example.tetris;

import com.almasb.fxgl.entity.component.Component;

public class TetrominoComponent extends Component {

    public void moveDown() {
        entity.translateY(30);
    }

    public void moveLeft() {
        entity.translateX(-30);
    }

    public void moveRight() {
        entity.translateX(30);
    }

    public void rotate() {
        entity.rotateBy(90);
    }
}
