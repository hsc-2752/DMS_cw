package com.ae2dms.cw.controller;

import com.ae2dms.cw.GameFeature;
import com.ae2dms.cw.consts.FxmlConst;
import com.ae2dms.cw.scene.SwitchScene;
import com.ae2dms.cw.util.SoundEffect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Controller for the start scene
 */
public class StartSceneController {

    @FXML
    public AnchorPane StartScene;

    @FXML
    public ColorPicker myColorPicker;
    /**
     * change the color of background of the whole game
     * @param event color picker event
     */
    public void changeColor(ActionEvent event) {
        GameFeature.setBackgroundColor(myColorPicker.getValue());
    }

    /**
     * switch to level choose scene
     * @param event
     */
    public void toLevelChooseSceneScene(ActionEvent event){
        SwitchScene.switchScene(FxmlConst.LEVELSCENE);
    }

    /**
     * switch to storm mode scene
     * @param event
     */
    public void toStormModeScene(ActionEvent event) {
        SwitchScene.buildGame(1,true,3,0);

    }

    /**
     * switch to info scene
     * @param event
     */
    public void toInfoScene(ActionEvent event) {
        SwitchScene.switchScene(FxmlConst.INFOSCENE);
    }

    /**
     * Open sound
     * @param event
     */
    public void OpenSound(ActionEvent event){
        Button button = (Button)event.getSource();
        button.setText(GameFeature.getSound()?"OFF":"ON");
        if (GameFeature.getSound()){
            SoundEffect.MAIN.stop();
        }else{
            SoundEffect.MAIN.play();
        }
        GameFeature.setSound(!GameFeature.getSound());
    }
}
