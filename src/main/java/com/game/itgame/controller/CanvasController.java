package com.game.itgame.controller;

import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.entity.enemy.Mob1;
import com.game.itgame.entity.enemy.Mob2;
import com.game.itgame.entity.enemy.Mob3;
import com.game.itgame.entity.player.Player;
import com.game.itgame.eventHandle.EnemyHandle;
import com.game.itgame.weapon.Aim;
import com.game.itgame.weapon.arrow.Bullet;
import com.game.itgame.weapon.bow.Bow;
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
    private Bow bow;
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
        bow = new Bow(map, enemies);
        key = new KeyHandle(scene);
        for (int i = 0; i < 1; i++) {
            int randomX = (int) (Math.random()*30) - 5;
            int randomY = (int) (Math.random()*30) - 5;
            //enemies.add(new Ghost( randomX, randomY, ctx));

            if(i % 2 == 0){
                enemies.add(new Mob2(randomX, randomY, ctx));
            }else{
                enemies.add(new Mob1(randomX, randomY, ctx)); // toa d
            }
        }
        enemies.add(new Mob1(16, 16, ctx));
        enemies.add(new Mob3(18, 18, ctx));
        move = new EnemyHandle(player, map, new Aim(ctx));
        Bullet.player = player;
//        Tao vòng lặp để vẽ và cập nhật trạng thái của player map và sword.
        AnimationTimer animation = new AnimationTimer() {
            private long lastTime = 0;
            @Override
            public void handle(long now) {
//                Tính thời gian giữa 2 frame.
                double deltaTime = (now - lastTime) / 1000000.0;
                lastTime = now;
                if(deltaTime >= 1000/90){
                    //                Set màu nền cho canvas.
                    ctx.setFill(javafx.scene.paint.Color.BLACK);
                    ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

//                Vẽ và cập nhật trạng thái của player map và sword.
                    map.mapRender(ctx, player, key);
                    player.update(deltaTime, key);
                    if(!enemies.isEmpty()) {
                        enemies.forEach(enemy -> enemy.update(deltaTime, move, map));
                    }

                    if(key.firstWeapon){
                        sword.draw(ctx, player, enemies, deltaTime);
                    }else{
                        bow.draw(ctx, player, enemies, deltaTime);
                    }
                    if(player.Hp <= 0){
                        player.update(deltaTime, key);
                        System.out.println("You Die");
                        ctx.fillText("You die!!!", 500, 500, 500);
                        this.stop();
                    }
                }

            }
        };

//        Bắt đầu vòng lặp.
        animation.start();
    }
}