package com.game.itgame.test;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MovingRectangleCollisionExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Tạo một Pane để chứa các hình
        Pane root = new Pane();
        double ver = 2;

        // Tạo hình cung (Arc) với tâm (150, 150), bán kính x = 50, y = 50, góc bắt đầu 45 và góc mở 90 độ
        Arc arc = new Arc(150, 150, 50, 50, 45, 90);
        arc.setType(ArcType.ROUND);
        arc.setFill(Color.BLUE);

        // Tạo hình chữ nhật (Rectangle) có góc trên-trái tại (50, 120), rộng 60, cao 60
        Rectangle rect = new Rectangle(50, 120, 60, 60);
        rect.setFill(Color.RED);

        // Thêm các hình vào Pane
        root.getChildren().addAll(arc, rect);

        // Tạo Timeline để di chuyển Rectangle theo chiều ngang
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), event -> {
            // Di chuyển Rectangle sang phải
            rect.setX(rect.getX() + 2);

            // Kiểm tra nếu Rectangle chạm biên phải của Scene, di chuyển ngược lại
            if (rect.getX() > 250 || rect.getX() < 0) {
                rect.setX(rect.getX() > 250 ? 250 : 0); // Giới hạn di chuyển
            }

            // Kiểm tra va chạm với Arc
            Shape intersection = Shape.intersect(arc, rect);
            if (intersection.getBoundsInLocal().getWidth() > 0 && intersection.getBoundsInLocal().getHeight() > 0) {
                System.out.println("Arc và Rectangle có va chạm!");
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Thiết lập và hiển thị Scene
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Moving Rectangle and Arc Collision Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
