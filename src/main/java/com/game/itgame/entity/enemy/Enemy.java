package com.game.itgame.entity.enemy;

import com.game.itgame.eventHandle.EnemyHandle;
import com.game.itgame.map.MapMove;

public interface Enemy {
    void update(double deltaTime, EnemyHandle key, MapMove map);
    void draw(double deltaTime);
    void move(EnemyHandle key);
}
