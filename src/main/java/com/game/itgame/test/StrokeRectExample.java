package com.game.itgame.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StrokeRectExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(400, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Thiết lập màu và độ dày viền
        gc.setStroke(Color.RED);
        gc.setLineWidth(3);

        // Vẽ đường viền hình chữ nhật
        gc.strokeRect(50, 50, 200, 100);

        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("StrokeRect Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
