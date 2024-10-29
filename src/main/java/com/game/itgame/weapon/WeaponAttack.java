package com.game.itgame.weapon;

import javafx.scene.canvas.GraphicsContext;

public abstract class WeaponAttack extends WeaponRender implements Weapon {
    private boolean onAttack = false;
    private double timer = 0;

    @Override
    public void attack(GraphicsContext ctx, double deltaTime) {

       if (!onAttack) {
           ctx.getCanvas().setOnMouseClicked(e -> {
               onAttack = true;
           });
       } else {
           if (timer > 200) {
               if (weaponIndex >= weaponImageLength) {
                   weaponIndex = 0;
                     onAttack = false;
               } else {
                   weaponIndex++;
               }
               timer = 0;
           } else {
               timer += deltaTime;
           }
       }
    }
}
