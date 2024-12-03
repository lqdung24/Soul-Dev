package com.game.itgame.entity.enemy;

import com.game.itgame.eventHandle.EnemyHandle;
import com.game.itgame.eventHandle.Skill;
import com.game.itgame.map.MapMove;
import com.game.itgame.weapon.arrow.Arrow;
import com.game.itgame.weapon.arrow.Bullet;
import com.game.itgame.weapon.arrow.FlyThings;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Mob2 extends EnemyRender {
    public List<Bullet> bulletList = new ArrayList<>();
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
        attack1 = new Skill(this, 2000, true, 2000);
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
        if(attack1.isAttack){
            key.bulletAttack(this, bulletList);
            attack1.isAttack = false;
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
        key.collisionPlayer(this, deltaTime);

        draw(deltaTime);
    }
}
