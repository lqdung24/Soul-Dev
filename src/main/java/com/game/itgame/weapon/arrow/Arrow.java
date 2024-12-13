package com.game.itgame.weapon.arrow;

import com.game.itgame.controller.CanvasController;
import com.game.itgame.eventHandle.EntityHandle;
import com.game.itgame.util.Hitbox;
import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.weapon.sword.Sword;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

import java.util.Objects;

public class Arrow extends FlyThings {
    public Arrow(double x, double y, double angle) {
        super();
        arrowX = x;
        arrowY = y;
        arrowAngle = angle;
        offsetAngle = 0;
        width = 35;
        height = 35;
        arrowImage = new Image(Objects.requireNonNull(
                Sword.class.getResourceAsStream("/images/weapon/arrow22.png")));
        speed = 15;
        offsetX = 10;
        offsetY = 5;
        hitbox = new Hitbox(0, 0, 10, 10);
    }

    @Override
    public void attack() {
        CanvasController.enemies.forEach(enemy -> {
            if(Shape.intersect(hitbox, enemy.hitbox).getBoundsInLocal().getWidth() > 0){
                EntityHandle.reduceEnemyHp(enemy,2);
                this.remove = true;
                System.out.println(enemy.Hp);
            }
        });
    }

    @Override
    public void render(GraphicsContext ctx, double deltaTime) {
        move();

        double hitboxX = arrowX - 5 + 20 * Math.cos(Math.toRadians(arrowAngle));
        double hitboxY = arrowY - 5 + 20 * Math.sin(Math.toRadians(arrowAngle));
        hitbox.update(hitboxX, hitboxY);
        hitbox.draw(ctx);

        ctx.save();
        ctx.translate(arrowX, arrowY);
        ctx.rotate(offsetAngle + arrowAngle);
        ctx.drawImage(arrowImage, 0, 0, 720, 720, -10, -5, height, width);
        ctx.restore();
        attack();

    }
}
