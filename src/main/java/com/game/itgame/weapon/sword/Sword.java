package com.game.itgame.weapon.sword;

import com.game.itgame.entity.player.Player;
import com.game.itgame.weapon.WeaponAttack;
import com.game.itgame.weapon.WeaponRender;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Sword extends WeaponAttack {
    private Image swordImage = new Image(Objects.requireNonNull(Sword.class.getResourceAsStream("/images/weapon/sword.png")));
    private int swordImageLength = 3;

    public Sword() {
        weaponImageLength = swordImageLength;
        weaponImage = swordImage;
    }

    @Override
    public void reload() {
        // reload logic
    }

    @Override
    public void changeWeapon() {
        // change weapon logic
    }

    @Override
    public void useWeapon() {
        // use weapon logic
    }
}
