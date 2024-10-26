package com.game.itgame.map;

import com.game.itgame.entity.player.Player;
import com.game.itgame.eventHandle.CollisionHandle;
import com.game.itgame.eventHandle.KeyHandle;

public abstract class MapMove extends MapMatrix implements Map{
    protected double x = 0;
    protected double y = 0;
    protected double mapFrameSize = 30;
    protected int startX = 2;
    protected int startY = 2;

    public void mapMove(Player player, KeyHandle key) {
        double velocityX = 0;
        double velocityY = 0;
        double newX = x + startX * mapFrameSize;
        double newY = y + startX * mapFrameSize;

        if (key.up && !CollisionHandle.isCollision(player, this, 1, newX, newY)) {
            velocityY -= 5;
        }
        if (key.down && !CollisionHandle.isCollision(player, this, 2, newX, newY)) {
            velocityY += 5;
        }
        if (key.left && !CollisionHandle.isCollision(player, this, 3, newX, newY)) {
            velocityX -= 5;
        }
        if (key.right && !CollisionHandle.isCollision(player, this, 4, newX, newY)) {
            velocityX += 5;
        }

//        Tính toán vị trí mới của player rồi thay đổi vị trí của map.
        double sqrt = Math.sqrt(velocityX * velocityX + velocityY * velocityY);
        if (sqrt == 0) {
            return;
        }
        x += velocityX * player.getVerticalSpeed() / sqrt;
        y += velocityY * player.getVerticalSpeed() / sqrt;
    }

    public double getMapFrameSize() {
        return mapFrameSize;
    }
}
