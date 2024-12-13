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
    protected double bowX;
    protected double bowY;

    @Override
    public void draw(GraphicsContext ctx, Player player, List<EnemyRender> enemies, double deltaTime) {
        ctx.getCanvas().setOnMouseMoved(e -> {
            mouseX = e.getX();
            mouseY = e.getY();
        });

        bowX = player.hitbox.getCenterX() + 10;
        bowY = player.hitbox.getCenterY() + 5;

        double angle = Math.toDegrees(Math.atan2(bowX  - mouseX, mouseY - bowY) + Math.PI / 2);
//      Check attack.
        attack(ctx, deltaTime, player, enemies, angle);

//      Draw weapon.
        ctx.save();
        ctx.translate(bowX - 10, bowY-2);
        ctx.rotate(angle);
        ctx.drawImage(bowImage, bowIndex * 720, 0, 720, 720, -25, -35, 70, 70);
        ctx.restore();
    }
}
