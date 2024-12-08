package com.game.itgame.entity.enemy;

import com.game.itgame.util.Hitbox;
import com.game.itgame.eventHandle.EnemyHandle;
import com.game.itgame.skill.SkillTimer;
import com.game.itgame.map.MapMove;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Objects;

public class Mob1 extends EnemyRender {
    public int swordDamage = 2;
    public Mob1(int x, int y, GraphicsContext ctx) {
        super(x, y, ctx);
        this.width = 50;
        this.height = 50;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mob/mob1/mob1V2.png")));
        this.imageWidth = 720;
        this.imageHeight = 720;
        this.frameLength = 5;
        this.frameStateIndex = 0;
        this.verticalSpeed = 2;
        Hp = 8;
        attack1 = new SkillTimer(4000, 1500, 1000);
        hitbox = new Hitbox(x, y, width, height);
    }

    @Override
    public void update(double deltaTime) {
        //update vi tri tuong doi khi nhan vat di chuyen
        this.x -= MapMove.offsetX;
        this.y -= MapMove.offsetY;
        attack1.update(deltaTime);
        if(attack1.state == 1){
            critMode();
            EnemyHandle.moveEnemy(this);
            if(EnemyHandle.checkDamage(this) && attack1.makeDamage > 0){
                EnemyHandle.reduceHp(swordDamage);
                attack1.makeDamage = 0;
            }
            //System.out.println("Check");
        }
        else if(attack1.state == 0){
            normMode();
            EnemyHandle.moveEnemy(this);
        } else {
            normMode();
            EnemyHandle.moveRandom(this);
        }

        hitbox.update(x, y);
        EnemyHandle.collisionPlayer(this, deltaTime);

        draw(deltaTime);
        hitbox.draw(ctx);
    }
    protected void critMode(){
        this.verticalSpeed = 6;
        this.frameStateIndex = 1;
    }
    protected void normMode(){
        this.verticalSpeed = 2;
        this.frameStateIndex = 0;
    }
}
