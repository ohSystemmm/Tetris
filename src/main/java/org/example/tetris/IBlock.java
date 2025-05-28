package org.example.tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class IBlock implements Block {

    @Override
    public List<Rectangle> getShape() {
        List<Rectangle> shape = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            shape.add(new Rectangle(20, 20, Color.CYAN));
        }
        return shape;
    }

    @Override
    public Color getColor() {
        return Color.CYAN;
    }
}
