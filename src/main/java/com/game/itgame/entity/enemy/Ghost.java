package com.game.itgame.entity.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Objects;

public class Ghost extends EnemyRender {

    public Ghost(double x, double y, GraphicsContext ctx) {
        super(x, y, ctx);
        this.width = 30;
        this.height = 30;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/idle_0.png")));
        this.imageWidth = 32;
        this.imageHeight = 32;
        this.frameLength = 0;
        this.frameStateIndex = 0;
        this.verticalSpeed = 2;
    }
}
