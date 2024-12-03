package com.game.itgame.weapon;

import javafx.scene.canvas.GraphicsContext;

public class Aim {
    public double mouseX, mouseY;
    public Aim(GraphicsContext ctx){
        ctx.getCanvas().setOnMouseMoved(e -> {
            mouseX = e.getX();
            mouseY = e.getY();
        });
    }
}
