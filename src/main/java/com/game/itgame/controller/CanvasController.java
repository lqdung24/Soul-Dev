package com.game.itgame.controller;

import com.game.itgame.entity.player.Player;
import com.game.itgame.weapon.sword.Sword;
import com.game.itgame.eventHandle.KeyHandle;
import com.game.itgame.map.MapRender;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class CanvasController {
    private GraphicsContext ctx;
    private Player player;
    private Sword sword;
    private MapRender map;
    private KeyHandle key;
    @FXML
    private Canvas canvas;


    public void update(Scene scene) {
        ctx = canvas.getGraphicsContext2D();
        player = new Player(canvas.getWidth() / 2 - 15, canvas.getHeight() / 2 - 15, ctx);
        map = new MapRender();
        sword = new Sword();
        key = new KeyHandle(scene);

        AnimationTimer animation = new AnimationTimer() {
            private long lastTime = 0;
            @Override
            public void handle(long now) {
                double deltaTime = (now - lastTime);
                lastTime = now;
                ctx.setFill(javafx.scene.paint.Color.BLACK);
                ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                map.mapRender(ctx, player, key);
                player.update(deltaTime, key);
                sword.draw(ctx, player, deltaTime);
            }
        };

        animation.start();
    }
}