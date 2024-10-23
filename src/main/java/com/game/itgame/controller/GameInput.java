package com.game.itgame.controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class GameInput {
    public boolean up, down, right, left;

    // Constructor mặc định
    public GameInput() {
        up = down = right = left = false;
    }

    // Constructor với tham số Scene
    public GameInput(Scene scene) {
        // Thêm phím vào set khi được nhấn
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case W: // Di chuyển lên
                    up = true;
                    break;
                case S: // Di chuyển xuống
                    down = true;
                    break;
                case A: // Di chuyển trái
                    left = true;
                    break;
                case D: // Di chuyển phải
                    right = true;
                    break;
                default:
                    break;
            }
        });

        // Xóa phím khỏi set khi nhả ra
        scene.setOnKeyReleased((KeyEvent event) -> {
            switch (event.getCode()) {
                case W: // Ngừng di chuyển lên
                    up = false;
                    break;
                case S: // Ngừng di chuyển xuống
                    down = false;
                    break;
                case A: // Ngừng di chuyển trái
                    left = false;
                    break;
                case D: // Ngừng di chuyển phải
                    right = false;
                    break;
                default:
                    break;
            }
        });
    }
}
