package com.game.itgame.controller;

import com.game.itgame.entity.enemy.Boss1;
import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.entity.enemy.Mob1;
import com.game.itgame.entity.enemy.Mob2;
import com.game.itgame.entity.item.Chest;
import com.game.itgame.entity.item.HealthPotion;
import com.game.itgame.entity.player.Player;
import com.game.itgame.eventHandle.EntityHandle;
import com.game.itgame.gameStart.Main;
import com.game.itgame.gameStart.RunGame;
import com.game.itgame.skill.Skill;
import com.game.itgame.util.GameImage;
import com.game.itgame.weapon.Aim;
import com.game.itgame.weapon.arrow.Bullet;
import com.game.itgame.eventHandle.KeyHandle;
import com.game.itgame.map.MapRender;
import com.game.itgame.weapon.arrow.FlyThings;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CanvasController {
    private GraphicsContext ctx;
    private Player player;
    private MapRender map;
    private EntityHandle entityHandle;
    public static List<EnemyRender> enemies = new ArrayList<>();
    public static List<Chest> chestList = new ArrayList<>();
    public static List<HealthPotion> healthPotionList = new ArrayList<>();
    public static List<Bullet> enemyBullets = new ArrayList<>();
    private Iterator<Chest> iterator;
    private Iterator<HealthPotion> healthPotionIterator;
    private Iterator<EnemyRender> itE;
    private Boss1 boss1;
    public static boolean lose = false, win = false;
    public Scene playScene;
//    Khai báo canvas.
    @FXML
    public Canvas canvas;

    public void update(Scene scene) {
        ctx = canvas.getGraphicsContext2D();
        this.playScene = scene;
        start();

//        Tao vòng lặp để vẽ và cập nhật trạng thái của player map và sword.
        AnimationTimer animation = new AnimationTimer() {
            private long lastTime = 0;
            @Override
            public void handle(long now) {
                double deltaTime = (now - lastTime) / 1000000.0;
                if(deltaTime < 1000/ 90){
                    return;
                }
                lastTime = now;

                if(lose){
                    loseHanler();
                    return;
                }

                if(win){
                    winHandler();
                    return;
                }

                ctx.setFill(javafx.scene.paint.Color.BLACK);
                ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

//                Vẽ và cập nhật trạng thái của player map và sword.
                map.mapRender(ctx, player);

                itE = enemies.iterator();
                while(itE.hasNext()){
                    EnemyRender e = itE.next();
                    if(!e.remove){
                        e.update(deltaTime);
                    }
                }
                FlyThings.bulletDraw(ctx, deltaTime);
                iterator = chestList.iterator();

                while (iterator.hasNext()) {
                    Chest chest = iterator.next();
                    if (!chest.remove) {
                        chest.update(deltaTime);
                    }
                }

                healthPotionIterator = healthPotionList.iterator();
                while(healthPotionIterator.hasNext()) {
                    HealthPotion healthPotion = healthPotionIterator.next();
                    healthPotion.update(deltaTime);
                    if (healthPotion.expired()) {
                        healthPotionIterator.remove();
                    }
                }

                player.update(deltaTime);
                if(boss1.Hp <= 0){
                    win = true;
                }
                if(player.Hp <= 0){
                    player.update(deltaTime);
                    lose = true;
                }
            }
        };
//        Bắt đầu vòng lặp.
        animation.start();
    }

    public void start(){
        GameImage.get();
        Skill.setGraphicsContext(ctx);
        player = new Player(canvas.getWidth() / 2 - 15, canvas.getHeight() / 2 - 15, ctx);
        boss1 = new Boss1(7, 6, ctx);
        map = new MapRender();
        new KeyHandle(playScene);
        for (int i = 0; i < 1; i++) {
            int randomX = (int) (Math.random()*30) - 5;
            int randomY = (int) (Math.random()*30) - 5;

             enemies.add(new Mob2(5, 5, ctx));
             enemies.add(new Mob1(5, 6, ctx));
        }

        //enemies.add(new Mob1(16, 16, ctx));
        enemies.add(boss1);
        entityHandle = new EntityHandle(player, map, new Aim(ctx));
        chestList.add(new Chest(6, 6, ctx));
        chestList.add(new Chest(25, 25, ctx));

        // set thuộc tính cho các trường static
        Bullet.player = player;
    }

    public void restart(){
        map.restart();
        player.restart();
        enemies.forEach(e -> e.restart());
    }
    public void loseHanler(){
        ctx.save();
        GameImage.alpha += 0.0005;
        if(GameImage.alpha > 1){
            GameImage.alpha = 1;
        }
        ctx.setGlobalAlpha(GameImage.alpha);
        ctx.drawImage(GameImage.deadScreen, 0, 0);
        ctx.restore();

        if(KeyHandle.enter){
            restart();
            lose = false;
            GameImage.alpha = 0;
        }else if(KeyHandle.esc){
            Platform.exit();
        }
    }
    public void winHandler(){
        ctx.save();
        GameImage.alpha += 0.0005;
        if(GameImage.alpha > 1){
            GameImage.alpha = 1;
        }
        ctx.setGlobalAlpha(GameImage.alpha);
        ctx.drawImage(GameImage.winScreen, 0, 0);
        ctx.restore();

        if(KeyHandle.enter){
            restart();
            win = false;
            GameImage.alpha = 0;
        }
    }
}