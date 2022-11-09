package com.ae2dms.cw;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class set the overall global feature: stage, color, score, sound and storm
 */
public class GameFeature {

    static Stage stage;
    private static Color backgroundColor;
    static private int score;
    private static boolean sound = true;
    static private boolean storm = false;

    private static GameFeature instance = new GameFeature();
    public static GameFeature getInstance() {
        return instance;
    }


    public static Color getBackgroundColor() {
        return backgroundColor;
    }

    public static void setBackgroundColor(Color backgroundColor) {
        GameFeature.getInstance().backgroundColor = backgroundColor;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        GameFeature.getInstance().score = score;
    }

    public static boolean getSound() {
        return sound;
    }

    public static void setSound(boolean sound) {
        GameFeature.getInstance().sound = sound;
    }

    public static Stage getStage(){
        return stage;
    }

    public static void setStage(Stage sta){
        stage = sta;
    }

    public static void setStorm(boolean stm){
        storm = stm;
    }

    public static boolean getStorm(){
        return storm;
    }
}
