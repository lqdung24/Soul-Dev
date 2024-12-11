package com.game.itgame.eventHandle;

import javafx.scene.Scene;
import javafx.scene.input.MouseButton;

public class KeyHandle {
    public static boolean up, down, left, right, space, enter, firstWeapon = true, one, two, three, esc;
    public static double mouseX, mouseY;
    public static double sceneX, sceneY;
    public static boolean rightMouse;

    public KeyHandle(Scene scene) {
        sceneX = scene.getX();
        sceneY = scene.getY();
        scene.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.SECONDARY) { // Kiểm tra chuột phải
                rightMouse = true;// Gán true khi nhấp chuột phải
            }
        });

        // Xử lý khi thả chuột phải
        scene.setOnMouseReleased(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                rightMouse = false;
            }
        });
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:
                    up = true;
                    break;
                case S:
                    down = true;
                    break;
                case A:
                    left = true;
                    break;
                case D:
                    right = true;
                    break;
                case SPACE:
                    space = true;
                    break;
                case ENTER:
                    enter = true;
                    break;
                case R:
                    firstWeapon = !firstWeapon;
                    break;
                case DIGIT1:
                    one = true;
                    break;
                case DIGIT2:
                    two = true;
                    break;
                case DIGIT3:
                    three = true;
                    break;
                case ESCAPE:
                    esc = true;
                    break;
            }
        });

        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case W:
                    up = false;
                    break;
                case S:
                    down = false;
                    break;
                case A:
                    left = false;
                    break;
                case D:
                    right = false;
                    break;
                case SPACE:
                    space = false;
                    break;
                case ENTER:
                    enter = false;
                    break;
                case DIGIT1:
                    one = false;
                    break;
                case DIGIT2:
                    two = false;
                    break;
                case DIGIT3:
                    three = false;
                    break;
            }
        });

        scene.setOnMouseMoved(e -> {
            mouseX = e.getX();
            mouseY = e.getY();
        });
    }
    public static boolean mouseLeft(){
        return mouseX <= sceneX/2;
    }
}
