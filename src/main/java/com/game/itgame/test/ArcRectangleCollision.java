package com.game.itgame.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class ArcRectangleCollision extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        // Tạo hình cung (Arc)
        Arc arc = new Arc(150, 150, 100, 100, 0, 90); // Tâm (150, 150), bán kính 100, góc quét 90 độ
        arc.setFill(Color.BLUE);
        arc.setStroke(Color.BLUE);
        arc.setStrokeWidth(2);
        arc.setType(ArcType.OPEN);

        // Tạo hình chữ nhật (Rectangle)
        Rectangle rectangle = new Rectangle(200, 100, 100, 100); // Góc trên bên trái (200, 100), kích thước 100x100
        rectangle.setFill(Color.RED);
        rectangle.setStroke(Color.RED);
        rectangle.setStrokeWidth(2);

        // Kiểm tra va chạm
        Shape intersection = Shape.intersect(arc, rectangle);

        // Nếu có giao nhau, hiển thị vùng giao nhau
        if (intersection.getBoundsInLocal().getWidth() > 0) {
            rectangle.setStroke(Color.GREEN); // Đổi màu viền hình chữ nhật để báo hiệu va chạm
            intersection.setFill(Color.YELLOW); // Vùng giao nhau màu vàng
            root.getChildren().add(intersection); // Thêm vùng giao nhau vào giao diện
            System.out.println("ok");
        }

        root.getChildren().addAll(arc, rectangle);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Arc and Rectangle Collision");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
