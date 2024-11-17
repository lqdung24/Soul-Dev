package com.game.itgame.eventHandle;

import com.game.itgame.entity.Enemy.EnemyRender;
import com.game.itgame.entity.player.Player;

public class EnemyHandle { // điều khiển enemy tiến lại gần player
    Player player;
    private int moveRadius = 32*10;

    public EnemyHandle(Player p){
        player = p;
    }
    public void moveEnemy(EnemyRender enemy){
        double dx = player.getX() - enemy.x;
        double dy = player.getY() - enemy.y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance != 0 && distance <= moveRadius) {
            enemy.x += enemy.getSpeed() * (dx / distance);
            enemy.y += enemy.getSpeed() * (dy / distance);
        }
    }
    public void collisionPlayer(EnemyRender enemy, double deltatime){

        if (player.getX() + player.getWidth() < enemy.x || enemy.x + enemy.getWidth() < player.getX()
                || player.getY() + player.getHeight() < enemy.y || enemy.y + enemy.getHeight() < player.getY()) {
            return;
        }
        enemy.collisionTimer += deltatime;

        if(enemy.collisionTimer >= 1000){
            player.Hp -= enemy.getCollisionDamage();
            System.out.println("ops");
            enemy.collisionTimer -= 1000;
        }

    }
}
