package com.game.itgame.util;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CanvasAndImageViewExampleWithStackPane extends Application {

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();

        // Tạo Canvas
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Vẽ gì đó lên canvas
        gc.fillRect(100, 100, 200, 200);

        // Tạo ImageView
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/images/screen/huongdan.png")));
        imageView.setX(200); // Vị trí X
        imageView.setY(150); // Vị trí Y
        imageView.setFitWidth(100); // Kích thước
        imageView.setFitHeight(100);

        // Thêm Canvas và ImageView vào StackPane
        root.getChildren().addAll(canvas, imageView);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Canvas and ImageView in StackPane");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
