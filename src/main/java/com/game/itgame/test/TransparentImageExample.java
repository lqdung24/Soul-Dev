package com.game.itgame.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TransparentImageExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Tạo đối tượng Image từ file ảnh
        Image image = new Image("E:\\Code\\ITGame\\src\\main\\resources\\images\\mob\\mob1\\mob1.png"); // Đảm bảo file tồn tại

        // Tạo ImageView và đặt ảnh vào
        ImageView imageView = new ImageView(image);

        // Thiết lập độ trong suốt cho ảnh (0.0 là hoàn toàn trong suốt, 1.0 là không trong suốt)
        imageView.setOpacity(0.8); // Thay đổi giá trị Opacity theo nhu cầu

        // Đặt ImageView vào layout
        StackPane root = new StackPane();
        root.getChildren().add(imageView);

        // Thiết lập scene và hiển thị
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Transparent Image Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
