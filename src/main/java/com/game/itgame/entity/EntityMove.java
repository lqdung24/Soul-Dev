package com.game.itgame.entity;

import com.game.itgame.eventHandle.KeyHandle;

public abstract class EntityMove  implements EntityInterface {
    protected double verticalSpeed;
    protected int frameStateIndex;
    protected int frameLength;
    protected int frameIndex = 0;


    @Override
    public void move() {
////        Thay đổi frameStateIndex và frameLength tùy thuộc vào trạng thái của player.
    }
    public double getVerticalSpeed(){
        return verticalSpeed;
    }
}
