package com.game.itgame.eventHandle;

import com.game.itgame.entity.enemy.EnemyRender;

public class Skill {
    EnemyRender enemy;
    public double cooldownTime, activeTime, randomTime;
    public int state; // 0 -> normal, 1 -> skill active, maybe 2 -> moverandom mode
    public double timer;
    public double interval = 0;
    public int makeDamage = 0;
    public double angle;
    public boolean isOneTime = false;
    public boolean isAttack;

    public Skill(EnemyRender enemy, double cooldownTime, double activeTime, double randomTime){
        this.enemy = enemy;
        this.cooldownTime = cooldownTime;
        this.activeTime = activeTime;
        this.randomTime = randomTime;
        timer = 0;
        this.isOneTime = false;
    }

    public Skill(EnemyRender enemy, double cooldownTime, boolean isOneTime, double randomTime){
        this.enemy = enemy;
        this.cooldownTime = cooldownTime;
        this.activeTime = 0;
        this.randomTime = randomTime;
        timer = 0;
        this.isOneTime = true;
    }

    public void update(double deltaTime){
        timer += deltaTime;
        interval += deltaTime;
        if(state == 1 && interval >= 900){
            makeDamage++;
            interval = 0;
        }

        // chuyển trạng thái: cooldown -> active -> random
        if(state == 0 && timer >= cooldownTime){
            state = 1;
            timer = 0;
            makeDamage = 0;
        }
        if(state == 1 && (timer >= activeTime || isOneTime)){
            isAttack = true;
            state = 2;
            timer = 0;
        }
        if(state == 2 && timer >= randomTime){
            state = 0;
            timer = 0;
            angle = Math.random() * Math.PI * 2;
        }
    }
}
