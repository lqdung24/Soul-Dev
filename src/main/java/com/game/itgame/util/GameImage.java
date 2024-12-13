package com.game.itgame.util;

import javafx.scene.image.Image;

import java.util.Objects;

public class GameImage {
    public static Image bossSkillImage, bossImage, mob1Image, mob2Image, chestImage, potionImage,
                        deadScreen, winScreen, deadmob, script;
    public static double alpha = 0;
    public static double deadWidth, deadHeight, winWidth, winHeight;
    public GameImage() {
    }
    public static void get(){
        bossSkillImage = new Image(Objects.requireNonNull(GameImage.class.getResourceAsStream("/images/mob/boss1/bossskill.png")));
        bossImage = new Image(Objects.requireNonNull(GameImage.class.getResourceAsStream("/images/mob/boss1/boss.png")));
        mob1Image = new Image(Objects.requireNonNull(GameImage.class.getResourceAsStream("/images/mob/mob1/mob1V2.png")));
        mob2Image = new Image(Objects.requireNonNull(GameImage.class.getResourceAsStream("/images/mob/mob2/mob2.png")));
        chestImage = new Image(Objects.requireNonNull(GameImage.class.getResourceAsStream("/images/items/CHEST.png")));
        potionImage = new Image(Objects.requireNonNull(GameImage.class.getResourceAsStream("/images/items/healthpoison.png")));
        deadScreen = new Image(Objects.requireNonNull(GameImage.class.getResourceAsStream("/images/screen/thua.png")));
        winScreen = new Image(Objects.requireNonNull(GameImage.class.getResourceAsStream("/images/screen/thang.png")));
        deadmob = new Image(Objects.requireNonNull(GameImage.class.getResourceAsStream("/images/mob/mobdeath.png")));
        script = new Image(Objects.requireNonNull(GameImage.class.getResourceAsStream("/images/screen/credit.png")));
    }
}

