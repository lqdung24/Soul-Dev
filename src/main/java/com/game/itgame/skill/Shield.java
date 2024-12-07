package com.game.itgame.skill;

import com.game.itgame.eventHandle.KeyHandle;
import javafx.scene.image.Image;

import java.util.Objects;

public class Shield extends Skill {
    double time = 0;
    double skillX, skillY;
    public Shield() {
        activated = false;
        this.x = 100;
        this.y = ctx.getCanvas().getHeight() - 200;
        skillX = ctx.getCanvas().getWidth() / 2;
        skillY = ctx.getCanvas().getHeight() / 2;
        buttonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/shieldbutton.png")));
        skillImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/shield.png")));
        timer = new SkillTimer(1000, 1000, 50);

    }
    // 3 state: cooldown, availabel, actived
    @Override
    public void update(double deltaTime) {
        time += deltaTime;
        if(time >= 10000){
            availabel = true;
            time = 0;
        }
        if(availabel && KeyHandle.one){
            activated = true;
            availabel = false;
            System.out.println("Shield activated");
        }
        draw();
    }

    @Override
    public void draw() {
        if(activated){
            ctx.drawImage(skillImage, 720, 720, 720, 720, skillX - 150/2, skillY-150/2, 150, 150);
        }
        if(availabel){
            ctx.drawImage(buttonImage, 0, 0, 720, 720, x, y, 150, 150);
        }else{
            ctx.drawImage(buttonImage, 720, 0, 720, 719, x, y, 150, 150);
        }

    }
}
