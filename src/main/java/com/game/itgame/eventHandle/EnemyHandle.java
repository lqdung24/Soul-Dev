package com.game.itgame.eventHandle;

import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.entity.enemy.Mob1;
import com.game.itgame.entity.player.Player;
import com.game.itgame.map.MapMove;

public class EnemyHandle { // điều khiển enemy tiến lại gần player
    Player player;
    private int moveRadius = 32*10;
    private MapMove map;
    private double dx, dy;

    public EnemyHandle(Player p, MapMove map){
        player = p;
        this.map = map;
    }
    public void moveEnemy(EnemyRender enemy){
        dx = player.getX() - enemy.x;
        dy = player.getY() - enemy.y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        if(distance < 15 || distance > moveRadius){// nếu không nằm trong vùng thì khỏi xử lí
            return;
        }
        dx = enemy.getSpeed() * (dx / distance);
        dy = enemy.getSpeed() * (dy / distance);
        if(enemy instanceof Mob1){// nếu là mob1
            if(collisionMap(enemy, enemy.mapX + dx, enemy.mapY + dy)){// nếu va chạm thì thôi
                enemy.moveRight = false;
                enemy.moveLeft = false;
                System.out.println("o no");
                return;
            }
        }
        enemy.x += dx;
        enemy.y += dy;
        enemy.mapX += dx;
        enemy.mapY += dy;

        if(dx > 0){
            enemy.moveRight = true;
        }else if(dx < 0){
            enemy.moveLeft = true;
        }else{
            enemy.moveRight = false;
            enemy.moveLeft = false;
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
            //System.out.println("ops");
            enemy.collisionTimer -= 1000;
        }
    }
    public boolean collisionMap(EnemyRender enemy, double newX, double newY){
        int colTopLeft = (int) (newX / map.getMapFrameSize());
        int rowTopLeft = (int) (newY / map.getMapFrameSize());

        int colBottomRight = (int) ((newX + enemy.getWidth()) / map.getMapFrameSize());
        int rowBottomRight = (int) ((newY + enemy.getHeight()) / map.getMapFrameSize());

        if (rowTopLeft < 0 || rowBottomRight >= 50 || colTopLeft < 0 || colBottomRight >= 50) {
            return true;
        }
        return map.getValue(rowTopLeft, colTopLeft) != 0 ||
                map.getValue(rowTopLeft, colBottomRight) != 0 ||
                map.getValue(rowBottomRight, colTopLeft) != 0 ||
                map.getValue(rowBottomRight, colBottomRight) != 0;
    }
}
