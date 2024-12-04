package com.game.itgame.weapon.bow;

import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.entity.player.Player;
import com.game.itgame.weapon.Weapon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.List;

public abstract class BowRender implements Weapon {
    protected Image bowImage;
    protected int bowIndex = 0;
    protected int bowImageLength;
    protected double mouseX;
    protected double mouseY;
    List<EnemyRender> enemies;

    @Override
    public void draw(GraphicsContext ctx, Player player, List<EnemyRender> enemies, double deltaTime) {
        ctx.getCanvas().setOnMouseMoved(e -> {
            mouseX = e.getX();
            mouseY = e.getY();
        });

        double angle = Math.toDegrees(Math.atan2(player.getX() + 25  - mouseX, mouseY - 40 - player.getY()) + Math.PI / 2);
//      Check attack.
        attack(ctx, deltaTime, player, enemies, angle);

//      Draw weapon.
        ctx.save();
        ctx.translate(player.getX() + 25, player.getY() + 40);
        ctx.rotate(angle);
        ctx.drawImage(bowImage, bowIndex * 720, 0, 720, 720, -15, -25, 50, 50);
        ctx.restore();
    }
}
