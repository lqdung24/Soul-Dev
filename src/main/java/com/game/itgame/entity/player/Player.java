package com.game.itgame.entity.player;

import com.game.itgame.entity.EntityRender;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Player extends EntityRender {

    public Player(double x, double y, GraphicsContext ctx) {
        super(x, y, ctx);
        this.width = 30;
        this.height = 30;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/player.png")));
        this.imageWidth = 575;
        this.imageHeight = 523;
        this.frameLength = 6;
        this.frameStateIndex = 0;
        this.verticalSpeed = 10;

        this.damage = 2;
        this.Hp = 10;
    }
}
