package com.game.itgame.util;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.nio.file.Paths;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Tạo nút để phát nhạc
        Button playButton = new Button("Play Music");
        new GameSound();
        //GameSound.setting();
        // Thêm sự kiện khi nhấn nút
        playButton.setOnAction(event -> {
            //GameSound.playSword();
            //GameSound.playerHitted();
            //GameSound.mobHitted();
            GameSound.playBow();
        });

        // Dừng nhạc khi thoát ứng dụng
        primaryStage.setOnCloseRequest(event -> {
            GameSound.bowSound.stop();
        });

        // Tạo giao diện
        StackPane root = new StackPane(playButton);
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("JavaFX Music Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
