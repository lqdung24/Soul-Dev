package com.game.itgame.weapon.sword;

import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.entity.player.Player;
import com.game.itgame.weapon.Weapon;
import javafx.scene.canvas.GraphicsContext;
import java.util.Iterator;
import java.util.List;

public abstract class SwordAttack extends SwordRender implements Weapon {
    private boolean onAttack = false;
    private boolean isCoolDown = false;
    private double timer = 0, coolDownTimer = 0;
    Iterator<EnemyRender> iterator;
    private final double coolDownTime = 400;

    @Override
    public void attack(GraphicsContext ctx, double deltaTime, Player player, List<EnemyRender> enemies, double angle) {
        timer += deltaTime;
        coolDownTimer += deltaTime;
       if (!onAttack) {
           ctx.getCanvas().setOnMouseClicked(e -> {
               onAttack = true;
           });
       } else if (!isCoolDown) {
           iterator = enemies.iterator();
           while (iterator.hasNext()) {
               EnemyRender enemy = iterator.next();
               double angleToEnemy = Math.toDegrees(Math.atan2(player.getX() + 15  - enemy.getX(), enemy.getY() - 20 - player.getY()) + Math.PI / 2);
               double distance = Math.sqrt(Math.pow(player.getX() - enemy.getX(), 2) + Math.pow(player.getY() - enemy.getY(), 2));

               if ((angleToEnemy < angle + 50 && angleToEnemy > angle - 50 && distance < 50) || distance <= 20 ) {
                   enemy.Hp -= 1;
                   System.out.println(enemy.Hp);
                   if(enemy.Hp <= 0){
                       iterator.remove();
                   }
               }
           }

           if (timer > 30) {
               if (swordIndex >= swordImageLength) {
                   swordIndex = 0;
                   onAttack = false;
                   isCoolDown = true;
               } else {
                   swordIndex++;
               }
               timer = 0;
           }
       }

        if (coolDownTimer >= coolDownTime) {
            coolDownTimer = 0;
            isCoolDown = false;
        }
    }
}
