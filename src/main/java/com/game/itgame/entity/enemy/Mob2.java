package com.game.itgame.entity.enemy;

import com.game.itgame.map.MapRender;
import com.game.itgame.util.GameImage;
import com.game.itgame.util.Hitbox;
import com.game.itgame.eventHandle.EntityHandle;
import com.game.itgame.skill.SkillTimer;
import com.game.itgame.map.MapMove;
import javafx.scene.canvas.GraphicsContext;


public class Mob2 extends EnemyRender {
    public Mob2(int x, int y, GraphicsContext ctx) {
        super(x, y, ctx);
        this.width = 80;
        this.height = 80;
        this.image = GameImage.mob2Image;
        this.imageWidth = 720;
        this.imageHeight = 720;
        this.frameLength = 7;
        this.frameStateIndex = 0;
        this.verticalSpeed = 1.5;
        Hp = 8;
        attack1 = new SkillTimer(500, true, 1500);
        hitbox = new Hitbox(x, y, width - 20, height - 20);
    }

    @Override
    public void update(double deltaTime) {
        //update vi tri tuong doi khi nhan vat di chuyen
        this.x -= MapMove.offsetX;
        this.y -= MapMove.offsetY;
        roomNum = MapRender.getRoomNum(mapX, mapY);
        stop = roomNum != 0 && roomNum != EntityHandle.player.roomNum;

        if(stop){
            if(die){
                ctx.drawImage(GameImage.deadmob, 0, 0, 480, 480, x, y, 45, 45);
                return;
            }
            frameStateIndex = 0;
            draw(deltaTime);
            return;
        }
        if(die){
            ctx.drawImage(GameImage.deadmob, 0, 0, 480, 480, x, y, 45, 45);
            return;
        }

        EntityHandle.moveEnemy(this);
        hitbox.update(x + 10, y + 10);
        attack1.update(deltaTime);
//        if(attack1.state == 0){ // di chuyển bình thường
//            // stay
//        }
        if(attack1.isAvailabel){
            EntityHandle.bulletMob2Attack(this);
            attack1.isAvailabel = false;
        }
        else if(attack1.state == 2){
            EntityHandle.moveRandom(this);
        }

        EntityHandle.collisionPlayer(this, deltaTime);
        hitbox.draw(ctx);
        draw(deltaTime);
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
}
