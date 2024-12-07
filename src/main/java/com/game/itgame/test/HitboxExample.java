package com.game.itgame.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class HitboxExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        // Tạo hitbox 1 (khung viền đỏ)
        Rectangle hitbox1 = new Rectangle(50, 50, 100, 100);
        hitbox1.setStroke(Color.RED); // Màu viền
        hitbox1.setFill(null); // Không có màu nền

        // Tạo hitbox 2 (khung viền xanh)
        Rectangle hitbox2 = new Rectangle(120, 120, 150, 100);
        hitbox2.setStroke(Color.BLUE);
        hitbox2.setFill(null);

        // Thêm hitbox vào giao diện
        root.getChildren().addAll(hitbox1, hitbox2);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Hitbox Visualization");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
