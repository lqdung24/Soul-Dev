package com.game.itgame.weapon.arrow;

import com.game.itgame.weapon.sword.Sword;
import javafx.scene.image.Image;

import java.util.Objects;

public class Arrow extends ArrowRender {
    public Arrow(double x, double y, double angle) {
        arrowX = x;
        arrowY = y;
        arrowAngle = angle;
        this.offsetAngle = 0;
        height = width = 30;
        arrowImage = new Image(Objects.requireNonNull(Sword.class.getResourceAsStream("/images/weapon/arrow22.png")));
    }
}
