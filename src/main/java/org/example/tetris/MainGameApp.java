package org.example.tetris;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;
import static com.almasb.fxgl.dsl.FXGL.*;

public class MainGameApp extends GameApplication {
    private final GameFactory gameFactory = new GameFactory();
    private Entity player;

    private static int screenWidth;
    private static int screenHeight;

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setHeight(screenHeight);
        settings.setWidth(screenWidth);
        settings.setFullScreenAllowed(true);
        settings.setFullScreenFromStart(true);
        settings.setTitle("Oracle Java Magazine - FXGL");
    }

    @Override
    protected void initInput() {
        onKey(KeyCode.LEFT, "left", () -> this.player.getComponent(PlayerComponent.class).left());
        onKey(KeyCode.RIGHT, "right", () -> this.player.getComponent(PlayerComponent.class).right());
        onKey(KeyCode.UP, "up", () -> this.player.getComponent(PlayerComponent.class).up());
        onKey(KeyCode.DOWN, "down", () -> this.player.getComponent(PlayerComponent.class).down());
        onKeyDown(KeyCode.SPACE, "Bullet", () -> this.player.getComponent(PlayerComponent.class).shoot());
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
        vars.put("lives", 5);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(this.gameFactory);

        // Background color
        spawn("background", new SpawnData(0, 0).put("width", getAppWidth())
                .put("height", getAppHeight()));

        // Circle in the middle of the screen
        int circleRadius = 80;
        spawn("center", new SpawnData(
                (getAppWidth() / 2) - (circleRadius / 2),
                (getAppHeight() / 2) - (circleRadius / 2))
                .put("x", (circleRadius / 2))
                .put("y", (circleRadius / 2))
                .put("radius", circleRadius));

        // Add the player
        this.player = spawn("duke", 0, 0);
    }

    @Override
    protected void initPhysics() {
        onCollisionBegin(EntityType.DUKE, EntityType.CENTER, (duke, center) ->
                this.player.getComponent(PlayerComponent.class).die());

        onCollisionBegin(EntityType.DUKE, EntityType.CLOUD, (enemy, cloud) ->
                this.player.getComponent(PlayerComponent.class).die());

        onCollisionBegin(EntityType.BULLET, EntityType.CLOUD, (bullet, cloud) -> {
            inc("score", 1);
            bullet.removeFromWorld();
            cloud.removeFromWorld();
        });
    }

    @Override
    protected void initUI() {
        Text scoreLabel = getUIFactoryService().newText("Score", Color.BLACK, 22);
        Text scoreValue = getUIFactoryService().newText("", Color.BLACK, 22);
        Text livesLabel = getUIFactoryService().newText("Lives", Color.BLACK, 22);
        Text livesValue = getUIFactoryService().newText("", Color.BLACK, 22);

        scoreLabel.setTranslateX(20);
        scoreLabel.setTranslateY(20);

        scoreValue.setTranslateX(90);
        scoreValue.setTranslateY(20);

        livesLabel.setTranslateX(getAppWidth() - 100);
        livesLabel.setTranslateY(20);

        livesValue.setTranslateX(getAppWidth() - 30);
        livesValue.setTranslateY(20);

        scoreValue.textProperty().bind(getWorldProperties().intProperty("score").asString());
        livesValue.textProperty().bind(getWorldProperties().intProperty("lives").asString());

        getGameScene().addUINodes(scoreLabel, scoreValue, livesLabel, livesValue);
    }

    @Override
    protected void onUpdate(double tpf) {
        if (getGameWorld().getEntitiesByType(EntityType.CLOUD).size() < 10) {
            spawn("cloud", getAppWidth() / 2, getAppHeight() / 2);
        }
    }
}
