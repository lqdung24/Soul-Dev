package com.game.itgame.map;

import com.game.itgame.entity.player.Player;
import com.game.itgame.eventHandle.KeyHandle;
import javafx.scene.canvas.GraphicsContext;

public interface Map {
    void mapRender(GraphicsContext ctx, Player player);
    void mapMove(Player player);
}
