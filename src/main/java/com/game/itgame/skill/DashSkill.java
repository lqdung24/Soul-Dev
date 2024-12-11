package com.game.itgame.skill;

import com.game.itgame.controller.CanvasController;
import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.entity.player.Player;
import com.game.itgame.eventHandle.KeyHandle;
import javafx.scene.shape.Shape;

public class DashSkill {
    protected SkillTimer dashSkill;
    protected Player player;
    private double activeTime, timer;
    private boolean actived;
    private int impactDamage = 2;
    private double collisionTimer, interval = 1000;

    public DashSkill(double cooldown, double activeTime, Player player) {
        this.player = player;
        dashSkill = new SkillTimer(cooldown, 10, 0);
        this.activeTime = activeTime;
    }
    public void update(double deltaTime){
        dashSkill.update(deltaTime);
        if(dashSkill.state == 1){
            player.increaseMana(1);
        }
        if(KeyHandle.space && !actived){
            if(player.getMana() != 0){
                player.increaseMana(-1);
                actived = true;
            }
        }
        if(actived){
            timer += deltaTime;
            if(timer >= activeTime){
                timer = 0;
                actived = false;
            }
            dashMode();
            inflictDamage(deltaTime);
        }else{
            normMode();
        }
    }
    public void normMode(){
        player.setVerticalSpeed(5);
        player.setImmune(false);
    }
    public void dashMode(){
        player.setVerticalSpeed(20);
        player.setImmune(true);
    }
    public void inflictDamage(double deltaTime){
        for (EnemyRender enemy : CanvasController.enemies) {
            collisionTimer += deltaTime;
            if (collisionTimer >= interval) {
                if (Shape.intersect(player.hitbox, enemy.hitbox).getBoundsInLocal().getWidth() > 0) {
                    enemy.Hp -= impactDamage;
                    collisionTimer = 0;
                }
            }
        }
    }
}
