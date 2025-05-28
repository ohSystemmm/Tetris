module org.example.tetris {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens org.example.tetris to javafx.fxml;
    exports org.example.tetris;
}