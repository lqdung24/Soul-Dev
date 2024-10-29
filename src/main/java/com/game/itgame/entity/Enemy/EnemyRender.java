package com.game.itgame.entity.Enemy;

import com.game.itgame.eventHandle.EnemyHandle;
import com.game.itgame.map.MapMove;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EnemyRender extends EnemyMove implements Enemy {
    public double x;
    public double y;
    protected double width;
    protected double height;
    protected double imageWidth;
    protected double imageHeight;
    protected GraphicsContext ctx;
    protected Image image;
    private double time = 0;
    protected double damage;
    protected double collisionDamage = 1;

    public EnemyRender(double x, double y, GraphicsContext ctx) {
        this.x = x;
        this.y = y;
        this.ctx = ctx;
    }

    @Override
    public void update(double deltaTime, EnemyHandle key, MapMove map) {
        key.moveEnemy(this);
        //update vi tri tuong doi do nhan vat di chuyen
        this.x -= map.getOffsetX();
        this.y -= map.getOffsetY();

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

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
    public double getSpeed() {
        return verticalSpeed;
    }

}
