package com.game.itgame.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TopDownGame extends Application {
    private static final int TILE_SIZE = 32;
    private static final int MAP_WIDTH = 50;
    private static final int MAP_HEIGHT = 50;
    private static final int PLAYER_WIDTH = 16;
    private static final int PLAYER_HEIGHT = 20;
    private static final int VIEW_WIDTH = 800;
    private static final int VIEW_HEIGHT = 640;

    private double offsetX = 0;
    private double offsetY = 0;

    private double playerMapX = 25 * TILE_SIZE;
    private double playerMapY = 25 * TILE_SIZE;

    private int[][] map = new int[MAP_HEIGHT][MAP_WIDTH];
    private List<Monster> monsters = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(VIEW_WIDTH, VIEW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);

        try {
            loadMapFromFile("map.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Thêm một số quái vật vào game
        initializeMonsters();

        // Vẽ bản đồ, quái vật và nhân vật
        drawMap(gc);
        drawMonsters(gc);
        drawPlayer(gc);

        scene.setOnKeyPressed(this::handleMovement);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Top-Down Game with Monsters");
        primaryStage.show();
    }

    private void loadMapFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\MyPC\\IdeaProjects\\ITGame\\src\\main\\java\\com\\game\\itgame\\test\\map"))) {
            for (int row = 0; row < MAP_HEIGHT; row++) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] values = line.split(" ");
                for (int col = 0; col < MAP_WIDTH; col++) {
                    map[row][col] = Integer.parseInt(values[col]);
                }
            }
        }
    }

    private void initializeMonsters() {
        // Thêm một số quái vật vào danh sách
        monsters.add(new Monster(5 * TILE_SIZE, 5 * TILE_SIZE));
        monsters.add(new Monster(10 * TILE_SIZE, 15 * TILE_SIZE));
        monsters.add(new Monster(30 * TILE_SIZE, 40 * TILE_SIZE));
    }

    private void drawMap(GraphicsContext gc) {
        gc.clearRect(0, 0, VIEW_WIDTH, VIEW_HEIGHT);

        for (int row = 0; row < MAP_HEIGHT; row++) {
            for (int col = 0; col < MAP_WIDTH; col++) {
                double x = col * TILE_SIZE - offsetX;
                double y = row * TILE_SIZE - offsetY;

                if (x + TILE_SIZE > 0 && x < VIEW_WIDTH && y + TILE_SIZE > 0 && y < VIEW_HEIGHT) {
                    if (map[row][col] == 1) {
                        gc.setFill(Color.DARKGRAY);
                        gc.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                    } else {
                        gc.setFill(Color.LIGHTGRAY);
                        gc.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                    }
                    gc.setStroke(Color.GRAY);
                    gc.strokeRect(x, y, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    private void drawMonsters(GraphicsContext gc) {
        gc.setFill(Color.RED);
        for (Monster monster : monsters) {
            double x = monster.getX() - offsetX;
            double y = monster.getY() - offsetY;
            if (x + Monster.MONSTER_SIZE > 0 && x < VIEW_WIDTH && y + Monster.MONSTER_SIZE > 0 && y < VIEW_HEIGHT) {
                gc.fillRect(x, y, Monster.MONSTER_SIZE, Monster.MONSTER_SIZE);
            }
        }
    }

    private void drawPlayer(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect((VIEW_WIDTH - PLAYER_WIDTH) / 2, (VIEW_HEIGHT - PLAYER_HEIGHT) / 2, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    private void handleMovement(KeyEvent event) {
        double moveSpeed = 10;
        double newPlayerMapX = playerMapX;
        double newPlayerMapY = playerMapY;

        switch (event.getCode()) {
            case W -> newPlayerMapY -= moveSpeed;
            case S -> newPlayerMapY += moveSpeed;
            case A -> newPlayerMapX -= moveSpeed;
            case D -> newPlayerMapX += moveSpeed;
        }

        if (!isCollision(newPlayerMapX, newPlayerMapY)) {
            playerMapX = newPlayerMapX;
            playerMapY = newPlayerMapY;
            offsetX = playerMapX - VIEW_WIDTH / 2;
            offsetY = playerMapY - VIEW_HEIGHT / 2;

            offsetX = Math.max(0, Math.min(offsetX, MAP_WIDTH * TILE_SIZE - VIEW_WIDTH));
            offsetY = Math.max(0, Math.min(offsetY, MAP_HEIGHT * TILE_SIZE - VIEW_HEIGHT));

            for (Monster monster : monsters) {
                monster.moveTowards(playerMapX, playerMapY); // Quái vật di chuyển hướng tới nhân vật
            }

            GraphicsContext gc = ((Canvas) ((Pane) ((Scene) event.getSource()).getRoot()).getChildren().get(0)).getGraphicsContext2D();
            drawMap(gc);
            drawMonsters(gc);
            drawPlayer(gc);
        }
    }

    private boolean isCollision(double newPlayerMapX, double newPlayerMapY) {
        int tileX = (int) (newPlayerMapX / TILE_SIZE);
        int tileY = (int) (newPlayerMapY / TILE_SIZE);

        return tileX < 0 || tileY < 0 || tileX >= MAP_WIDTH || tileY >= MAP_HEIGHT || map[tileY][tileX] == 1;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
