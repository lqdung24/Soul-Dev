package com.game.itgame.entity.player;

import com.game.itgame.entity.EntityRender;
import com.game.itgame.map.MapRender;
import com.game.itgame.skill.DashSkill;
import com.game.itgame.util.Hitbox;
import com.game.itgame.eventHandle.KeyHandle;
import com.game.itgame.weapon.bow.Bow;
import com.game.itgame.weapon.sword.Sword;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

import static com.game.itgame.controller.CanvasController.enemies;

public class Player extends EntityRender {
    protected HealthBar healthBar;
    protected DashSkill dashSkill;
    private Sword sword;
    private Bow bow;
    private double offsetX = 10, offsetY = 10;
    private ManaBar manaBar;
    private int mana;
    private boolean immune = false;
    private Image runImage;

    public Player(double x, double y, GraphicsContext ctx) {
        super(x, y, ctx);
        this.width = 85;
        this.height = 85;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/maincharacter3.png")));
        this.runImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/characterwalk.png")));
        this.imageWidth = 720;
        this.imageHeight = 720;
        this.frameLength = 5;
        this.frameStateIndex = 0;
        this.verticalSpeed = 5;
        this.damage = 2;
        this.Hp = 10;
        this.healthBar = new HealthBar(ctx, this);
        this.manaBar = new ManaBar(ctx, this);
        this.mana = 3;

        dashSkill = new DashSkill(1000, 100, this);
        sword = new Sword();
        bow = new Bow();
        hitbox = new Hitbox(x + 5, y + 20, width - offsetX*2 - 10, height - offsetY - 20);
    }

    @Override
    public void update(double deltaTime) {
        draw(deltaTime);
        healthBar.draw(deltaTime);
        manaBar.draw(deltaTime);
        hitbox.update(this.x + offsetX + 5, this.y + offsetY + 20);
        dashSkill.update(deltaTime);
        roomNum = MapRender.getRoomNum(mapX, mapY);
        //System.out.println(roomNum);
        hitbox.draw(ctx);
    }

    @Override
    public void draw(double deltaTime) {
        Image drawImg;
        if(KeyHandle.space || !(KeyHandle.left || KeyHandle.right || KeyHandle.up || KeyHandle.down)){
            drawImg = image;
        }else{
            drawImg = runImage;
        }
        ctx.drawImage(drawImg, frameIndex * imageWidth, frameStateIndex * imageHeight, imageWidth, imageHeight, x, y, width, height);
        if(KeyHandle.left) {
            this.frameStateIndex = 1;
        }else if(KeyHandle.right) {
            this.frameStateIndex = 0;
        }

        //weapon chose
        if(KeyHandle.firstWeapon){
            sword.draw(ctx, this, enemies, deltaTime);
            if(sword.onAttack) {
                if (KeyHandle.mouseLeft()) {
                    this.frameStateIndex = 1;
                } else {
                    this.frameStateIndex = 0;
                }
            }
            bow.arrows(ctx, deltaTime);
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
    }

    public void setImmune(boolean immune) {
        this.immune = immune;
    }

    public boolean getImmuneState(){
        return immune;
    }

    public int getMana() {
        return mana;
    }
    public boolean increaseMana(int change) {
        mana += change;
        if(mana <= 0){
            mana = 0;
            return false;
        }
        if(mana >= 3){
            mana = 3;
        }
        return true;
    }
    @Override
    public void restart(){
        this.startX = x;
        this.startY = y;
        bow.arrows.clear();
        Hp = 10;
        mana = 3;
    }
}
