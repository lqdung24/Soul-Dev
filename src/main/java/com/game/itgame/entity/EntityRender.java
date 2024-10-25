package com.game.itgame.entity;

import com.game.itgame.eventHandle.KeyHandle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EntityRender extends EntityMove implements Entity {
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected double imageWidth;
    protected double imageHeight;
    protected GraphicsContext ctx;
    protected Image image;
    private double time = 0;

    public EntityRender(double x, double y, GraphicsContext ctx) {
        this.x = x;
        this.y = y;
        this.ctx = ctx;
    }

    @Override
    public void update(double deltaTime, KeyHandle key) {
        move(key);
        draw(deltaTime);
    }

    @Override
    public void draw(double deltaTime) {
        ctx.drawImage(image, frameIndex * imageWidth, frameStateIndex * imageHeight, imageWidth, imageHeight, x, y, width, height);

//        Chỉnh thơi gian chuyển frame
        if (time > 300) {
            if (frameIndex >= frameLength) {
                frameIndex = 0;
            } else {
                frameIndex++;
                time = 0;
            }
        } else {
            time += deltaTime;
        }
    }
}
