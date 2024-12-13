package com.game.itgame.map;

import com.game.itgame.entity.player.Player;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class MapRender extends MapMove implements Map {
    public static List<Room> roomList = new ArrayList<>();
    public MapRender() {
        roomList.add(new Room(1, 15, 4, 9));
        roomList.add(new Room(27, 46, 1, 17));
        roomList.add(new Room(54, 61, 4, 14));
        roomList.add(new Room(23, 50, 28, 39));
        roomList.add(new Room(7, 16, 25, 37));
        roomList.add(new Room(28, 47, 44, 60));
        roomList.add(new Room(55, 68, 30, 45));
        roomList.add(new Room(73, 79, 34, 40));
        roomList.add(new Room(66, 95, 1, 23));
    }
    public void mapRender(GraphicsContext ctx, Player player) {
        mapMove(player);
        double mapX = ctx.getCanvas().getWidth() / 2 - (startX * mapFrameSize + mapFrameSize / 2) -x ;
        double mapY = ctx.getCanvas().getHeight() / 2 - (startY * mapFrameSize + mapFrameSize / 2) -y ;

//        Lưu trạng thái ban đầu của canvas.
        ctx.save();
//        Di chuyển tới vị trí đầu tiên của map.
        ctx.translate(mapX, mapY);
//        Vẽ map.
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                ctx.drawImage(mapImageIndex[map[i][j]], 0, 0, 512, 512, j * mapFrameSize, i * mapFrameSize, mapFrameSize, mapFrameSize);
            }
        }
//        Trả về trạng thái ban đầu của canvas.
        ctx.restore();
    }
    public static int getRoomNum(double mx, double my){
        int x = (int) ((mx+15)/mapFrameSize);
        int y = (int) ((my+15)/mapFrameSize);
        for(int i=0; i<roomList.size(); i++){
            if(roomList.get(i).checkInRoom(x, y)){
                return i;
            }
        }
        return 0;
    }

}
