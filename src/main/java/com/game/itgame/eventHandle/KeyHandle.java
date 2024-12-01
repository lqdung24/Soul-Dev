package com.game.itgame.eventHandle;

import javafx.scene.Scene;

public class KeyHandle {
    public boolean up, down, left, right, space, enter, firstWeapon = true;

    public KeyHandle(Scene scene) {
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
            }
        });
    }
}
