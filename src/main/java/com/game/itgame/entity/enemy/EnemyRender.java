package com.game.itgame.entity.enemy;

import com.game.itgame.eventHandle.EnemyHandle;
import com.game.itgame.eventHandle.Skill;
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
    protected double time = 0;
    protected double damage;
    protected double collisionDamage = 1;
    public double collisionTimer = 0;
    public double Hp;
    public double mapX, mapY;
    public Skill attack1;

    public EnemyRender(int x, int y, GraphicsContext ctx) {
        this.x = ctx.getCanvas().getWidth()/2-15-2*30 + x*30;
        this.y = ctx.getCanvas().getHeight()/2-15-2*30 + y*30;
        mapX = x*30;
        mapY = y*30;
        this.ctx = ctx;
    }

    @Override
    public void update(double deltaTime, EnemyHandle key, MapMove map) {
        //update vi tri tuong doi khi nhan vat di chuyen
        this.x -= map.getOffsetX();
        this.y -= map.getOffsetY();

        key.moveEnemy(this);
        move(key);
        key.collisionPlayer(this, deltaTime);
        //System.out.println((int)mapX/30 + " " + (int)mapY/30);
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
    public double getCollisionDamage() {
        return collisionDamage;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getVerticalSpeed(){
        return verticalSpeed;
    }
}
