package com.game.itgame.gameStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class Main extends Application {
    static Button button = new Button();
    static Button guideButton = new Button(), quitButton = new Button();

    @Override
    public void start(Stage stage) throws IOException {

        Group root = new Group();
        Scene mainScene = new Scene(root, 1200, 650);

        Scene startScene = greetScene(mainScene, stage);
        root.getChildren().add(quitButton);

        stage.setScene(startScene);
        stage.setTitle("Soul Dev");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Scene greetScene(Scene mainScene, Stage stage) {
        Group root = new Group();

        button.setLayoutX(500);
        button.setLayoutY(500);
        guideButton.setLayoutX(500);
        guideButton.setLayoutY(600);
        guideButton.setText("Guide");

        Image startButton = new Image(getClass().getResourceAsStream("/images/START-stage 2.png"));
        ImageView imageView = new ImageView(startButton);
        ImageView guideImage = new ImageView(new Image(getClass().getResourceAsStream("/images/huongdan.png")));
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        guideImage.setFitWidth(800);
        guideImage.setFitHeight(450);

        button.setGraphic(imageView);
        button.setOnAction(event -> {
            try {
                RunGame.run(mainScene, stage);
                stage.setScene(mainScene);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        guideButton.setOnAction(event -> {
            root.getChildren().add(guideImage);
        });

        Scene startScene = new Scene(root, 1200, 650);

        startScene.setOnMouseClicked(event -> {
            root.getChildren().remove(guideImage);
        });
        Image startImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mainscreen.png")));
        Canvas startCanvas = new Canvas(1200, 650);
        root.getChildren().addAll(startCanvas, button, guideButton);
        GraphicsContext gc = startCanvas.getGraphicsContext2D();
        gc.drawImage(startImage, 0, 0, 1200, 650);
        return startScene;
    }
}
