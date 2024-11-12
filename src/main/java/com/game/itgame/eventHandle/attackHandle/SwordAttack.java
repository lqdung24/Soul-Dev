package com.game.itgame.eventHandle.attackHandle;

import com.game.itgame.entity.enemy.Enemy;
import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.entity.player.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;

public class SwordAttack {
    public Arc atkZone;
    double radius;
    double angle;
    double damage;
    double offSetX = -35;
    double offSetY = -35;
    EnemyRender enemy;
    Shape intersect;
    double time = 0;

    public SwordAttack(double radius, double angle, double damage) {
        this.angle = angle;
        this.damage = damage;
        this.radius = radius;
        atkZone = new Arc(585 + 0, 285 - 15, radius, radius, -angle/2, angle);
        atkZone.setType(ArcType.ROUND);
    }


    //EnemyRender enemy
    public void checkDamage(GraphicsContext ctx ,double deltaTime) {
        ctx.fillArc(585 + 0, 285 - 15, radius, radius, -angle/2, angle, ArcType.ROUND);
        intersect = Shape.intersect(atkZone, enemy.border);
        if (intersect.getBoundsInLocal().getWidth() == 0 || intersect.getBoundsInLocal().getHeight() == 0) {
            return;
        }
        time += deltaTime;
//        if(time > 200){
//            System.out.println("atkZone Bounds: " + atkZone.getBoundsInParent());
//            System.out.println("enemy.border Bounds: " + enemy.border.getBoundsInParent());
//        }
        if(time > 200){
            //enemy.Hp -= damage;
            time -= 200;
            System.out.println("hehe " + enemy.Hp);
        }

    }

    void checkDamage(Player player) {

    }
    public void getEnemy(EnemyRender enemy){
        this.enemy = enemy;
    }
}
