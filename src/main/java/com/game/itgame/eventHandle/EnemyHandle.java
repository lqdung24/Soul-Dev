package com.game.itgame.eventHandle;

import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.entity.enemy.Mob1;
import com.game.itgame.entity.enemy.Mob2;
import com.game.itgame.entity.player.Player;
import com.game.itgame.map.MapMove;
import com.game.itgame.weapon.Aim;
import com.game.itgame.weapon.arrow.Arrow;

import java.util.List;

public class EnemyHandle { // điều khiển enemy tiến lại gần player
    Player player;
    private int moveRadius = 32*10;
    private MapMove map;
    private double dx, dy;
    Aim aim;

    public EnemyHandle(Player p, MapMove map, Aim aim){
        player = p;
        this.map = map;
        this.aim = aim;
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
            enemy.collisionTimer = 0;
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

    public boolean checkDamage(EnemyRender enemy){
        dx = player.getX() - enemy.x;
        dy = player.getY() - enemy.y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < 50;
    }
    public void moveRandom(EnemyRender enemy){
        double angle = enemy.attack1.angle;
        double dx = enemy.getVerticalSpeed() * Math.cos(angle);
        double dy = enemy.getVerticalSpeed() * Math.sin(angle);
        if (enemy instanceof Mob1) {
            if(collisionMap(enemy, enemy.mapX + dx, enemy.mapY + dy)){  // nếu va chạm thì thôi
                enemy.moveRight = false;
                enemy.moveLeft = false;
                return;
            }
        }

        enemy.x += dx;
        enemy.y += dy;
        enemy.mapX += dx;
        enemy.mapY += dy;
    }
    public void bulletAttack(EnemyRender enemy, List<Arrow> arrowList){
        double angle = Math.toDegrees(Math.atan2(player.getY() - enemy.y, player.getX() - enemy.x));
        Arrow arrow = new Arrow(enemy.x, enemy.y, angle);
        arrowList.add(arrow);
    }
    public void reduceHp(int hp){
        player.Hp -= hp;
    }
}
