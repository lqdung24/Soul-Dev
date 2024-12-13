package com.game.itgame.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.nio.file.Paths;

public class GameSound {
    public static MediaPlayer bowSound = new MediaPlayer(new Media(
                                    Paths.get("src/main/resources/sound/bow.wav").toUri().toString())),
                swordSound = new MediaPlayer(new Media(
                                    Paths.get("src/main/resources/sound/sword.wav").toUri().toString())),
                playerHitted = new MediaPlayer(new Media(
                        Paths.get("src/main/resources/sound/playergethit.wav").toUri().toString())),
                mobHitted = new MediaPlayer(new Media(
                        Paths.get("src/main/resources/sound/mobgethit.wav").toUri().toString()));

    public static void playBow(){
        //System.out.println("Playing bow...");
        bowSound.stop();
        bowSound.play();
    }
    public static void playSword(){
        //System.out.println("sword");
        swordSound.stop();
        swordSound.play();
    }
    public static void playerHitted(){
        playerHitted.stop();
        playerHitted.play();
    }
    public static void mobHitted(){
        mobHitted.stop();
        mobHitted.play();
    }

}
