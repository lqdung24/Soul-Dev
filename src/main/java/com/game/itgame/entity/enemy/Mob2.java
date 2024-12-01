package com.game.itgame.entity.enemy;

import com.game.itgame.eventHandle.EnemyHandle;
import com.game.itgame.eventHandle.Skill;
import com.game.itgame.map.MapMove;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Objects;

public class Mob2 extends EnemyRender {

    public Mob2(int x, int y, GraphicsContext ctx) {
        super(x, y, ctx);
        this.width = 60;
        this.height = 60;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mob/mob2/mob2walk.png")));
        this.imageWidth = 720;
        this.imageHeight = 720;
        this.frameLength = 3;
        this.frameStateIndex = 0;
        this.verticalSpeed = 2;
        Hp = 8;
        attack1 = new Skill(this, 2000, 0, 2000);
    }
    @Override
    public void update(double deltaTime, EnemyHandle key, MapMove map) {
        //update vi tri tuong doi khi nhan vat di chuyen
        this.x -= map.getOffsetX();
        this.y -= map.getOffsetY();
        attack1.update(deltaTime);
        if(attack1.state == 1){ // chưa thêm tấn công bằng đạn
            key.moveEnemy(this);
            if(key.checkDamage(this) && attack1.makeDamage > 0){
                key.reduceHp(2);
                attack1.makeDamage--;
            }
            //System.out.println("Check");
        }
        else if(attack1.state == 0){
            key.moveEnemy(this);
        } else {
            key.moveRandom(this);
        }

        move(key);
        key.collisionPlayer(this, deltaTime);

        draw(deltaTime);
    }

    protected void critMode(){
        this.verticalSpeed = 8;
        this.frameStateIndex = 1;
    }
    protected void normMode(){
        this.verticalSpeed = 2;
        this.frameStateIndex = 0;
    }
}
