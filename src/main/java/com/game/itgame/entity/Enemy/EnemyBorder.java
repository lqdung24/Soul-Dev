package com.game.itgame.entity.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class EnemyBorder extends Rectangle {
    EnemyRender entity;

    public EnemyBorder(EnemyRender entity) {
        this.entity = entity;
        update();
        setWidth(entity.width);
        setHeight(entity.height);
    }

    public void update() {
        setX(entity.x);
        setY(entity.y);
    }

    public void draw(GraphicsContext ctx) {
        ctx.setGlobalAlpha(0.5);
        ctx.fillRect(getX(), getY(), getWidth(), getHeight());
        ctx.setGlobalAlpha(1);
    }
}
