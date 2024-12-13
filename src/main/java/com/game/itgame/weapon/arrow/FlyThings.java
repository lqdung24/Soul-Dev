package com.game.itgame.weapon.arrow;

import com.game.itgame.controller.CanvasController;
import com.game.itgame.util.Hitbox;
import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.map.MapMove;
import com.game.itgame.util.Timer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Iterator;

public abstract class FlyThings {
    protected double arrowX;
    protected double arrowY;
    protected Image arrowImage;
    protected double arrowAngle;
    protected double speed;
    protected double offsetAngle = 0;
    protected double height, width;
    protected Hitbox hitbox;
    protected double offsetX, offsetY;
    protected boolean remove;
    protected Timer timer;

    public FlyThings() {
        timer = new Timer(2500);
    }

    public void move() {
        arrowX += speed * Math.cos(Math.toRadians(arrowAngle)) - MapMove.offsetX;
        arrowY += speed * Math.sin(Math.toRadians(arrowAngle)) - MapMove.offsetY;
    }

    public void render(GraphicsContext ctx, double deltaTime) {
        move();

        double hitboxX = arrowX - 1;
        double hitboxY = arrowY - 0.5;

        timer.update(deltaTime);

        hitbox.update(hitboxX, hitboxY);
        hitbox.draw(ctx);
        remove = remove || timer.available;
        ctx.save();
        ctx.translate(arrowX, arrowY );
        ctx.drawImage(arrowImage, 0, 0, 720, 720, 0, 0, height, width);
        ctx.restore();
        attack();
    }

    public abstract void attack();

    public static void bulletDraw(GraphicsContext ctx, double deltaTime) {
        for (int i = 0; i< CanvasController.enemyBullets.size(); i++) {
            if(CanvasController.enemyBullets.get(i).remove){
                CanvasController.enemyBullets.remove(i);
            }else {
                CanvasController.enemyBullets.get(i).render(ctx, deltaTime);
            }
        }
    }

    public boolean getRemove() {
        return remove;
    }
}
