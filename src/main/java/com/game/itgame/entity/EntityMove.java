package com.game.itgame.entity;

import com.game.itgame.eventHandle.KeyHandle;

public abstract class EntityMove implements Entity {
    protected double x;
    protected double y;
    protected double verticalSpeed;
    protected int frameStateIndex;
    protected int frameLength;
    protected int frameIndex = 0;

    public EntityMove(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(KeyHandle key) {
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

        if(sqrt == 0) {
            this.frameStateIndex = 0;
            this.frameLength = 6;
        } else {
            this.frameStateIndex = 3;
            this.frameLength = 8;
        }
    }
}
