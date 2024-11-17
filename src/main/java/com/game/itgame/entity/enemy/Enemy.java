package com.game.itgame.entity.Enemy;

import com.game.itgame.eventHandle.EnemyHandle;
import com.game.itgame.map.MapMove;

public interface Enemy {
    public void update(double deltaTime, EnemyHandle key, MapMove map);
    public void draw(double deltaTime);
    public void move(EnemyHandle key);
}
