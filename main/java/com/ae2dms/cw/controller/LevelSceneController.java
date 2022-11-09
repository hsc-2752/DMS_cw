package com.ae2dms.cw.controller;


import com.ae2dms.cw.scene.SwitchScene;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * controller class of the level scene,only has one instance
 */
public class LevelSceneController {

    @FXML
    private ImageView level1;

    @FXML
    private ImageView level2;

    @FXML
    private ImageView level3;

    @FXML
    void toLevel(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        toLevelAction(imageView);
    }

    /**
     * responding to enter different level
     * @param level
     */
    private void toLevelAction(ImageView level){
        int lv = 1;
        if (level1.equals(level)) {
            lv = 1;
        } else if (level2.equals(level)) {
            lv = 2;
        } else if (level3.equals(level)) {
            lv = 3;
        }
        SwitchScene.buildGame(lv,false,3,0);
    }

    /**
     * mouse entering
     * @param event mouse enter
     */
    @FXML
    void mouseEnter(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        setMouseEvent(imageView,false);
    }

    /**
     * mouse leaving
     * @param event mouse leave
     */
    @FXML
    void mouseLeave(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        setMouseEvent(imageView,true);
    }

    /**
     * set opacity effect according to mouse event
     * @param imageView the scope of response area
     * @param leave whether mouse is entering or not
     */
    private void setMouseEvent(ImageView imageView,boolean leave){
        if(leave){
            imageView.setOpacity(1);
        }
        else{
            imageView.setOpacity(0.5);
        }
    }
}
