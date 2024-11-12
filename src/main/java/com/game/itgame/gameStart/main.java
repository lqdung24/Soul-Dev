package com.game.itgame.gameStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/game/itgame/client.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);

        runGame.run(scene);

        stage.setTitle("JavaFX Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
