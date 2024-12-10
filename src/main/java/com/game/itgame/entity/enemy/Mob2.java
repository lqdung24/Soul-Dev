package com.game.itgame.entity.enemy;

import com.game.itgame.util.Hitbox;
import com.game.itgame.eventHandle.EntityHandle;
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
        this.width = 80;
        this.height = 80;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mob/mob2/mob2.png")));
        this.imageWidth = 720;
        this.imageHeight = 720;
        this.frameLength = 7;
        this.frameStateIndex = 0;
        this.verticalSpeed = 1.5;
        Hp = 8;
        attack1 = new SkillTimer(2000, true, 2000);
        hitbox = new Hitbox(x, y, width - 20, height - 20);
    }

    @Override
    public void update(double deltaTime) {
        //update vi tri tuong doi khi nhan vat di chuyen
        this.x -= MapMove.offsetX;
        this.y -= MapMove.offsetY;

        EntityHandle.moveEnemy(this);
        hitbox.update(x + 10, y + 10);
        attack1.update(deltaTime);
//        if(attack1.state == 0){ // di chuyển bình thường
//            // stay
//        }
        if(attack1.isAvailabel){
            EntityHandle.bulletAttack(this, bulletList);
            attack1.isAvailabel = false;
        }
        else if(attack1.state == 2){
            EntityHandle.moveRandom(this);
        }

        bulletDraw(ctx, deltaTime);

        EntityHandle.collisionPlayer(this, deltaTime);
        hitbox.draw(ctx);
        draw(deltaTime);
    }

    public void bulletDraw(GraphicsContext ctx, double deltaTime) {
        for (int i=0; i<bulletList.size(); i++) {
            if(bulletList.get(i).getIsAttacked()){
                bulletList.remove(i);
            }else {
                bulletList.get(i).render(ctx, deltaTime);
            }

        }
    }
}
