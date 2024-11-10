package com.game.itgame.entity.Enemy;

import com.game.itgame.eventHandle.EnemyHandle;

public abstract class EnemyMove implements Enemy{
    protected double verticalSpeed;
    protected int frameStateIndex;
    protected int frameLength;
    protected int frameIndex = 0;

    @Override
    public void move(EnemyHandle key) {}
    public double getVerticalSpeed(){
        return verticalSpeed;
    }
}
