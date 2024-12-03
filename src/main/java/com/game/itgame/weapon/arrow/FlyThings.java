package com.game.itgame.weapon.arrow;

import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.entity.player.Player;
import com.game.itgame.map.MapMove;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Iterator;
import java.util.List;

public abstract class FlyThings {
    protected double arrowX;
    protected double arrowY;
    protected Image arrowImage;
    protected double arrowAngle;
    private double speed = 15;
    protected Iterator<EnemyRender> iterator;
    protected boolean isAttacked = false;
    protected double offsetAngle = 0;

    public void move(MapMove map) {
        arrowX += speed * Math.cos(Math.toRadians(arrowAngle)) - map.getOffsetX();
        arrowY += speed * Math.sin(Math.toRadians(arrowAngle)) - map.getOffsetY();
    }

    public abstract void render(GraphicsContext ctx, List<EnemyRender> enemies, MapMove map);

    public abstract void attack(List<EnemyRender> enemies);

    public boolean getIsAttacked() {
        return isAttacked;
    }
}
