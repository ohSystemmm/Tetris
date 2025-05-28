package org.example.tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.List;

public interface Block {
    List<Rectangle> getShape();
    Color getColor();
}