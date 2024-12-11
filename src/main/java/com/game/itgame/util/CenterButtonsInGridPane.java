package com.game.itgame.util;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CenterButtonsInGridPane extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Tạo một GridPane
        GridPane gridPane = new GridPane();

        // Tạo các nút
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");

        // Đặt các nút vào chính giữa GridPane (dòng 0, cột 0)
        gridPane.add(button1, 0, 3);
        gridPane.add(button2, 0, 4);

        // Căn chỉnh GridPane vào chính giữa màn hình
        gridPane.setHgap(10); // Khoảng cách ngang giữa các cột
        gridPane.setVgap(10); // Khoảng cách dọc giữa các hàng
        gridPane.setAlignment(javafx.geometry.Pos.CENTER);

        // Tạo Scene
        Scene scene = new Scene(gridPane, 400, 300);

        primaryStage.setTitle("Center Buttons in GridPane");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
