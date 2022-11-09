package com.ae2dms.cw.controller;

import com.ae2dms.cw.GameFeature;
import com.ae2dms.cw.Main;
import com.ae2dms.cw.consts.FxmlConst;
import com.ae2dms.cw.consts.WorldConst;
import com.ae2dms.cw.scene.SwitchScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

/**
 * controller class of the info scene,only has one instance
 */
public class HighScoreController implements Initializable {

    @FXML
    public ListView<String> ScoreList;

    @FXML
    public Text rank;

    @FXML
    public Button goBack;

    private  ArrayList<String> scoreAndtime; //used for storing score and time in string form
    private ArrayList<String> originalList; //used as a copy of scoreAndtime before sorting

    /**
     * This method read the score from file and rank
     */
    public void readHighScore(){
        scoreAndtime = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader((Main.class.getResource(WorldConst.score).getFile())));
            String str;
            while ((str = in.readLine()) != null) {
                scoreAndtime.add(str);
            }
        } catch (IOException ignored) {}
        originalList = new ArrayList<>(scoreAndtime);
        scoreAndtime.sort(Comparator.reverseOrder());
    }

    /**
     * This method displays the corresponding text information in the list
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        readHighScore();
        ObservableList<String> scoreTimeList = FXCollections.observableArrayList(scoreAndtime);
        ScoreList.setItems(scoreTimeList);

        if(getLatestRank() == 1){
            rank.setText("Congratulations! NEW RECORD!"+GameFeature.getScore());
        }
        else if (scoreTimeList.get(getLatestRank()-1).startsWith("0000")){
            rank.setText("You got a zero...");
        }
        else{
            rank.setText("Your current ranking is "+getLatestRank());
        }
    }

    /**
     * @return the rank of the current play
     */
    public int getLatestRank(){
        return (scoreAndtime.indexOf(originalList.get(originalList.size()-1))+1);
    }

    /**
     * back to main scene
     * @param event color picker event
     */
    public void toStartScene(ActionEvent event) {
        SwitchScene.switchScene(FxmlConst.STARTSCENE);
    }
}
