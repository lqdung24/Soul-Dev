package com.game.itgame.controller;

import com.game.itgame.main.MainApp;
import javafx.scene.Scene;

public class GameRunnable implements Runnable {
    final int FPS = 60;
    Scene scene;
    MainApp mainApp;
    int gameState = 1;
    final int PAUSE = 0, PLAY = 1;

    public GameRunnable(Scene scene, MainApp mainApp) {
        this.scene = scene;
        this.mainApp = mainApp;
    }

    @Override
    public void run() {
        double interval = (double) 1000 / FPS;
        double delta = 0;
        long currentTime;
        long lastTime = System.currentTimeMillis();
        int count = 0;

        while (true) {
            currentTime = System.currentTimeMillis();
            delta += (currentTime - lastTime) / interval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;

            }
        }
    }

    private void update() {
        mainApp.player.update();
    }

    private void repaint() {
        mainApp.map.draw();
        mainApp.player.draw();
    }

}
