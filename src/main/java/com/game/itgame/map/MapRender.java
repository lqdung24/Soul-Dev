package com.game.itgame.map;


import com.game.itgame.entity.player.Player;
import com.game.itgame.eventHandle.KeyHandle;
import javafx.scene.canvas.GraphicsContext;

public class MapRender extends MapMove implements Map {
    private double mapFrameSize = 16.05;
    private int startX = 6;
    private int startY = 6;

    public void mapRender(GraphicsContext ctx, Player player, KeyHandle key) {
        mapMove(player, key);
        double mapX = ctx.getCanvas().getWidth() / 2 - (startX * mapFrameSize + mapFrameSize / 2) -x ;
        double mapY = ctx.getCanvas().getHeight() / 2 - (startY * mapFrameSize + mapFrameSize / 2) -y ;

        ctx.save();
        ctx.translate(mapX, mapY);
        for (int i = 0; i < mapImageIndex.length; i++) {
            for (int j = 0; j < mapImageIndex[i].length; j++) {
                ctx.drawImage(mapImage, mapImageIndex[i][j][0] * mapFrameSize, mapImageIndex[i][j][1] * mapFrameSize, mapFrameSize, mapFrameSize, j * 30, i * 30, 30, 30);
            }
        }
        ctx.restore();
    }
}
