package com.game.itgame.entity;

import com.game.itgame.eventHandle.KeyHandle;

interface Entity {
//    public void update(double deltaTime);
    public void update(double deltaTime, KeyHandle key);
    public void draw(double deltaTime);
    public void move(KeyHandle key);
}
