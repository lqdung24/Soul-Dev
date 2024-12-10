package com.game.itgame.entity.player;

import com.game.itgame.entity.EntityRender;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Objects;

public class ManaBar{
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected double imageWidth;
    protected double imageHeight;
    protected GraphicsContext ctx;
    protected Image image;
    Player entityRender;

    public ManaBar(GraphicsContext ctx, EntityRender entityRender){
        this.ctx = ctx;
        x = 30;
        y = 70;
        height = 40;
        width = height*10/3;
        imageWidth = 2400;
        imageHeight = 720;
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/manabar.png")));
        ctx.drawImage(image, x, y);
        this.entityRender = (Player) entityRender;
    }

    public void draw(double deltaTime) {
        ctx.drawImage(image, (3-entityRender.getMana()) * imageWidth, 0,
                imageWidth, imageHeight, x, y, width, height);
    }
}
