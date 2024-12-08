package com.game.itgame.util;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartScreenExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Màn hình khởi động
        StackPane startScreen = createStartScreen(primaryStage);

        // Tạo Scene cho màn hình khởi động
        Scene startScene = new Scene(startScreen, 600, 400);

        // Cài đặt và hiển thị Stage
        primaryStage.setTitle("Start Screen Example");
        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    // Tạo màn hình khởi động
    private StackPane createStartScreen(Stage stage) {
        // Tựa game
        Text title = new Text("My Game");
        title.setFont(Font.font("Arial", 50));
        title.setFill(Color.DARKBLUE);

        // Nút Start
        Button startButton = new Button("Start");
        startButton.setFont(Font.font(20));
        startButton.setOnAction(e -> {
            // Khi nhấn nút Start, chuyển sang màn hình chơi game
            Scene gameScene = createGameScene(stage);
            stage.setScene(gameScene);
        });

        // Layout cho màn hình khởi động
        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, startButton);
        layout.setStyle("-fx-alignment: center; -fx-background-color: lightgray;");

        return new StackPane(layout);
    }

    // Tạo màn hình chơi game
    private Scene createGameScene(Stage stage) {
        // Nội dung game (đơn giản chỉ là Text ở đây)
        Text gameText = new Text("Game Started!");
        gameText.setFont(Font.font("Arial", 40));
        gameText.setFill(Color.GREEN);

        // Layout cho màn hình chơi game
        StackPane gameLayout = new StackPane(gameText);
        gameLayout.setStyle("-fx-background-color: black;");

        return new Scene(gameLayout, 600, 400);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
