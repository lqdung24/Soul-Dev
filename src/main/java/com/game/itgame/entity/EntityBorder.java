package com.game.itgame.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class EntityBorder extends Rectangle{
    EntityRender entity;

    public EntityBorder(EntityRender entity){
        this.entity = entity;
        update();
        setWidth(entity.width);
        setHeight(entity.height);
    }
    public void update(){
        setX(entity.x);
        setY(entity.y);
    }
    public void draw(GraphicsContext ctx){
        ctx.setGlobalAlpha(0.5);
        ctx.fillRect(getX(), getY(), getWidth(), getHeight());
        ctx.setGlobalAlpha(1);
    }
}
