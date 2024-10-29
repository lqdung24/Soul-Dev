package com.game.itgame.eventHandle;

import com.game.itgame.entity.Enemy.EnemyRender;
import com.game.itgame.entity.player.Player;

public class EnemyHandle { // điều khiển enemy tiến lại gần player
    Player player;
    public EnemyHandle(Player p){
        player = p;
    }
    public void moveEnemy(EnemyRender enemy){
        double dx = player.getX() - enemy.x;
        double dy = player.getY() - enemy.y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance != 0) {
            enemy.x += enemy.getSpeed() * (dx / distance);
            enemy.y += enemy.getSpeed() * (dy / distance);
        }
    }
    public void collisionPlayer(){
        
    }
}
