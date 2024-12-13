package com.game.itgame.gameStart;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class Main extends Application {
    private int guide = 2;
    public static Scene startScene;
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Scene mainScene = new Scene(root, 1200, 650);

        startScene = greetScene(mainScene, stage);
        Main.stage = stage;

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

        ImageView startbutton = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/screen/START-stage 2.png"))));
        ImageView guideImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/screen/huongdan.png"))));
        ImageView guidebutton = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/screen/GUIDE-2.png"))));

        startbutton.setFitWidth(100);
        startbutton.setPreserveRatio(true);
        startbutton.setLayoutY(400);
        startbutton.setLayoutX(550);

        guideImage.setFitWidth(800);
        guideImage.setPreserveRatio(true);
        guideImage.setLayoutY(100);
        guideImage.setLayoutX(600 - guideImage.getFitWidth()/2);

        guidebutton.setFitWidth(150);
        guidebutton.setPreserveRatio(true);
        guidebutton.setLayoutY(445);
        guidebutton.setLayoutX(525);

        startbutton.setOnMouseClicked(event -> {
            try {
                RunGame.run(mainScene);
                stage.setScene(mainScene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Scene startScene = new Scene(root, 1200, 650);
        startScene.setOnMouseClicked(event -> {
            guide--;
            System.out.println(guide);
            if(guide <= 0){
                guideImage.toBack();
                root.getChildren().remove(guideImage);
            }
        });
        guidebutton.setOnMouseClicked(event -> {
            root.getChildren().add(guideImage);
            guide = 2;
        });


        ImageView startImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/screen/mainscreenn.png"))));
        root.getChildren().add(startImage);
        root.getChildren().add(startbutton);
        root.getChildren().add(guidebutton);
        return startScene;
    }
}
