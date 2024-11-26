package com.game.itgame.eventHandle;

import com.game.itgame.entity.enemy.EnemyRender;

public class Skill {
    EnemyRender enemy;
    public double cooldownTime, activeTime;
    public boolean isActive;
    public double timer;
    public double interval = 0;
    public int makeDamage = 0;

    public Skill(EnemyRender enemy, double cooldownTime, double activeTime){
        this.enemy = enemy;
        this.cooldownTime = cooldownTime;
        this.activeTime = activeTime;
        timer = 0;
    }
    public void update(double deltaTime){
        timer += deltaTime;
        interval += deltaTime;
        if(isActive && interval >= 900){
            makeDamage++;
            interval = 0;
        }
        if(isActive && timer >= activeTime){
            isActive = false;
            timer = 0;
            System.out.println("check");
        }
        if(!isActive && timer >= cooldownTime){
            isActive = true;
            timer = 0;
            makeDamage = 0;
        }
    }
}
