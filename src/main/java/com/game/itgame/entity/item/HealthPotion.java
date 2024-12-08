package com.game.itgame.entity.item;

import com.game.itgame.eventHandle.EntityHandle;
import com.game.itgame.map.MapMove;
import com.game.itgame.util.Hitbox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class HealthPotion extends Item {
    public HealthPotion(double x, double y, GraphicsContext ctx) {
        super(x, y, ctx);
        this.width = 50;
        this.height = 50;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/items/healthpoison.png")));
        this.imageWidth = 720;
        this.imageHeight = 720;
        this.frameLength = 0;
        this.frameStateIndex = 0;
        this.verticalSpeed = 0;
        Hp = 1;
        hitbox = new Hitbox(this.x, this.y, width, height);
    }
    @Override
    public void update(double deltaTime) {
        this.x -= MapMove.offsetX;
        this.y -= MapMove.offsetY;
        draw(deltaTime);
        hitbox.update(x, y);
        hitbox.draw(ctx);
        expired = EntityHandle.itemUse(this);
    }
    @Override
    public void draw(double deltaTime) {
        ctx.drawImage(image, frameIndex * imageWidth, frameStateIndex * imageHeight, imageWidth, imageHeight, x, y, width, height);
    }

}
