package com.game.itgame.weapon.arrow;

import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.entity.player.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Iterator;
import java.util.List;

public abstract class ArrowRender {
    protected double arrowX;
    protected double arrowY;
    protected Image arrowImage;
    protected double arrowAngle;
    private double speed = 5.0;
    private Iterator<EnemyRender> iterator;
    private boolean isAttacked = false;

    public void render(GraphicsContext ctx, List<EnemyRender> enemies) {
        move();

        ctx.save();
        ctx.translate(arrowX + 15, arrowY + 20);
        ctx.rotate(-45.0 + arrowAngle);
        ctx.drawImage(arrowImage, 0, 0, 720, 720, 0, 0, 30, 30);
        ctx.restore();

        attack(enemies);
    }

    public void move() {
        arrowX += speed * Math.cos(Math.toRadians(arrowAngle));
        arrowY += speed * Math.sin(Math.toRadians(arrowAngle));
    }

    public void attack(List<EnemyRender> enemies) {
        iterator = enemies.iterator();

        while (iterator.hasNext()) {
            EnemyRender enemy = iterator.next();
            double distance = Math.sqrt(Math.pow(arrowX - enemy.getX(), 2) + Math.pow(arrowY - enemy.getY(), 2));

            if (distance <= 20) {
                enemy.Hp -= 1;
                isAttacked = true;
                System.out.println(enemy.Hp);

                if (enemy.Hp <= 0) {
                    iterator.remove();
                }
            }
        }
    }

    public double getArrowX() {
        return arrowX;
    }

    public double getArrowY() {
        return arrowY;
    }

    public boolean getIsAttacked() {
        return isAttacked;
    }
}
