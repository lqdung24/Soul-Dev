package com.game.itgame.weapon;

import com.game.itgame.entity.Enemy.EnemyRender;
import com.game.itgame.entity.player.Player;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public abstract class WeaponAttack extends WeaponRender implements Weapon {
    private boolean onAttack = false;
    private boolean isCoundown = false;
    private double countDown = 400;
    private double timer = 0;

    @Override
    public void attack(GraphicsContext ctx, double deltaTime, Player player, List<EnemyRender> enemies, double angle) {

       if (!onAttack) {
           ctx.getCanvas().setOnMouseClicked(e -> {
               onAttack = true;
           });
       } else if (!isCoundown) {
           enemies.forEach(enemy -> {
               double angleToEnemy = Math.toDegrees(Math.atan2(player.getX() + 15  - enemy.getX(), enemy.getY() - 20 - player.getY()) + Math.PI / 2);
               double distance = Math.sqrt(Math.pow(player.getX() - enemy.getX(), 2) + Math.pow(player.getY() - enemy.getY(), 2));

                if ((angleToEnemy < angle + 50 && angleToEnemy > angle - 50 && distance < 50) || distance <= 20 ) {
                     enemies.remove(enemy);
                }
           });

           if (timer > 30) {
               if (weaponIndex >= weaponImageLength) {
                   weaponIndex = 0;
                   onAttack = false;
                   isCoundown = true;
               } else {
                   weaponIndex++;
               }
           }
       }

        timer += deltaTime;

        if (timer >= countDown) {
          timer = 0;
          isCoundown = false;
        }
    }
}
