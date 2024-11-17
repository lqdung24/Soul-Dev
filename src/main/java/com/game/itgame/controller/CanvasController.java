package com.game.itgame.controller;

import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.entity.enemy.Mob1;
import com.game.itgame.entity.enemy.Mob2;
import com.game.itgame.entity.player.Player;
import com.game.itgame.eventHandle.EnemyHandle;
import com.game.itgame.weapon.sword.Sword;
import com.game.itgame.eventHandle.KeyHandle;
import com.game.itgame.map.MapRender;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class CanvasController {
    private GraphicsContext ctx;
    private Player player;
    private Sword sword;
    private MapRender map;
    private KeyHandle key;
    private EnemyHandle move;
    private final List<EnemyRender> enemies = new ArrayList<>();

//    Khai báo canvas.
    @FXML
    private Canvas canvas;


    public void update(Scene scene) {
        ctx = canvas.getGraphicsContext2D();
        player = new Player(canvas.getWidth() / 2 - 15, canvas.getHeight() / 2 - 15, ctx);
        map = new MapRender();
        sword = new Sword();
        key = new KeyHandle(scene);
        for (int i = 0; i < 8; i++) {
            int randomX = (int) (Math.random()*30) - 5;
            int randomY = (int) (Math.random()*30) - 5;
            //enemies.add(new Ghost( randomX, randomY, ctx));

            if(i % 2 == 0){
                enemies.add(new Mob2(randomX, randomY, ctx));
            }else{
                enemies.add(new Mob1(randomX, randomY, ctx)); // toa d
            }
        }
        //enemies.add(new Mob1(canvas.getWidth()/2-15-2*30 + 16*30, canvas.getHeight()/2-15-2*30 + 16*30, ctx)); // toa do: (16,16)

        move = new EnemyHandle(player, map);
//        map.setGhost(ghost);

//        Tao vòng lặp để vẽ và cập nhật trạng thái của player map và sword.
        AnimationTimer animation = new AnimationTimer() {
            private long lastTime = 0;
            @Override
            public void handle(long now) {
//                Tính thời gian giữa 2 frame.
                double deltaTime = (now - lastTime) / 1000000.0;
                lastTime = now;

//                Set màu nền cho canvas.
                ctx.setFill(javafx.scene.paint.Color.BLACK);
                ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

//                Vẽ và cập nhật trạng thái của player map và sword.
                map.mapRender(ctx, player, key);
                player.update(deltaTime, key);
                enemies.forEach(enemy -> enemy.update(deltaTime, move, map));
                sword.draw(ctx, player, enemies, deltaTime);

                if(player.Hp <= 0){
                    player.update(deltaTime, key);
                    System.out.println("You Die");
                    ctx.fillText("You die!!!", 500, 500, 500);
                    this.stop();
                }
            }
        };

//        Bắt đầu vòng lặp.
        animation.start();
    }
}