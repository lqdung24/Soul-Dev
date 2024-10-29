package com.game.itgame.entity;

import com.game.itgame.eventHandle.KeyHandle;

public abstract class EntityMove  implements EntityInterface {
    protected double verticalSpeed;
    protected int frameStateIndex;
    protected int frameLength;
    protected int frameIndex = 0;


    @Override
    public void move(KeyHandle key) {
//        Thay đổi frameStateIndex và frameLength tùy thuộc vào trạng thái của player.
        if(key.up || key.down || key.left || key.right) {
            this.frameStateIndex = 3;
            this.frameLength = 8;
        } else {
            this.frameStateIndex = 0;
            this.frameLength = 6;
        }
    }
    public double getVerticalSpeed(){
        return verticalSpeed;
    }
}
