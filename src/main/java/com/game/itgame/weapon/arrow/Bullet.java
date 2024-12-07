package com.game.itgame.weapon.arrow;

import com.game.itgame.entity.Hitbox;
import com.game.itgame.entity.player.Player;
import com.game.itgame.weapon.sword.Sword;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

import java.util.Objects;

public class Bullet extends FlyThings{
    public static Player player;
    public Bullet(double x, double y, double angle) {
        arrowX = x;
        arrowY = y;
        arrowAngle = angle;
        this.offsetAngle = 0;
        this.height = 20;
        this.width = 20;
        speed = 6;
        arrowImage = new Image(Objects.requireNonNull(Sword.class.getResourceAsStream("/images/mob/mob2/mob2bullet.png")));
        offsetY = 5;
        hitbox = new Hitbox(arrowX, arrowY, width-10, height-10);
    }

    @Override
    public void attack() {
        // tâm của đạn
        if(Shape.intersect(hitbox, player.hitbox).getBoundsInLocal().getWidth() > 0){
            player.Hp -= 2;
            isAttacked = true;
        }
    }
}
