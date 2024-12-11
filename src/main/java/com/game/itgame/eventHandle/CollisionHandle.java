package com.game.itgame.eventHandle;

import com.game.itgame.entity.EntityRender;
import com.game.itgame.map.MapMove;

import java.util.ArrayList;
import java.util.List;

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
        int colTopLeft = (int) ((newX + 15)/ map.getMapFrameSize());
        int rowTopLeft = (int) ((newY + 15)/ map.getMapFrameSize());

        int colBottomRight = (int) ((newX + entity.hitbox.getWidth() + 15) / map.getMapFrameSize());
        int rowBottomRight = (int) ((newY +  entity.hitbox.getHeight() + 15) / map.getMapFrameSize());

        //System.out.println(map.getValue(rowTopLeft, colTopLeft) + " " + rowBottomRight + " " + colTopLeft);

        return !checkMoveable(map.getValue(rowTopLeft, colTopLeft)) ||
                !checkMoveable(map.getValue(rowTopLeft, colBottomRight))||
                !checkMoveable(map.getValue(rowBottomRight, colTopLeft)) ||
                !checkMoveable(map.getValue(rowBottomRight, colBottomRight));
    }
    public static boolean checkMoveable(int x){
        return (x == 0) || (x == 17) || (x == 18) || (x == 19)
                || (x == 20) || (x == 21) || (x == 23)
                || (x == 37) || (x == 38);
    }
}
