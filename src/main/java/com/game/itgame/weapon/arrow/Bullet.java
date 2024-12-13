package com.game.itgame.weapon.arrow;

import com.game.itgame.eventHandle.EntityHandle;
import com.game.itgame.util.Hitbox;
import com.game.itgame.entity.player.Player;
import com.game.itgame.weapon.sword.Sword;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

import java.util.Objects;

public class Bullet extends FlyThings{
    public static Player player;
    public Bullet(double x, double y, double angle) {
        super();
        arrowX = x;
        arrowY = y;
        arrowAngle = angle;
        this.offsetAngle = 0;
        this.height = 40;
        this.width = 40;
        speed = 10;
        arrowImage = new Image(Objects.requireNonNull(Sword.class.getResourceAsStream("/images/mob/mob2/mob2bullet.png")));
        offsetY = 15;
        offsetX = 15;
        hitbox = new Hitbox(x, y, 22, 22);
    }

    @Override
    public void attack() {
        // tâm của đạn
        if(Shape.intersect(hitbox, player.hitbox).getBoundsInLocal().getWidth() > 0){
            EntityHandle.reduceHp(2);
            remove = true;
        }
    }
}
