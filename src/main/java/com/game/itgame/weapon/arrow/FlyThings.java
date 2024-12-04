package com.game.itgame.weapon.arrow;

import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.eventHandle.EnemyHandle;
import com.game.itgame.map.MapMove;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Iterator;

public abstract class FlyThings {
    protected double arrowX;
    protected double arrowY;
    protected Image arrowImage;
    protected double arrowAngle;
    protected double speed = 15;
    protected Iterator<EnemyRender> iterator;
    protected double offsetAngle = 0;
    protected double height, width;
    protected boolean isAttacked = false;
    protected double mapX, mapY;

    public void move(MapMove map) {
//        if(EnemyHandle.collisionMap(arrowX, arrowY, width, height)) {
//            isAttacked = true;
//            return;
//        }

        arrowX += speed * Math.cos(Math.toRadians(arrowAngle)) - map.getOffsetX();
        arrowY += speed * Math.sin(Math.toRadians(arrowAngle)) - map.getOffsetY();
    }

    public void render(GraphicsContext ctx, MapMove map) {
        move(map);
        ctx.save();
        ctx.translate(arrowX + 15, arrowY + 20);
        ctx.rotate(offsetAngle + arrowAngle);
        ctx.drawImage(arrowImage, 0, 0, 720, 720, 0, 0, height, width);
        ctx.restore();

        attack();
    }

    public abstract void attack();

    public boolean getIsAttacked() {
        return Math.sqrt(Math.pow(arrowX, 2) + Math.pow(arrowY, 2)) >= 30*100 || isAttacked;
    }
}
