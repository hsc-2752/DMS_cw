package com.ae2dms.cw.scene;


import com.ae2dms.cw.GameFeature;
import com.ae2dms.cw.Main;
import com.ae2dms.cw.consts.FxmlConst;
import com.ae2dms.cw.consts.SceneConst;
import com.ae2dms.cw.util.SoundEffect;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This mathod of switch game scene
 * Used for display main scene and switch scenes
 */
public class SwitchScene {
    private static Scene scene;
    private static Parent root;

    /**
     * Display main scene
     */
    public static void mainScene(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(FxmlConst.STARTSCENE));
            Scene scene = new Scene(fxmlLoader.load(), SceneConst.WIDTH * SceneConst.UNIT_SIZE, SceneConst.HEIGHT * SceneConst.UNIT_SIZE);
            //play main sound
            SoundEffect.MAIN.play();
            Stage stage = GameFeature.getStage();
            stage.setTitle(SceneConst.TITLE);
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest((event) -> Platform.exit());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * @param url: the scene url to switch
     * Display the destined scene
     */
    public static void switchScene(String url) {
        try {
            root = FXMLLoader.load(Main.class.getResource(url));
            Stage stage = GameFeature.getStage();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Build main scene,set game feature and call start game
     * @param level set the level
     * @param storm determines if it is storm mode
     * @param life gives hero life value
     * @param score give initial score
     */
    public static void buildGame(int level,boolean storm,int life,int score){
        MainScene mainScene = new MainScene();
        GameFeature.setStorm(storm);
        mainScene.startGame(level,life,score,storm);
    }

    /**
     * get the root
     * @return
     */

    public static Parent getRoot() {
        return root;
    }
}
