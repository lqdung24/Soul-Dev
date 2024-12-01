package com.game.itgame.entity.player;

import com.game.itgame.entity.EntityRender;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Objects;

public class HealthBar{
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected double imageWidth;
    protected double imageHeight;
    protected GraphicsContext ctx;
    protected Image image;
    EntityRender entityRender;

     public HealthBar(GraphicsContext c, EntityRender entityRender){
         ctx = c;
         x = 30;
         y = 10;
         width = 48*2;
         height = 48*2;
         imageWidth = 720;
         imageHeight = 720;
         image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/healthbar1.png")));
         this.entityRender = entityRender;
     }

    public void draw(double deltaTime) {
         ctx.setFill(Color.WHITE);ctx.fillRect(0, 0, 200, 100);
         ctx.drawImage(image, (10-entityRender.Hp) * imageWidth, 0,
                    imageWidth, imageHeight, x, y, width, height);

    }
}
