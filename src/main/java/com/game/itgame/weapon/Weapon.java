package com.game.itgame.weapon;

import com.game.itgame.entity.player.Player;
import javafx.scene.canvas.GraphicsContext;

public interface Weapon {
    void draw(GraphicsContext ctx, Player player, double deltaTime);
    void attack(GraphicsContext ctx, double deltaTime);
    void reload();
    void changeWeapon();
    void useWeapon();
}
