package com.game.itgame.map;

import com.game.itgame.entity.player.Player;
import com.game.itgame.eventHandle.KeyHandle;

public abstract class MapMove extends MapMatrix implements Map{
    protected double x = 0;
    protected double y = 0;

    public void mapMove(Player player, KeyHandle key) {
        double velocityX = 0;
        double velocityY = 0;

        if (key.up) {
            velocityY = -5;
        }
        if (key.down) {
            velocityY = 5;
        }
        if (key.left) {
            velocityX = -5;
        }
        if (key.right) {
            velocityX = 5;
        }

        double sqrt = Math.sqrt(velocityX * velocityX + velocityY * velocityY);
        if (sqrt == 0) {
            return;
        }
        x += velocityX * player.getVerticalSpeed() / sqrt;
        y += velocityY * player.getVerticalSpeed() / sqrt;
    }
}
