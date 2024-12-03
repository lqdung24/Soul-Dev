package com.game.itgame.entity;

import com.game.itgame.eventHandle.KeyHandle;

public interface EntityInterface {
//    public void update(double deltaTime);
    void update(double deltaTime, KeyHandle key);
    void draw(double deltaTime);
    void move(KeyHandle key);
}
