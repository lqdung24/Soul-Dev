package com.game.itgame.map;

import com.game.itgame.entity.Enemy.Ghost;
import com.game.itgame.entity.player.Player;
import com.game.itgame.eventHandle.CollisionHandle;
import com.game.itgame.eventHandle.KeyHandle;

public abstract class MapMove extends MapMatrix implements Map{
    protected double x = 0;
    protected double y = 0;
    protected double mapFrameSize = 30;
    protected int startX = 2;
    protected int startY = 2;
    private double offsetX;
    private double offsetY; // xử lí draw cho thực thể

    Ghost ghost;

    public void mapMove(Player player, KeyHandle key) {
        double velocityX = 0;
        double velocityY = 0;
        double newX = x + startX * mapFrameSize;
        double newY = y + startX * mapFrameSize;
        offsetY = offsetX = 0;

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
        offsetX = velocityX * player.getVerticalSpeed() / sqrt;
        offsetY = velocityY * player.getVerticalSpeed() / sqrt;
        x += offsetX;
        y += offsetY;
    }

    public double getMapFrameSize() {
        return mapFrameSize;
    }
    public void setGhost(Ghost ghost){
        this.ghost = ghost;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }
}
