package com.game.itgame.weapon;

import com.game.itgame.entity.Enemy.EnemyRender;
import com.game.itgame.entity.player.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;

public abstract class WeaponRender implements Weapon {
    protected Image weaponImage;
    protected int weaponIndex = 0;
    protected int weaponImageLength;
    private double mouseX;
    private double mouseY;

    @Override
    public void draw(GraphicsContext ctx, Player player, double deltaTime, List<EnemyRender> enemies) {
        ctx.getCanvas().setOnMouseMoved(e -> {
            mouseX = e.getX();
            mouseY = e.getY();
        });

        double angle = Math.toDegrees(Math.atan2(player.getX() + 15  - mouseX, mouseY - 20 - player.getY()) + Math.PI / 2);
//        Check attack.
        attack(ctx, deltaTime,player, enemies, angle);

//        Draw weapon.
        ctx.save();
        ctx.translate(player.getX() + 15, player.getY() + 20);
        ctx.rotate(angle);
        ctx.drawImage(weaponImage, weaponIndex * 480, 0, 480, 480, -15, -35, 70, 70);
        ctx.restore();
    }
}
