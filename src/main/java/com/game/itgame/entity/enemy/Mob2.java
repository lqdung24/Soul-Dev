package com.game.itgame.entity.enemy;

import com.game.itgame.entity.Hitbox;
import com.game.itgame.eventHandle.EnemyHandle;
import com.game.itgame.skill.SkillTimer;
import com.game.itgame.map.MapMove;
import com.game.itgame.weapon.arrow.Bullet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.*;

public class Mob2 extends EnemyRender {
    public List<Bullet> bulletList = new ArrayList<>();
    public Mob2(int x, int y, GraphicsContext ctx) {
        super(x, y, ctx);
        this.width = 70;
        this.height = 70;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mob/mob2/mob2.png")));
        this.imageWidth = 720;
        this.imageHeight = 720;
        this.frameLength = 7;
        this.frameStateIndex = 0;
        this.verticalSpeed = 2;
        Hp = 8;
        attack1 = new SkillTimer(2000, true, 2000);
        hitbox = new Hitbox(x, y, width - 20, height - 20);
    }
    @Override
    public void update(double deltaTime, EnemyHandle key, MapMove map) {
        //update vi tri tuong doi khi nhan vat di chuyen
        this.x -= map.getOffsetX();
        this.y -= map.getOffsetY();
        attack1.update(deltaTime);
//        if(attack1.state == 0){ // di chuyển bình thường
//            // stay
//        }
        if(attack1.isAvailabel){
            key.bulletAttack(this, bulletList);
            attack1.isAvailabel = false;
        }
        else if(attack1.state == 2){
            key.moveRandom(this);
        }

        for (int i=0; i<bulletList.size(); i++) {
            if(bulletList.get(i).getIsAttacked()){
                bulletList.remove(i);
            }else {
                bulletList.get(i).render(ctx, map);
            }

        }
        move(key);
        hitbox.update(x + 10, y + 10);
        key.collisionPlayer(this, deltaTime);
        hitbox.draw(ctx);
        draw(deltaTime);
    }
}
