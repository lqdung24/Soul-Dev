package com.game.itgame.util;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ClickOnImageViewExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Tải hình ảnh
        Image image = new Image("E:\\Code\\ITGame\\src\\main\\resources\\images\\START-stage 2.png"); // Thay bằng URL hoặc đường dẫn hợp lệ
        ImageView imageView = new ImageView(image);

        // Đặt kích thước (tuỳ chọn)
        imageView.setFitWidth(200);
        imageView.setFitHeight(150);

        // Thêm sự kiện click chuột
        imageView.setOnMouseClicked(event -> {
            System.out.println("Image clicked at: (" + event.getX() + ", " + event.getY() + ")");
        });

        // Đưa ImageView vào StackPane
        StackPane root = new StackPane(imageView);

        // Tạo Scene và hiển thị
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Click on ImageView Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
