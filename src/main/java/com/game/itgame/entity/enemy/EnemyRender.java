package com.game.itgame.entity.enemy;

import com.game.itgame.entity.EntityInterface;
import com.game.itgame.entity.EntityRender;
import com.game.itgame.eventHandle.EntityHandle;
import com.game.itgame.map.MapRender;
import com.game.itgame.skill.SkillTimer;
import com.game.itgame.map.MapMove;
import javafx.scene.canvas.GraphicsContext;

public class EnemyRender extends EntityRender implements EntityInterface {
    protected double time = 0;
    protected int collisionDamage = 1;
    public double collisionTimer = 0;
    public double mapX, mapY, smX, smY;
    public SkillTimer attack1;
    public boolean moveLeft = false, moveRight = false;
    protected double startX, startY;

    public EnemyRender(int x, int y, GraphicsContext ctx) {
        this.x = ctx.getCanvas().getWidth() / 2 - 15 - 2 * MapRender.mapFrameSize + x * MapRender.mapFrameSize;
        this.y = ctx.getCanvas().getHeight() / 2 - 15 - 2 * MapRender.mapFrameSize + y * MapRender.mapFrameSize;
        mapX = x * MapRender.mapFrameSize;
        mapY = y * MapRender.mapFrameSize;
        startX = this.x;
        startY = this.y;
        smX = mapX;
        smY = mapY;
        this.ctx = ctx;
    }

    @Override
    public void update(double deltaTime) {
        this.x -= MapMove.offsetX;
        this.y -= MapMove.offsetY;
        if(!stop){
            EntityHandle.moveEnemy(this);
            EntityHandle.collisionPlayer(this, deltaTime);
        }
        draw(deltaTime);
    }

    @Override
    public void draw(double deltaTime) {
        ctx.drawImage(image, frameIndex * imageWidth, frameStateIndex * imageHeight, imageWidth, imageHeight, x, y, width, height);

//        Chỉnh thơi gian chuyển frame
        if (time > 150) {
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

    public double getSpeed() {
        return verticalSpeed;
    }
    public int getCollisionDamage() {
        return collisionDamage;
    }

}
