package com.game.itgame.weapon;

import com.game.itgame.entity.Enemy.EnemyRender;
import com.game.itgame.entity.player.Player;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public interface Weapon {
    void draw(GraphicsContext ctx, Player player, double deltaTime, List<EnemyRender> enemies);
    void attack(GraphicsContext ctx, double deltaTime, Player player, List<EnemyRender> enemies, double angle);
    void reload();
    void changeWeapon();
    void useWeapon();
}
