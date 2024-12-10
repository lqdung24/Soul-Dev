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

     public HealthBar(GraphicsContext ctx, EntityRender entityRender){
         this.ctx = ctx;
         x = 30;
         y = 10;
         height = 70;
         width = height*2.5;
         imageWidth = 1800;
         imageHeight = 720;
         image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/healthbar.png")));
         ctx.drawImage(image, x, y);
         this.entityRender = entityRender;
     }

    public void draw(double deltaTime) {

        if (entityRender.Hp >= 5) {
            ctx.drawImage(image, (10-entityRender.Hp) * imageWidth, 0,
                    imageWidth, imageHeight, x, y, width, height);
        }else {
            ctx.drawImage(image, (4-entityRender.Hp)*imageWidth, 720, imageWidth, imageHeight, x, y, width, height);
        }

        //ctx.drawImage(image, x, y);

    }
}
