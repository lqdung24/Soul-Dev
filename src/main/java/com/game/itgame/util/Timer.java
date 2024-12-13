package com.game.itgame.util;

public class Timer {
    private double timer, interval;
    public boolean available;
    public Timer(double interval) {
        this.interval = interval;
        timer = 0;
        available = false;
    }
    public void update(double deltaTime){
        if(available){
            return;
        }
        timer += deltaTime;
        if(timer >= interval){
            available = true;
            timer = 0;
        }
    }
}
