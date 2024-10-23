package com.game.itgame.main;

import com.game.itgame.controller.GameInput;
import com.game.itgame.controller.GameRunnable;
import com.game.itgame.entity.Player;
import com.game.itgame.map.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MainApp extends Application {
    Pane pane = new Pane();
    Scene scene = new Scene(pane, 32 * 19, 32 * 12);// hứa sau sẽ fix :)
    public Player player;
    public Canvas canvas = new Canvas(32 * 19, 32 * 12);
    public Map map;
    GameInput gameInput;

    @Override
    public void start(Stage stage) throws Exception {
        pane.setStyle("-fx-background-color: lightblue;");
        pane.getChildren().add(canvas);
        map = new Map(canvas);
        player = new Player(map, canvas, 2*32, 9*32);

        stage.setScene(scene);
        stage.setResizable(false);

        GameInput gameInput = new GameInput(scene);
        player.setInput(gameInput);

        GameRunnable runnable = new GameRunnable(scene, this);
        Thread gameThread = new Thread(runnable);
        gameThread.setDaemon(true);
        gameThread.start();

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

