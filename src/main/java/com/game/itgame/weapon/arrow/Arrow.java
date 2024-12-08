package com.game.itgame.weapon.arrow;

import com.game.itgame.controller.CanvasController;
import com.game.itgame.util.Hitbox;
import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.weapon.sword.Sword;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

import java.util.Objects;

public class Arrow extends FlyThings {
    public Arrow(double x, double y, double angle) {
        arrowX = x;
        arrowY = y;
        arrowAngle = angle;
        offsetAngle = 0;
        width = 30;
        height = 30;
        arrowImage = new Image(Objects.requireNonNull(
                Sword.class.getResourceAsStream("/images/weapon/arrow22.png")));
        speed = 15;
        offsetX = 10;
        offsetY = 5;
        hitbox = new Hitbox(arrowX, arrowY, width, height-20);
    }
    @Override
    public void attack() {
        iterator = CanvasController.enemies.iterator();

        while (iterator.hasNext()) {
            EnemyRender enemy = iterator.next();

            if(Shape.intersect(hitbox, enemy.hitbox).getBoundsInLocal().getWidth() > 0){
                enemy.Hp -= 2;
                isAttacked = true;
                System.out.println(enemy.Hp);

                if (enemy.Hp <= 0) {
                    iterator.remove();
                }
            }
        }
    }

    @Override
    public void render(GraphicsContext ctx) {
        move();
        hitbox.update(arrowX + offsetX, arrowY + offsetY);
        hitbox.draw(ctx);
        ctx.save();
        ctx.translate(hitbox.getCenterX(), hitbox.getCenterY());
        ctx.rotate(offsetAngle + arrowAngle);
        ctx.drawImage(arrowImage, 0, 0, 720, 720, 0, 0, height, width);
        ctx.restore();
        attack();
    }
}
