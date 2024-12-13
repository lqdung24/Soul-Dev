package com.game.itgame.map;

import com.game.itgame.entity.player.Player;
import com.game.itgame.eventHandle.CollisionHandle;
import com.game.itgame.eventHandle.KeyHandle;

public abstract class MapMove extends MapMatrix implements Map{
    protected double x = 0;
    protected double y = 0;
    public static double mapFrameSize = 60;
    public static int startX = 5, startY = 5;
    public static double offsetX, offsetY;
    private double ox = 15, oy = 30;

    public void mapMove(Player player) {
        double velocityX = 0;
        double velocityY = 0;
        player.mapX = x + startX * mapFrameSize;
        player.mapY = y + startY * mapFrameSize;
        offsetY = offsetX = 0;

        if (KeyHandle.up && !CollisionHandle.isCollision(player, this, 1, player.mapX+ox, player.mapY + oy)) {
            velocityY -= 5;
        }
        if (KeyHandle.down && !CollisionHandle.isCollision(player, this, 2, player.mapX+ox, player.mapY + oy)) {
            velocityY += 5;
        }
        if (KeyHandle.left && !CollisionHandle.isCollision(player, this, 3, player.mapX+ox, player.mapY + oy)) {
            velocityX -= 5;
        }
        if (KeyHandle.right && !CollisionHandle.isCollision(player, this, 4, player.mapX+ox, player.mapY + oy)) {
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
    public void restart(){
        x = y = 0;
    }
}
