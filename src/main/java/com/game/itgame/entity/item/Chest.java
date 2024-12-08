package com.game.itgame.entity.item;

import com.game.itgame.controller.CanvasController;
import com.game.itgame.map.MapMove;
import com.game.itgame.util.Hitbox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Chest extends Item{
    public Hitbox hitbox;
    int ix, iy;
    public Chest(int x, int y, GraphicsContext ctx) {
        super(x, y, ctx);
        this.ix = x;
        this.iy = y;
        this.width = 50;
        this.height = 50;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/items/CHEST.png")));
        this.imageWidth = 720;
        this.imageHeight = 720;
        this.frameLength = 3;
        this.frameStateIndex = 0;
        this.verticalSpeed = 0;
        Hp = 1;
        hitbox = new Hitbox(this.x, this.y, width, height);
    }

    @Override
    public void update(double deltaTime) {
        this.x -= MapMove.offsetX;
        this.y -= MapMove.offsetY;
        if(Hp == 0) {
            CanvasController.healthPotionList.add(new HealthPotion(x, y, ctx));
        }
        draw(deltaTime);
        hitbox.update(x, y);
        hitbox.draw(ctx);
    }
    @Override
    public void draw(double deltaTime) {
        ctx.drawImage(image, frameIndex * imageWidth, frameStateIndex * imageHeight, imageWidth, imageHeight, x, y, width, height);

        if(Hp == 0){
            if (time > 150) {
                if (frameIndex < frameLength) {
                    frameIndex++;
                    time = 0;
                }
            } else {
                time += deltaTime;
            }
        }
//        Chỉnh thơi gian chuyển frame

    }
}
