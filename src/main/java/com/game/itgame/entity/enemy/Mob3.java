package com.game.itgame.entity.enemy;

import com.game.itgame.eventHandle.EntityHandle;
import com.game.itgame.skill.SkillTimer;
import com.game.itgame.map.MapMove;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Objects;

public class Mob3 extends EnemyRender {

    public Mob3(int x, int y, GraphicsContext ctx) {
        super(x, y, ctx);
        this.width = 50;
        this.height = 50;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mob/mob3.png")));
        this.imageWidth = 720;
        this.imageHeight = 720;
        this.frameLength = 3;
        this.frameStateIndex = 0;
        this.verticalSpeed = 2;
        Hp = 8;
        attack1 = new SkillTimer(4000, 1500, 1000);
    }

    @Override
    public void update(double deltaTime) {
        //update vi tri tuong doi khi nhan vat di chuyen
        this.x -= MapMove.offsetX;
        this.y -= MapMove.offsetY;


        attack1.update(deltaTime);

        if(attack1.state == 0){
            // stay
        } else if(attack1.state == 1){
            EntityHandle.moveEnemy(this);
        } else if(attack1.state == 2){

        }

        EntityHandle.collisionPlayer(this, deltaTime);
        draw(deltaTime);
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
