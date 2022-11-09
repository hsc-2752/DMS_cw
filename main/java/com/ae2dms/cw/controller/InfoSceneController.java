package com.ae2dms.cw.controller;


import com.ae2dms.cw.scene.SwitchScene;
import javafx.event.ActionEvent;

/**
 * controller class of the info scene,only has one instance
 */
public class InfoSceneController {

    /**
     * back to main scene
     * @param event color picker event
     */
    public void toStartScene(ActionEvent event) {
        SwitchScene.mainScene();
    }
}
