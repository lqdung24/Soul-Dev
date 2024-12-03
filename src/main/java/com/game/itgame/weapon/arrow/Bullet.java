package com.game.itgame.weapon.arrow;

import com.game.itgame.entity.player.Player;
import com.game.itgame.weapon.sword.Sword;
import javafx.scene.image.Image;
import java.util.Objects;

public class Bullet extends FlyThings{
    public static Player player;
    public Bullet(double x, double y, double angle) {
        arrowX = x;
        arrowY = y;
        arrowAngle = angle;
        this.offsetAngle = -45;
        this.height = 60;
        this.width = 60;
        speed = 10;
        arrowImage = new Image(Objects.requireNonNull(Sword.class.getResourceAsStream("/images/mob/mob2/mob2bullet.png")));
    }

    @Override
    public void attack() {
        // tâm của đạn
        double distance = Math.sqrt(Math.pow(arrowX - player.getX(), 2) + Math.pow(arrowY - player.getY(), 2));
        if(distance <= 25){
            player.Hp -= 2;
            isAttacked = true;
        }
    }
}
