package com.ae2dms.cw;

import com.ae2dms.cw.consts.FxmlConst;
import com.ae2dms.cw.consts.SceneConst;
import com.ae2dms.cw.util.SoundEffect;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * This class is the entrance of the game, displaying the startScene page
 * @author Shuchang HUANG
 */

public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(FxmlConst.STARTSCENE));
        Scene scene = new Scene(fxmlLoader.load(), SceneConst.WIDTH * SceneConst.UNIT_SIZE, SceneConst.HEIGHT * SceneConst.UNIT_SIZE);
        stage.setTitle("Bubble Bobbles");
        SoundEffect.MAIN.play();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest((event) -> Platform.exit());
        GameFeature.setStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}