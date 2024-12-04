package com.game.itgame.weapon.arrow;

import com.game.itgame.entity.enemy.EnemyRender;
import java.util.List;

public abstract class ArrowRender extends FlyThings {
    protected static List<EnemyRender> enemyList;

    @Override
    public void attack() {
        iterator = enemyList.iterator();

        while (iterator.hasNext()) {
            EnemyRender enemy = iterator.next();
            double distance = Math.sqrt(Math.pow(arrowX - enemy.getX() - enemy.getWidth()/2, 2)
                                        + Math.pow(arrowY - enemy.getY() - enemy.getHeight()/2, 2));
//            double distance = Math.sqrt(Math.pow(arrowX - enemy.getX(), 2)
//                                      + Math.pow(arrowY - enemy.getY(), 2));
            if (distance <= 50) {
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
