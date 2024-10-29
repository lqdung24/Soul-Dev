package com.game.itgame.eventHandle;

import com.game.itgame.entity.EntityRender;
import com.game.itgame.map.MapMove;

public class CollisionHandle {
    static public final int U = 1, D = 2, L = 3, R = 4;

    public static boolean isCollision(EntityRender entity, MapMove map, int direction, double newX, double newY) {
        switch (direction) {
            case U:
                newY -= entity.getVerticalSpeed();
                break;
            case D:
                newY += entity.getVerticalSpeed();
                break;
            case L:
                newX -= entity.getVerticalSpeed();
                break;
            case R:
                newX += entity.getVerticalSpeed();
                break;
        }
        int colTopLeft = (int) (newX / map.getMapFrameSize());
        int rowTopLeft = (int) (newY / map.getMapFrameSize());

        int colBottomRight = (int) ((newX + entity.getWidth()) / map.getMapFrameSize());
        int rowBottomRight = (int) ((newY + entity.getHeight()) / map.getMapFrameSize());

        if (rowTopLeft < 0 || rowBottomRight >= 50 || colTopLeft < 0 || colBottomRight >= 50) {
            return true;
        }

        if (map.getValue(rowTopLeft, colTopLeft) != 0 ||
                map.getValue(rowTopLeft, colBottomRight) != 0 ||
                map.getValue(rowBottomRight, colTopLeft) != 0 ||
                map.getValue(rowBottomRight, colBottomRight) != 0) {
            return true;
        }

        return false;
    }
}
