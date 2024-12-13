package com.game.itgame.weapon.bow;

import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.entity.player.Player;
import com.game.itgame.weapon.Weapon;
import com.game.itgame.weapon.arrow.Arrow;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BowAttack extends BowRender implements Weapon {
    public boolean onAttack = false;
    private boolean isCoolDown = false;
    private double timer = 0;
    private final double coolDown = 500;
    public final List<Arrow> arrows = new ArrayList<>();

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
                Arrow arrow = new Arrow(bowX , bowY, angle);

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
        arrows(ctx, deltaTime);
    }
    public void arrows(GraphicsContext ctx, double deltaTime) {
            for (Arrow arrow : arrows) {
                arrow.render(ctx, deltaTime);
                if (arrow.getRemove()) {
                    arrows.remove(arrow);
                    return;
                }
        }
    }

}
