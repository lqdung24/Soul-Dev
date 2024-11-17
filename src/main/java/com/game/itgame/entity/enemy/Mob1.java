package com.game.itgame.entity.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Objects;

public class Mob1 extends EnemyRender {
    public boolean attacking = false;
    public Mob1(int x, int y, GraphicsContext ctx) {
        super(x, y, ctx);
        this.width = 30;
        this.height = 30;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mob/mob1/mob1.png")));
        this.imageWidth = 720;
        this.imageHeight = 720;
        this.frameLength = 3;
        this.frameStateIndex = 0;
        this.verticalSpeed = 2;
        Hp = 8;
    }
}
