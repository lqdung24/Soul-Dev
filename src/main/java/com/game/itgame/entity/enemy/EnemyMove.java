package com.game.itgame.entity.enemy;

import com.game.itgame.eventHandle.EnemyHandle;

public abstract class EnemyMove implements Enemy{
    protected double verticalSpeed;
    protected int frameStateIndex;
    protected int frameLength;
    protected int frameIndex = 0;
    public boolean moveLeft = false, moveRight = false;
    @Override
    public void move(EnemyHandle key) {
        if(moveLeft || moveRight) {
            frameStateIndex = 0;
        }
    }
    public double getVerticalSpeed(){
        return verticalSpeed;
    }
}
