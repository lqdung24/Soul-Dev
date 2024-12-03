package com.game.itgame.weapon.bow;

import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.entity.player.Player;
import com.game.itgame.map.MapMove;
import com.game.itgame.weapon.Weapon;
import com.game.itgame.weapon.arrow.Arrow;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BowAttack extends BowRender implements Weapon {
    private boolean onAttack = false;
    private boolean isCoolDown = false;
    private double timer = 0;
    Iterator<EnemyRender> iterator;
    private final double coolDown = 1000;
    private final List<Arrow> arrows = new ArrayList<>();
    protected MapMove map;

    @Override
    public void attack(GraphicsContext ctx, double deltaTime, Player player, List<EnemyRender> enemies, double angle) {
        ctx.getCanvas().setOnMouseDragged(e -> {
            mouseX = e.getX();
            mouseY = e.getY();

            if(!onAttack && !isCoolDown){
                onAttack = true;
            }
        });

        ctx.getCanvas().setOnMousePressed(e -> {
            mouseX = e.getX();
            mouseY = e.getY();

            if(!onAttack && !isCoolDown){
                onAttack = true;
            }
        });

        if(onAttack) {
            if(timer >= 30) {
                if (bowIndex >= bowImageLength) {
                    bowIndex = bowImageLength;
                } else {
                    bowIndex++;
                }
            } else {
                timer += deltaTime;
            }
        }

        ctx.getCanvas().setOnMouseClicked(e -> {
            if (onAttack && !isCoolDown) {
                Arrow arrow = new Arrow(player.getX(), player.getY(), angle + 45);

                arrows.add(arrow);

                onAttack = false;
                isCoolDown = true;
                bowIndex = 0;
                timer = 0;
            }
        });

        if (timer >= coolDown && !onAttack) {
            timer = 0;
            isCoolDown = false;
        } else {
            timer += deltaTime;
        }

        if (arrows != null) {
            for (Arrow arrow : arrows) {
                if (arrow.getIsAttacked()) {
                    arrow.render(ctx, map);
                    arrows.remove(arrow);
                    return;
                }
                arrow.render(ctx, map);
            }
        }
    }

}
