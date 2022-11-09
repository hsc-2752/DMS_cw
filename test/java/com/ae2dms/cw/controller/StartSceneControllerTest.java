package com.ae2dms.cw.controller;

import com.ae2dms.cw.GameFeature;
import com.ae2dms.cw.consts.FxmlConst;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;


class StartSceneControllerTest extends ApplicationTest {
    StartSceneController controller;
    Parent root;
    Stage stage;

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlConst.STARTSCENE));
        root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
        controller = loader.getController();
        this.stage = stage;
    }

    @Test
    public void testStartClick(){
        clickOn("#start");
    }
    @Test
    public void testStormClick(){
        clickOn("#storm");
    }
    @Test
    public void testInfoClick(){
        clickOn("#info");
    }
    @Test
    public void testSoundClick(){
        clickOn("#sound");

    }


}