package com.game.itgame.skill;

import com.game.itgame.entity.enemy.Boss1;
import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.eventHandle.EntityHandle;
import com.game.itgame.map.MapMove;
import com.game.itgame.util.Timer;
import javafx.scene.image.Image;

import java.util.Objects;

public class BossSkill {
    Boss1 boss;
    public Timer timer1, timer2;
    public SkillTimer attack1;
    private SkillTimer timer3;
    private double lx, ly;
    private Image lightningImage;
    private double frameIndex, time;
    private double lighhtTimer;
    private boolean inflicted = false;

    public BossSkill(EnemyRender enemy) {
        boss = (Boss1) enemy;
        frameIndex = 1;
        attack1 = new SkillTimer(2000, 9600, 2800);
        lightningImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mob/boss1/bosslightning.png")));
        timer1 = new Timer(1000);
        timer2 = new Timer(2000);
        timer3 = new SkillTimer(300, 300, 0);
    }

    public void update(double deltaTime){
        attack1.update(deltaTime);
        timer1.update(deltaTime);
        timer2.update(deltaTime);

        switch (attack1.state){
            case 0:
                EntityHandle.moveEnemy(boss);
                inflicted = false;
                break;
            case 1:
                if(timer1.available){
                    EntityHandle.bulletMob2Attack(boss);
                    timer1.available = false;
                }
                if(timer2.available){
                    EntityHandle.fullBulletAttack(boss, 24);
                    timer2.available = false;
                }
                break;
            case 2:
                lighhtTimer += deltaTime;
                if(lighhtTimer < 2000){
                    tracking(deltaTime);
                }else if(lighhtTimer < 2800){
                    lightning(deltaTime);
                }else{
                    lighhtTimer = 0;
                }
                break;
        }
    }

    public void tracking(double deltaTime){
        this.lx = EntityHandle.player.hitbox.getCenterX();
        this.ly = EntityHandle.player.hitbox.getCenterY();
        timer3.update(deltaTime);
        if(timer3.state == 1){
            EntityHandle.player.getCtx().drawImage(lightningImage, 0, 0, 720, 720, lx - 105, ly - 100, 200, 200);
        }
    }

    public void lightning(double deltaTime){
        lx -= MapMove.offsetX;
        ly -= MapMove.offsetY;
        time += deltaTime;
        if (frameIndex >= 5) {
            frameIndex = 1;
        }
        if (time > 200) {
            frameIndex++;
            time = 0;
        }
        EntityHandle.player.getCtx().drawImage(lightningImage, frameIndex * 720, 0, 720, 720, lx - 105, ly - 100, 200, 200);
        if(!inflicted && frameIndex >= 2){
            inflicted = EntityHandle.bossSkill3CheckDamage(lx, ly);
        }

    }
}
