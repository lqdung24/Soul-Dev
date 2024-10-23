package com.game.itgame.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComplexMapGameWithMovingRectangle extends Application {

    private int[][] map;
    private final int TILE_SIZE = 40; // Kích thước của mỗi ô
    private Rectangle player; // Hình chữ nhật đại diện cho người chơi

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        try {
            loadMapFromFile("input.txt"); // Đọc dữ liệu từ file
            drawMap(pane);
            createPlayer(pane); // Tạo hình chữ nhật cho người chơi
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(pane, map[0].length * TILE_SIZE, map.length * TILE_SIZE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Complex Map Game With Moving Rectangle");
        primaryStage.show();

        // Thêm sự kiện bàn phím để di chuyển hình chữ nhật
        scene.setOnKeyPressed(event -> handleKeyPress(event, pane));
    }

    // Đọc bản đồ từ file
    private void loadMapFromFile(String fileName) throws IOException {
        List<int[]> mapList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(
                "C:\\Users\\MyPC\\IdeaProjects\\ITGame\\src\\main\\java\\com\\game\\itgame\\map\\map"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(" ");
            int[] row = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                row[i] = Integer.parseInt(tokens[i]);
            }
            mapList.add(row);
        }
        reader.close();

        // Chuyển từ List<int[]> sang int[][]
        map = new int[mapList.size()][];
        map = mapList.toArray(map);
    }

    // Vẽ bản đồ
    private void drawMap(Pane pane) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                Rectangle tile = new Rectangle(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                switch (map[row][col]) {
                    case 0: // Tường
                        tile.setFill(Color.GRAY);
                        break;
                    case 1: // Đường đi
                        tile.setFill(Color.LIGHTGREEN);
                        break;
                    case 2: // Nước
                        tile.setFill(Color.BLUE);
                        break;
                    default:
                        tile.setFill(Color.WHITE); // Ô không xác định
                        break;
                }
                tile.setStroke(Color.BLACK); // Viền của ô
                pane.getChildren().add(tile);
            }
        }
    }

    // Tạo hình chữ nhật cho người chơi
    private void createPlayer(Pane pane) {
        player = new Rectangle(TILE_SIZE, TILE_SIZE, TILE_SIZE - 5, TILE_SIZE - 5); // Kích thước nhỏ hơn một chút
        player.setFill(Color.RED); // Màu sắc của hình chữ nhật
        player.setX(TILE_SIZE); // Vị trí khởi đầu
        player.setY(TILE_SIZE);
        pane.getChildren().add(player);
    }

    // Xử lý sự kiện bàn phím để di chuyển hình chữ nhật
    private void handleKeyPress(KeyEvent event, Pane pane) {
        double newX = player.getX();
        double newY = player.getY();

        switch (event.getCode()) {
            case UP:
                newY -= TILE_SIZE; // Di chuyển lên
                break;
            case DOWN:
                newY += TILE_SIZE; // Di chuyển xuống
                break;
            case LEFT:
                newX -= TILE_SIZE; // Di chuyển sang trái
                break;
            case RIGHT:
                newX += TILE_SIZE; // Di chuyển sang phải
                break;
            default:
                break;
        }

        // Kiểm tra va chạm với bản đồ
        if (isMoveValid(newX, newY)) {
            player.setX(newX);
            player.setY(newY);
        }
    }

    // Kiểm tra tính hợp lệ của việc di chuyển
    private boolean isMoveValid(double newX, double newY) {
        int col = (int) (newX / TILE_SIZE);
        int row = (int) (newY / TILE_SIZE);

        // Kiểm tra xem vị trí mới có nằm trong giới hạn của bản đồ hay không
        if (row < 0 || row >= map.length || col < 0 || col >= map[row].length) {
            return false; // Nằm ngoài giới hạn
        }

        // Kiểm tra loại ô tại vị trí mới
        return map[row][col] != 0; // 0 là tường, không cho phép di chuyển vào đó
    }

    public static void main(String[] args) {
        launch(args);
    }
}
