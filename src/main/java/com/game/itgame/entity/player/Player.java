package com.game.itgame.entity.player;

import com.game.itgame.entity.EntityRender;
import com.game.itgame.util.Hitbox;
import com.game.itgame.eventHandle.KeyHandle;
import com.game.itgame.skill.Skill;
import com.game.itgame.skill.Shield;
import com.game.itgame.weapon.bow.Bow;
import com.game.itgame.weapon.sword.Sword;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

import static com.game.itgame.controller.CanvasController.enemies;

public class Player extends EntityRender {
    protected HealthBar bar;
    protected Skill shield;
    private Sword sword;
    private Bow bow;
    private double offsetX = 10, offsetY = 10;
    public Player(double x, double y, GraphicsContext ctx) {
        super(x, y, ctx);
        this.width = 50;
        this.height = 50;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/maincharacter3.png")));
        this.imageWidth = 720;
        this.imageHeight = 720;
        this.frameLength = 5;
        this.frameStateIndex = 0;
        this.verticalSpeed = 5;
        this.damage = 2;
        this.Hp = 10;
        this.bar = new HealthBar(ctx, this);
        shield = new Shield();

        sword = new Sword();
        bow = new Bow();
        hitbox = new Hitbox(x, y, width - offsetX*2, height - offsetY);
    }
    @Override
    public void update(double deltaTime) {
        draw(deltaTime);
        bar.draw(deltaTime);
        hitbox.update(this.x + offsetX, this.y + offsetY);

        //shield.update(deltaTime);
    }
    @Override
    public void draw(double deltaTime) {
        ctx.drawImage(image, frameIndex * imageWidth, frameStateIndex * imageHeight, imageWidth, imageHeight, x, y, width, height);
        if(KeyHandle.left) {
            this.frameStateIndex = 1;
        }else if(KeyHandle.right) {
            this.frameStateIndex = 0;
        }
        //ctx.fillRect
        if(KeyHandle.firstWeapon){
            sword.draw(ctx, this, enemies, deltaTime);
            if(sword.onAttack) {
                if (KeyHandle.mouseLeft()) {
                    this.frameStateIndex = 1;
                } else {
                    this.frameStateIndex = 0;
                }
            }
            bow.arrows(ctx);
        }else{
            bow.draw(ctx, this, enemies, deltaTime);
            if(bow.onAttack){
                if (KeyHandle.mouseLeft()) {
                    this.frameStateIndex = 1;
                } else {
                    this.frameStateIndex = 0;
                }
            }
        }

        if (time > 100) {
            if (frameIndex >= frameLength) {
                frameIndex = 0;
            } else {
                frameIndex++;
                time = 0;
            }
        } else {
            time += deltaTime;
        }
        hitbox.draw(ctx);
    }

}
