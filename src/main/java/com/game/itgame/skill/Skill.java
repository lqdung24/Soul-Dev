package com.game.itgame.skill;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Skill {
    protected static GraphicsContext ctx;
    protected Image buttonImage, skillImage;
    protected SkillTimer timer;
    public boolean availabel, activated;
    protected double x, y;
    protected double cooldown;
    protected int frameState, frameIndex,frameLength;
    protected int buttonState, buttonIndex,buttonLength;

    public Skill() {}

    public abstract void update(double deltaTime);

    public abstract void draw();

    public static void setGraphicsContext(GraphicsContext ctx){
        Skill.ctx = ctx;
    }
}
