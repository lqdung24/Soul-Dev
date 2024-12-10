package com.game.itgame.weapon.sword;

import com.game.itgame.controller.CanvasController;
import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.entity.item.Chest;
import com.game.itgame.entity.player.Player;
import com.game.itgame.weapon.Weapon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Arc;

import java.util.Iterator;
import java.util.List;

public abstract class SwordAttack extends SwordRender implements Weapon {
    public boolean onAttack = false;
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
               double angleToEnemy = Math.toDegrees(Math.atan2(player.hitbox.getCenterX() + 15 - enemy.hitbox.getCenterX(), enemy.hitbox.getCenterY() - 20 - player.hitbox.getCenterY()) + Math.PI / 2);
               double distance = Math.sqrt(Math.pow(player.hitbox.getCenterX() - enemy.hitbox.getCenterX(), 2) + Math.pow(player.hitbox.getCenterY() - enemy.hitbox.getCenterY(), 2));

               if ((angleToEnemy < angle + 50 && angleToEnemy > angle - 50 && distance < 70) || distance <= 20 ) {
                   enemy.Hp -= 1;
                   System.out.println(enemy.Hp);
                   if(enemy.Hp <= 0){
                       iterator.remove();
                   }
               }
           }
           for(Chest chest : CanvasController.chestList){
               double angleToEnemy = Math.toDegrees(Math.atan2(player.hitbox.getCenterX() + 15 - chest.hitbox.getCenterX(), chest.hitbox.getCenterY() - 20 - player.hitbox.getCenterY()) + Math.PI / 2);
               double distance = Math.sqrt(Math.pow(player.hitbox.getCenterX() - chest.hitbox.getCenterX(), 2) + Math.pow(player.hitbox.getCenterY() - chest.hitbox.getCenterY(), 2));

               if ((angleToEnemy < angle + 50 && angleToEnemy > angle - 50 && distance < 80) || distance <= 20 ) {
                   chest.Hp = 0;
                   System.out.println(chest.Hp);
               }
           }

           if (timer > 40) {
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
