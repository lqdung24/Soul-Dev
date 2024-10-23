package com.game.itgame.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Objects;

public class TileMapExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Tạo các hình ảnh cho các loại ô, sử dụng đường dẫn tương đối từ thư mục resources
        Image grassImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/game/itgame/test/1.jpg")));
        Image waterImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Image/2.png")));

        // Tạo GridPane để tổ chức bản đồ
        GridPane gridPane = new GridPane();

        // Mảng bản đồ đại diện cho từng loại ô
        int[][] map = {
                {0, 1, 0, 0, 1},
                {1, 0, 1, 0, 0},
                {0, 0, 0, 1, 1},
                {1, 0, 1, 0, 0},
                {0, 1, 0, 1, 0}
        };

        // Lặp qua các hàng và cột để thêm ảnh từng ô vào GridPane
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                // Chọn ảnh dựa trên giá trị trong mảng map
                ImageView imageView = new ImageView(map[row][col] == 0 ? grassImage : waterImage);

                // Điều chỉnh kích thước ô (tùy chọn)
                imageView.setFitWidth(64);
                imageView.setFitHeight(64);

                // Thêm ImageView vào GridPane tại vị trí (col, row)
                gridPane.add(imageView, col, row);
            }
        }

        // Tạo Scene và hiển thị nó
        Scene scene = new Scene(gridPane, 320, 320);
        primaryStage.setTitle("Tile Map Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
