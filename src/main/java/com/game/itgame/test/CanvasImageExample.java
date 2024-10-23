package com.game.itgame.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class CanvasImageExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        Image image = new Image("C:\\Users\\MyPC\\IdeaProjects\\ITGame\\src\\main\\resources\\Image\\MapImg\\map_Img.jpg");
        double screenWidth = 1000; // hệ số tạm thời cho hình nó đẹp thôi
        double screenHeight = screenWidth/1.618;
//        double screenWidth = image.getWidth()/1.2; // hệ số tạm thời cho hình nó đẹp thôi
//        double screenHeight = image.getHeight()/1.2;
        // Tạo một Canvas
        Canvas canvas = new Canvas(screenWidth, screenHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Tải ảnh từ tệp
        // Đường dẫn tới ảnh

        // Vẽ ảnh lên Canvas
        gc.drawImage(image, 0, 0, screenWidth, screenHeight); // Vị trí (50, 50) trên Canvas

        // Tạo một StackPane và thêm Canvas vào
        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        // Tạo Scene và hiển thị
        Scene scene = new Scene(root, screenWidth, screenHeight);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Canvas Image Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
