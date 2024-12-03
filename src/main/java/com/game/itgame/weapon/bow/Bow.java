package com.game.itgame.weapon.bow;

import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.entity.player.Player;
import com.game.itgame.map.MapMove;
import com.game.itgame.weapon.arrow.Arrow;
import com.game.itgame.weapon.sword.Sword;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Objects;

public class Bow extends BowAttack {

    public Bow(MapMove map, List<EnemyRender> enemies) {
        bowImage = new Image(Objects.requireNonNull(Sword.class.getResourceAsStream("/images/weapon/bow.png")));
        bowImageLength = 3;
        this.map = map;
        this.enemies = enemies;
        Arrow.setEnemyList(enemies);
    }

    @Override
    public void draw(GraphicsContext ctx, Player player, double deltaTime, List<com.game.itgame.entity.enemy.EnemyRender> enemies) {
        // draw logic
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
