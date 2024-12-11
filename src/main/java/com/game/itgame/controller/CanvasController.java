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
import com.game.itgame.weapon.Aim;
import com.game.itgame.weapon.arrow.Bullet;
import com.game.itgame.eventHandle.KeyHandle;
import com.game.itgame.map.MapRender;
import com.game.itgame.weapon.arrow.FlyThings;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CanvasController {
    private GraphicsContext ctx;
    private Player player;
    private MapRender map;
    //private KeyHandle key;
    private EntityHandle entityHandle;
    public static List<EnemyRender> enemies = new ArrayList<>();
    public static List<Chest> chestList = new ArrayList<>();
    public static List<HealthPotion> healthPotionList = new ArrayList<>();
    public static List<Bullet> enemyBullets = new ArrayList<>();
    private Iterator<Chest> iterator;
    private Iterator<HealthPotion> healthPotionIterator;
    private Boss1 boss1;
    public static boolean die = false;
    public Scene scene;
    AnimationTimer animation;
    public Stage primaryStage;
//    Khai báo canvas.
    @FXML
    public Canvas canvas;

    public void update(Scene scene, Stage primaryStage) {
        ctx = canvas.getGraphicsContext2D();
        this.scene = scene;
        this.primaryStage = primaryStage;
        start();

//        Tao vòng lặp để vẽ và cập nhật trạng thái của player map và sword.
        animation = new AnimationTimer() {
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
                    map.mapRender(ctx, player);

                    Iterator<EnemyRender> itE = enemies.iterator();
                    while(itE.hasNext()){
                        EnemyRender e = itE.next();
                        if(e.Hp <= 0){
                            itE.remove();
                        }else{
                            e.update(deltaTime);
                        }
                    }
                    FlyThings.bulletDraw(ctx, deltaTime);
                    iterator = chestList.iterator();
                    while (iterator.hasNext()) {
                        Chest chest = iterator.next();
                        chest.update(deltaTime);
                        if (chest.remove) {
                            iterator.remove();
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
                    if(player.Hp <= 0){
                        player.update(deltaTime);
                        die = true;
                        ctx.fillText("You die!!!", 500, 500, 500);
                        stop();
                    }
                }

            }
        };
//        Bắt đầu vòng lặp.
        animation.start();
    }

    public void start(){
        Skill.setGraphicsContext(ctx);
        player = new Player(canvas.getWidth() / 2 - 15, canvas.getHeight() / 2 - 15, ctx);
        boss1 = new Boss1(20, 20, ctx);
        map = new MapRender();
        new KeyHandle(scene);
        for (int i = 0; i < 1; i++) {
            int randomX = (int) (Math.random()*30) - 5;
            int randomY = (int) (Math.random()*30) - 5;
            //enemies.add(new Ghost( randomX, randomY, ctx));

            // enemies.add(new Mob2(randomX, randomY, ctx));
        }

        //enemies.add(new Mob1(16, 16, ctx));
        enemies.add(boss1);
        entityHandle = new EntityHandle(player, map, new Aim(ctx));
        chestList.add(new Chest(17, 17, ctx));
        chestList.add(new Chest(25, 25, ctx));

        // set thuộc tính cho các trường static
        Bullet.player = player;
    }
}