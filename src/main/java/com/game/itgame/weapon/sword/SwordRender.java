package com.game.itgame.weapon.sword;

import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.entity.player.Player;
import com.game.itgame.weapon.Weapon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;

public abstract class SwordRender implements Weapon {
    protected Image swordImage;
    protected int swordIndex = 0;
    protected int swordImageLength;
    private double mouseX;
    private double mouseY;

    @Override
    public void draw(GraphicsContext ctx, Player player, List<EnemyRender> enemies, double deltaTime) {
        ctx.getCanvas().setOnMouseMoved(e -> {
            mouseX = e.getX();
            mouseY = e.getY();
        });

        double angle = Math.toDegrees(Math.atan2(player.getX() + 25  - mouseX, mouseY - 40 - player.getY()) + Math.PI / 2);
//        Check attack.
        attack(ctx, deltaTime, player, enemies, angle);

//        Draw weapon.
        ctx.save();
        ctx.translate(player.hitbox.getCenterX(), player.hitbox.getCenterY() + 13);
        ctx.rotate(angle);
        ctx.drawImage(swordImage, swordIndex * 720, 0, 720, 720, -15, -30, 50, 50);
        ctx.restore();
    }
}
