package com.game.itgame.entity.enemy;

import com.game.itgame.eventHandle.Skill;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Objects;

public class Mob2 extends EnemyRender {

    public Mob2(int x, int y, GraphicsContext ctx) {
        super(x, y, ctx);
        this.width = 60;
        this.height = 60;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mob/mob2/mob2walk.png")));
        this.imageWidth = 720;
        this.imageHeight = 720;
        this.frameLength = 3;
        this.frameStateIndex = 0;
        this.verticalSpeed = 2;
        Hp = 8;
    }
}
