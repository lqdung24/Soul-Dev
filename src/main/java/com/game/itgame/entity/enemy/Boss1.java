package com.game.itgame.entity.enemy;

import com.game.itgame.skill.BossSkill;
import com.game.itgame.util.Hitbox;
import com.game.itgame.eventHandle.EntityHandle;
import com.game.itgame.skill.SkillTimer;
import com.game.itgame.map.MapMove;
import com.game.itgame.util.Timer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Objects;

public class Boss1 extends EnemyRender {
    public Image skillImage;
    private BossSkill bossSkill;
    public Boss1(int x, int y, GraphicsContext ctx) {
        super(x, y, ctx);
        this.width = 250;
        this.height = 250;
        this.skillImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mob/boss1/bossskill.png")));
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mob/boss1/boss.png")));
        this.imageWidth = 720;
        this.imageHeight = 720;
        this.frameLength = 3;
        this.frameStateIndex = 0;
        this.verticalSpeed = 2;
        Hp = 50;
        hitbox = new Hitbox(x, y, width - 100, height - 100);
        bossSkill = new BossSkill(this);
    }

    @Override
    public void update(double deltaTime) {
        //update vi tri tuong doi khi nhan vat di chuyen
        this.x -= MapMove.offsetX;
        this.y -= MapMove.offsetY;
        bossSkill.update(deltaTime);

        hitbox.update(x + 50, y + 80);
        EntityHandle.collisionPlayer(this, deltaTime);
        draw(deltaTime);
        hitbox.draw(ctx);
    }
    @Override
    public void draw(double deltaTime) {
        Image drawImg;

        if(bossSkill.attack1.state != 0){
            drawImg = skillImage;
            frameLength = 11;
        }else{
            drawImg = image;
            frameLength = 3;
        }
        if (frameIndex >= frameLength) {
            frameIndex = 0;
            frameStateIndex++;
            frameStateIndex %= 4;
        }
        if (time > 200) {
            frameIndex++;
            time = 0;
        } else {
            time += deltaTime;
        }

        ctx.drawImage(drawImg, frameIndex * imageWidth, frameStateIndex * imageHeight, imageWidth, imageHeight, x, y, width, height);
    }
}
