package com.game.itgame.weapon.arrow;

import com.game.itgame.entity.Hitbox;
import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.map.MapMove;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Iterator;
import java.util.Map;

public abstract class FlyThings {
    protected double arrowX;
    protected double arrowY;
    protected Image arrowImage;
    protected double arrowAngle;
    protected double speed;
    protected Iterator<EnemyRender> iterator;
    protected double offsetAngle = 0;
    protected double height, width;
    protected boolean isAttacked = false;
    protected Hitbox hitbox;
    protected double offsetX, offsetY;
    public void move(MapMove map) {
        arrowX += speed * Math.cos(Math.toRadians(arrowAngle)) - MapMove.offsetX;
        arrowY += speed * Math.sin(Math.toRadians(arrowAngle)) - MapMove.offsetY;
    }

    public void render(GraphicsContext ctx, MapMove map) {
        move(map);
        hitbox.update(arrowX + offsetX, arrowY + offsetY);
        hitbox.draw(ctx);
        ctx.save();
        ctx.translate(arrowX + 15, arrowY + 20);
        ctx.rotate(offsetAngle + arrowAngle);
        ctx.drawImage(arrowImage, 0, 0, 720, 720, 0, 0, height, width);
        ctx.restore();
        attack();
    }

    public abstract void attack();

    public boolean getIsAttacked() {
        return Math.sqrt(Math.pow(arrowX, 2) + Math.pow(arrowY, 2)) >= 30*1000 || isAttacked;
    }
}
