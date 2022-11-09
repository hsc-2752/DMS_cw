package com.ae2dms.cw.controller;

import com.ae2dms.cw.consts.FxmlConst;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import static org.junit.jupiter.api.Assertions.*;



import java.io.IOException;


public class HighScoreControllerTest extends ApplicationTest {
    HighScoreController controller;
    Parent root;
    Stage stage;

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlConst.HEIGHSCORESCENCE));
        root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
        controller = loader.getController();
        this.stage = stage;
    }

//0010 2021-11-30 02:24:05
    @Test
    public void listViewTest() {
        ObservableList<String> testItem = controller.ScoreList.getItems();
        assertEquals("0230 2021-11-30 03:05:51",testItem.get(0));
    }

//    @Test
//    void toStartPageTest() throws IOException {
//        clickOn("#goBack");
//        FxAssert.verifyThat("#ap1", (Pane pane) -> {
//            return pane.isVisible();
//        });
//    }
}