package com.game.itgame.entity.enemy;

import com.game.itgame.eventHandle.EntityHandle;
import com.game.itgame.map.MapMove;
import com.game.itgame.skill.SkillTimer;
import com.game.itgame.util.Hitbox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Boss1 extends EnemyRender {
    public Boss1(double x, double y, GraphicsContext ctx) {
        super(x, y, ctx);
        this.width = 50;
        this.height = 50;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mob/boss1/boss.png")));
        this.imageWidth = 720;
        this.imageHeight = 720;
        this.frameLength = 15;
        this.frameStateIndex = 0;
        this.verticalSpeed = 2;
        Hp = 20;
        attack1 = new SkillTimer(4000, 1500, 1000);
        hitbox = new Hitbox(x, y, width, height);
    }

    @Override
    public void update(double deltaTime) {
        this.x -= MapMove.offsetX;
        this.y -= MapMove.offsetY;

        EntityHandle.moveEnemy(this);
        EntityHandle.collisionPlayer(this, deltaTime);
        draw(deltaTime);
    }
}
