package com.game.itgame.weapon.arrow;

import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.map.MapMove;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public abstract class ArrowRender extends FlyThings {
    protected static List<EnemyRender> enemyList;

    @Override
    public void attack() {
        iterator = enemyList.iterator();

        while (iterator.hasNext()) {
            EnemyRender enemy = iterator.next();
            double distance = Math.sqrt(Math.pow(arrowX - enemy.getX(), 2) + Math.pow(arrowY - enemy.getY(), 2));

            if (distance <= 25) {
                enemy.Hp -= 2;
                isAttacked = true;
                System.out.println(enemy.Hp);

                if (enemy.Hp <= 0) {
                    iterator.remove();
                }
            }
        }
    }

    public static void setEnemyList(List<EnemyRender> enemyList) {
        ArrowRender.enemyList= enemyList;
    }
}
