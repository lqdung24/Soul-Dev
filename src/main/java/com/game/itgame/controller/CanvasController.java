package com.game.itgame.controller;

import com.game.itgame.entity.enemy.Boss1;
import com.game.itgame.entity.enemy.EnemyRender;
import com.game.itgame.entity.enemy.Mob1;
import com.game.itgame.entity.enemy.Mob2;
import com.game.itgame.entity.item.Chest;
import com.game.itgame.entity.item.HealthPotion;
import com.game.itgame.entity.player.Player;
import com.game.itgame.eventHandle.EntityHandle;
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
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    public static boolean lose = false, win = false, startCredit = false;
    public Scene playScene;

    public double winTimer = 0, creditY = 650;
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
                if (deltaTime < 1000 / 90) {
                    return;
                }
                lastTime = now;
                if (lose) {
                    loseHandler();
                    return;
                }
                if (win) {
                    winHandler();
                    return;
                }

                ctx.setFill(javafx.scene.paint.Color.BLACK);
                ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

//                Vẽ và cập nhật trạng thái của player map và sword.
                map.mapRender(ctx, player);

                itE = enemies.iterator();
                while (itE.hasNext()) {
                    EnemyRender e = itE.next();
                    e.update(deltaTime);
                    if (e.remove) {
                        itE.remove();
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
                while (healthPotionIterator.hasNext()) {
                    HealthPotion healthPotion = healthPotionIterator.next();
                    healthPotion.update(deltaTime);
                    if (healthPotion.expired()) {
                        healthPotionIterator.remove();
                    }
                }

                player.update(deltaTime);
                if (boss1.Hp <= 0) {
                    win = true;
                }

                if (player.Hp <= 0) {
                    player.update(deltaTime);
                    lose = true;
                }
            }
        };
//        Bắt đầu vòng lặp.
        animation.start();
    }

    public void start() {
        GameImage.get();
        Skill.setGraphicsContext(ctx);
        player = new Player(canvas.getWidth() / 2 - 15, canvas.getHeight() / 2 - 15, ctx);
        map = new MapRender();
        new KeyHandle(playScene);

        addEnemies();

        entityHandle = new EntityHandle(player, map, new Aim(ctx));

        // set thuộc tính cho các trường static
        Bullet.player = player;
    }

    public void restart() {
        map.restart();
        player.restart();
        addEnemies();
        enemyBullets.clear();
    }

    public void loseHandler() {
        ctx.save();
        GameImage.alpha += 0.0005;
        if (GameImage.alpha > 1) {
            GameImage.alpha = 1;
        }
        ctx.setGlobalAlpha(GameImage.alpha);
        ctx.drawImage(GameImage.deadScreen, 0, 0);
        ctx.restore();

        if (KeyHandle.enter) {
            restart();
            lose = false;
            GameImage.alpha = 0;
        } else if (KeyHandle.esc) {
            Platform.exit();
        }
    }

    public void winHandler() {
        if(!startCredit){
            ctx.save();
            GameImage.alpha += 0.0005;
            if (GameImage.alpha > 1) {
                GameImage.alpha = 1;
            }
            ctx.setGlobalAlpha(GameImage.alpha);
            ctx.drawImage(GameImage.winScreen, 0, 0);
            ctx.restore();

            if (KeyHandle.enter) {
                startCredit = true;
            }
        }else{
            ctx.clearRect(0, 0, 1200, 650);
            ctx.fillRect(0, 0, 1200, 650);
            creditY -= 1.5;
            if(creditY < 0){ creditY = 0; }
            ctx.drawImage(GameImage.script, 0, 0, 411, 400, 600 - 400/2, creditY, 400, 600);
            if(KeyHandle.esc){
                Platform.exit();
            }else if(KeyHandle.enter){

            }
        }
    }

    public void addEnemies() {
        enemies.clear();
        chestList.clear();
        healthPotionList.clear();

        // room1
        enemies.add(new Mob1(30, 3, ctx));
        enemies.add(new Mob1(32, 17, ctx));
        enemies.add(new Mob1(45, 9, ctx));
        enemies.add(new Mob1(36, 3, ctx));
        chestList.add(new Chest(36, 9, ctx));

        //room 2
        enemies.add(new Mob2(57, 10, ctx));
        chestList.add(new Chest(60, 4, ctx));

        //room 3
        enemies.add(new Mob1(48, 30, ctx));
        enemies.add(new Mob1(38, 33, ctx));
        enemies.add(new Mob2(25, 30, ctx));
        enemies.add(new Mob2(25, 38, ctx));
        enemies.add(new Mob2(40, 38, ctx));
        chestList.add(new Chest(33, 33, ctx));

        //room 4
        enemies.add(new Mob2(8, 35, ctx));
        enemies.add(new Mob1(9, 27, ctx));
        enemies.add(new Mob1(12, 31, ctx));
        chestList.add(new Chest(15, 36, ctx));

        //room 5
        enemies.add(new Mob1(40, 47, ctx));
        enemies.add(new Mob1(38, 56, ctx));
        enemies.add(new Mob2(29, 45, ctx));
        enemies.add(new Mob2(41, 59, ctx));
        chestList.add(new Chest(45, 45, ctx));
        chestList.add(new Chest(28, 51, ctx));

        //room 6
        enemies.add(new Mob2(57, 42, ctx));
        enemies.add(new Mob2(57, 38, ctx));
        enemies.add(new Mob2(60, 32, ctx));
        enemies.add(new Mob2(66, 32, ctx));
        enemies.add(new Mob2(66, 37, ctx));
        enemies.add(new Mob2(62, 38, ctx));

        //room 7
        chestList.add(new Chest(77, 36, ctx));
        chestList.add(new Chest(77, 37, ctx));
        chestList.add(new Chest(76, 37, ctx));

        //room 8
        boss1 = new Boss1(79, 9, ctx);
        enemies.add(boss1);
        chestList.add(new Chest(71, 6, ctx));
        chestList.add(new Chest(71, 18, ctx));
        chestList.add(new Chest(90, 6, ctx));
        chestList.add(new Chest(90, 18, ctx));
    }
}
