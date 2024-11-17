package com.game.itgame.test;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class ArcTypeExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(500, 200);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Vẽ cung với ArcType.OPEN
        gc.setFill(Color.LIGHTBLUE);
        gc.fillArc(50, 50, 100, 100, 45, 60, ArcType.OPEN);
        gc.strokeText("ArcType.OPEN", 50, 160);

        // Vẽ cung với ArcType.CHORD
        gc.setFill(Color.LIGHTGREEN);
        gc.fillArc(200, 50, 100, 100, 45, 60, ArcType.CHORD);
        gc.strokeText("ArcType.CHORD", 200, 160);

        // Vẽ cung với ArcType.ROUND
        gc.setFill(Color.LIGHTCORAL);
        gc.fillArc(350, 50, 100, 100, 45, 45, ArcType.ROUND);
        gc.strokeText("ArcType.ROUND", 350, 160);

        Group root = new Group();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, 500, 200);
        primaryStage.setTitle("JavaFX ArcType Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
