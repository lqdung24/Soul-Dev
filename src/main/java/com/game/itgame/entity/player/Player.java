package com.game.itgame.entity.player;

import com.game.itgame.entity.EntityRender;
import com.game.itgame.eventHandle.KeyHandle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Player extends EntityRender {
    protected HealthBar bar;
    public Player(double x, double y, GraphicsContext ctx) {
        super(x, y, ctx);
        this.width = 50;
        this.height = 50;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/character3-left.png")));
        this.imageWidth = 720;
        this.imageHeight = 720;
        this.frameLength = 5;
        this.frameStateIndex = 0;
        this.verticalSpeed = 5;
        this.damage = 2;
        this.Hp = 10;
        this.bar = new HealthBar(ctx, this);
    }
    @Override
    public void update(double deltaTime, KeyHandle key) {
        move(key);
        draw(deltaTime);
        bar.draw(deltaTime);
    }
}
