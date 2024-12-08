package com.game.itgame.util;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Hitbox extends Rectangle {
    private double centerX, centerY;
    public Hitbox(double x, double y, double width, double height) {
        super(x, y , width, height);
    }
    public void update(double x, double y){
        super.setX(x);
        super.setY(y);
        centerX = x + getWidth()/2;
        centerY = y + getHeight()/2;
    }
    public void draw(GraphicsContext gc){
        gc.setStroke(Color.BLUE);
        gc.strokeRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    public double getCenterX() {
        return centerX;
    }
    public double getCenterY() {
        return centerY;
    }
}
