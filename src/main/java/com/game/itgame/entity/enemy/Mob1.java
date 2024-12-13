package com.game.itgame.entity.enemy;

import com.game.itgame.controller.CanvasController;
import com.game.itgame.map.MapRender;
import com.game.itgame.util.GameImage;
import com.game.itgame.util.Hitbox;
import com.game.itgame.eventHandle.EntityHandle;
import com.game.itgame.skill.SkillTimer;
import com.game.itgame.map.MapMove;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Objects;

public class Mob1 extends EnemyRender {
    public int swordDamage = 2;
    public Mob1(int x, int y, GraphicsContext ctx) {
        super(x, y, ctx);
        this.width = 70;
        this.height = 70;
        this.image = GameImage.mob1Image;
        this.imageWidth = 720;
        this.imageHeight = 720;
        this.frameLength = 5;
        this.frameStateIndex = 0;
        this.verticalSpeed = 2;
        Hp = 8;
        attack1 = new SkillTimer(4000, 1500, 1000);
        hitbox = new Hitbox(x, y, width, height-5);
    }

    @Override
    public void update(double deltaTime) {
        //update vi tri tuong doi khi nhan vat di chuyen
        this.x -= MapMove.offsetX;
        this.y -= MapMove.offsetY;
        roomNum = MapRender.getRoomNum(mapX, mapY);
        stop = roomNum != 0 && roomNum != EntityHandle.player.roomNum;

        if(stop){
            System.out.println(roomNum);
            frameStateIndex = 0;
            draw(deltaTime);
            return;
        }

        critMode();
        EntityHandle.moveEnemy(this);

//        attack1.update(deltaTime);
//        if(attack1.state == 1){
//            critMode();
//            EntityHandle.moveEnemy(this);
//            if(EntityHandle.checkDamage(this) && attack1.makeDamage > 0){
//                EntityHandle.reduceHp(swordDamage);
//                attack1.makeDamage = 0;
//            }
//        }
//        else if(attack1.state == 0){
//            normMode();
//            EntityHandle.moveEnemy(this);
//        } else {
//            normMode();
//            EntityHandle.moveRandom(this);
//        }



        hitbox.update(x, y);
        EntityHandle.collisionPlayer(this, deltaTime);

        draw(deltaTime);
        hitbox.draw(ctx);
    }
    @Override
    public void restart(){
        this.x = startX;
        this.y = startY;
        this.mapX = smX;
        this.mapY = smY;
        stop = remove = false;
        Hp = 8;
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
