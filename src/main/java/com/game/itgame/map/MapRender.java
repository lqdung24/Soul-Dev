package com.game.itgame.map;

import com.game.itgame.entity.player.Player;
import com.game.itgame.eventHandle.KeyHandle;
import javafx.scene.canvas.GraphicsContext;

public class MapRender extends MapMove implements Map {
    private double mapFrameSize = 30;
    private int startX = 2;
    private int startY = 2;



    public void mapRender(GraphicsContext ctx, Player player, KeyHandle key) {
        mapMove(player, key);
        double mapX = ctx.getCanvas().getWidth() / 2 - (startX * mapFrameSize + mapFrameSize / 2) -x ;
        double mapY = ctx.getCanvas().getHeight() / 2 - (startY * mapFrameSize + mapFrameSize / 2) -y ;

//        Lưu trạng thái ban đầu của canvas.
        ctx.save();
//        Di chuyển tới vị trí đầu tiên của map.
        ctx.translate(mapX, mapY);
//        Vẽ map.
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                ctx.drawImage(mapImageIndex[map[i][j]], 0, 0, 512, 512, j * 30, i * 30, 30, 30);
            }
        }
//        Trả về trạng thái ban đầu của canvas.
        ctx.restore();
    }


}
