package com.game.itgame.weapon.arrow;

import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.entity.player.Player;
import com.game.itgame.map.MapMove;
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
    private double offsetAngle = -45;
    public void render(GraphicsContext ctx, List<EnemyRender> enemies, MapMove map) {
        move(map);

        ctx.save();
        ctx.translate(arrowX + 15, arrowY + 20);
        ctx.rotate(offsetAngle + arrowAngle);
        ctx.drawImage(arrowImage, 0, 0, 720, 720, 0, 0, 30, 30);
        ctx.restore();

        attack(enemies);
    }

    public void move(MapMove map) {
        arrowX += speed * Math.cos(Math.toRadians(arrowAngle)) - map.getOffsetX();
        arrowY += speed * Math.sin(Math.toRadians(arrowAngle)) - map.getOffsetY();
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

    public boolean getIsAttacked() {
        return isAttacked;
    }
}
