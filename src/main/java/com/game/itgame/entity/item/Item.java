package com.game.itgame.entity.item;

import com.game.itgame.entity.EntityRender;
import com.game.itgame.map.MapMove;
import com.game.itgame.map.MapRender;
import javafx.scene.canvas.GraphicsContext;

public class Item extends EntityRender{
    protected double mapX, mapY;
    public Item(int x, int y, GraphicsContext ctx) {
        this.x = ctx.getCanvas().getWidth()/2-15-2* MapRender.mapFrameSize + x*MapRender.mapFrameSize;
        this.y = ctx.getCanvas().getHeight()/2-15-2*MapRender.mapFrameSize + y*MapRender.mapFrameSize;
        mapX = x*MapRender.mapFrameSize;
        mapY = y*MapRender.mapFrameSize;
        this.ctx = ctx;
    }
    public Item(double x, double y, GraphicsContext ctx) {
        this.x = x;
        this.y = y;
        this.ctx = ctx;
        mapX = x*MapRender.mapFrameSize;
        mapY = y*MapRender.mapFrameSize;
    }

    @Override
    public void update(double deltaTime) {
        this.x -= MapMove.offsetX;
        this.y -= MapMove.offsetY;
        draw(deltaTime);
    }

    @Override
    public void draw(double deltaTime) {
        ctx.drawImage(image, frameIndex * imageWidth, frameStateIndex * imageHeight, imageWidth, imageHeight, x, y, width, height);

//        Chỉnh thơi gian chuyển frame
        if (time > 200) {
            if (frameIndex >= frameLength) {
                frameIndex = 0;
            } else {
                frameIndex++;
                time = 0;
            }
        } else {
            time += deltaTime;
        }
    }
}
