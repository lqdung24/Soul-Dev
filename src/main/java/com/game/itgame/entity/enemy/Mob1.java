package com.game.itgame.entity.enemy;

import com.game.itgame.entity.player.Player;
import com.game.itgame.eventHandle.EnemyHandle;
import com.game.itgame.eventHandle.Skill;
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
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mob/mob1/mob1.png")));
        this.imageWidth = 720;
        this.imageHeight = 720;
        this.frameLength = 3;
        this.frameStateIndex = 0;
        this.verticalSpeed = 2;
        Hp = 8;
        attack1 = new Skill(this, 4000, 1500, 1000);
    }

    @Override
    public void update(double deltaTime, EnemyHandle key, MapMove map) {
        //update vi tri tuong doi khi nhan vat di chuyen
        this.x -= map.getOffsetX();
        this.y -= map.getOffsetY();
        attack1.update(deltaTime);
        if(attack1.state == 1){
            critMode();
            key.moveEnemy(this);
            if(key.checkDamage(this) && attack1.makeDamage > 0){
                key.reduceHp(swordDamage);
                attack1.makeDamage--;
            }
            //System.out.println("Check");
        }
        else if(attack1.state == 0){
            normMode();
            key.moveEnemy(this);
        } else {
            normMode();
            key.moveRandom(this);
        }

        move(key);
        key.collisionPlayer(this, deltaTime);

        draw(deltaTime);
    }
    protected void critMode(){
        this.verticalSpeed = 8;
    }
    protected void normMode(){
        this.verticalSpeed = 2;
    }
}
