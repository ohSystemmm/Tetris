package org.example.tetris;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

public class GameInputHandler {

    public static void initializeControls(Input input, Entity tetromino) {
        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                tetromino.getComponent(TetrominoComponent.class).moveLeft();
            }
        }, KeyCode.LEFT);

        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                tetromino.getComponent(TetrominoComponent.class).moveRight();
            }
        }, KeyCode.RIGHT);

        input.addAction(new UserAction("Rotate") {
            @Override
            protected void onAction() {
                tetromino.getComponent(TetrominoComponent.class).rotate();
            }
        }, KeyCode.UP);
    }
}
