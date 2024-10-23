package com.game.itgame.entity;

import com.game.itgame.controller.GameInput;
import com.game.itgame.map.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends Moveable {
    GameInput input;

    public Player(Map map, Canvas canvas, double startX, double startY) {
        this.border = new Rectangle(16, 16, Color.PURPLE);
        this.map = map;
        this.canvas = canvas;
        this.witdh = 16;
        this.height = 20;

        newX = startX;
        newY = startY;
        updateLocation();
        getResource();
        draw();
        //this.image = new Image("C:\\Users\\MyPC\\IdeaProjects\\ITGame\\src\\main\\resources\\Image\\player.png");
    }

    @Override
    public void update() {// cần sửa lại để khi nhân vật di chuyển, map di chuyển theo :)sss
        // checkCrossMove();
        // cần sửa lại để kiểm tra di chuyển chéo dễ hơn -> kiểm tra để gì? để giảm tốc độ khi di chuyển chéo
        // có thể bỏ kiểm tra di chuyển chéo luôn :)
        canvas.getGraphicsContext2D().clearRect(X, Y, witdh, height);
        if (input.left) {
            moveLeft(-ver); // thử tăng tọa độ của vật
            // nếu ok thì update luôn
            // update trong từng trường hợp để tránh việc di chuyển chéo bị mắc tường
            updateLocation();
        }
        if (input.up) {
            moveUp(-ver);
            updateLocation();
        }
        if (input.down) {
            moveDown(ver);
            updateLocation();
        }
        if (input.right) {
            moveRight(ver);
            updateLocation();
        }
    }

    // sau này draw lại ảnh cho dễ
    @Override
    public void draw() {
        //draw something
        border.setX(X);
        border.setY(Y);
        canvas.getGraphicsContext2D().drawImage(image, X, Y, this.witdh, this.height);
        canvas.getGraphicsContext2D().fillRect(X, Y, witdh, height);
    }

    public void setInput(GameInput key) {
        this.input = key;
    }

    void checkCrossMove() {
        isCrossMove = (input.up || input.down) && (input.left || input.right);
    }

    @Override
    protected void getResource() {
        image = new Image("C:\\Users\\MyPC\\IdeaProjects\\ITGame\\src\\main\\resources\\Image\\player.png");
    }
}
