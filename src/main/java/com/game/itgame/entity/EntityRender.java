package com.game.itgame.entity;

import com.game.itgame.util.Hitbox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EntityRender implements EntityInterface {
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected double imageWidth;
    protected double imageHeight;
    protected GraphicsContext ctx;
    protected Image image;
    protected double time = 0;
    protected double damage;
    public double Hp;
    public Hitbox hitbox;
    protected double verticalSpeed;
    protected int frameStateIndex;
    protected int frameLength;
    protected int frameIndex = 0;

    public EntityRender() {}
    public EntityRender(double x, double y, GraphicsContext ctx) {
        this.x = x;
        this.y = y;
        this.ctx = ctx;
    }

    @Override
    public void update(double deltaTime) {
        draw(deltaTime);
    }

    @Override
    public void draw(double deltaTime) {
        ctx.drawImage(image, frameIndex * imageWidth, frameStateIndex * imageHeight, imageWidth, imageHeight, x, y, width, height);

//        Chỉnh thơi gian chuyển frame
        if (time > 150) {
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

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public double getVerticalSpeed() {
        return verticalSpeed;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public double getHp() {
        return Hp;
    }

    public Image getImage() {
        return image;
    }

    public GraphicsContext getCtx() {
        return ctx;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
