package com.game.itgame.map;

import com.game.itgame.UI_things.UI;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map implements UI {
    Image map_image;
    public final int TILE_SIZE = 32; // sau sẽ dùng, hứa
    Canvas canvas;
    public int[][] map_matrix;
    final int MAX_ROW = 50, MAX_COL = 50;
    public int row, col;
    double WIDTH, HEIGHT;

    public Map(Canvas canvas) throws IOException {
        this.canvas = canvas;
        loadResource();
        row = map_matrix.length;
        col = map_matrix[0].length;
        draw();
    }

    // Đọc bản đồ từ file: load file map.txt và load ảnh bản đổ
    private void loadResource() throws IOException {
        List<int[]> mapList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\MyPC\\IdeaProjects\\ITGame\\src\\main\\java\\com\\game\\itgame\\map\\map"));
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

        map_matrix = new int[mapList.size()][];
        map_matrix = mapList.toArray(map_matrix);

        //load ảnh bản đồ
        map_image = new Image("C:\\Users\\MyPC\\IdeaProjects\\ITGame\\src\\main\\resources\\Image\\MapImg\\map.png");
    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        canvas.getGraphicsContext2D().drawImage(map_image, 0, 0, TILE_SIZE*col, TILE_SIZE*row);
    }
    // Vẽ bản đồ, muốn update chỉ cần sửa lại switch-case
//    private void drawMap() {
//        for (int row = 0; row < map.length; row++) {
//            for (int col = 0; col < map[row].length; col++) {
//                Rectangle tile = new Rectangle(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
//                switch (map[row][col]) {
//                    case 0: // Tường
//                        tile.setFill(Color.GRAY);
//                        break;
//                    case 1: // Đường đi
//                        tile.setFill(Color.LIGHTGREEN);
//                        break;
//                    case 2: // Nước
//                        tile.setFill(Color.BLUE);
//                        break;
//                    default:
//                        tile.setFill(Color.WHITE); // Ô không xác định
//                        break;
//                }
//                tile.setStroke(Color.BLACK); // Viền của ô
//                pane.getChildren().add(tile);
//            }
//        }
//    }
}
