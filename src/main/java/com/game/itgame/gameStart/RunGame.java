package com.game.itgame.gameStart;

import com.game.itgame.controller.CanvasController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RunGame {
    static CanvasController controller;
    public static void run(Scene scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RunGame.class.getResource("/com/game/itgame/canvas.fxml"));

        Image cursor = new Image(Objects.requireNonNull(RunGame.class.getResourceAsStream("/images/cursorImage/cursor.png")));
        scene.setRoot(fxmlLoader.load());
        scene.setCursor(new ImageCursor(cursor, cursor.getWidth() / 2, cursor.getHeight() / 2));
        controller = fxmlLoader.getController();
        controller.update(scene);
    }
}