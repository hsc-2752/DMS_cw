package com.ae2dms.cw.scene;

import com.ae2dms.cw.GameFeature;
import com.ae2dms.cw.consts.SceneConst;
import com.ae2dms.cw.model.*;
import com.ae2dms.cw.util.SoundEffect;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;

/**
 * Game main scene, start the game and handles key events
 */
public class MainScene {

    private Canvas canvas = new Canvas(SceneConst.MAX_WIDTH,SceneConst.MAX_HEIGHT);
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    Timer timer;
    boolean isRunning;
    boolean isStorm;

    Refresh refresh = new Refresh();
    AnchorPane root;
    UpdateObjectObserver observer = null;

    /**
     * start game with level, life score and storm settings
     * score is passed to so that it preserves after level ups
     */
    void startGame(int level,int life,int score,boolean storm) {
        SoundEffect.MAIN.stop();
        timer = new Timer();
        Stage stage = GameFeature.getStage();
        root = new AnchorPane(canvas);
        stage.getScene().setRoot(root);

        isRunning = true;
        refresh.start();
        this.isStorm = storm;
        //set background based on the game mode
        if(!isStorm) {
            root.setBackground(new Background(new BackgroundFill(GameFeature.getBackgroundColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else {
            root.setBackground(new Background(new BackgroundFill(BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        observer = new UpdateObjectObserver(Level.setLevel(level,life,score));
        observer.paintComponent(gc);
        observer.start();

        Hero hero = observer.getHero();
        stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (hero != null) {
                    if (event.getCode() == KeyCode.ESCAPE && event.getEventType() == KeyEvent.KEY_PRESSED){
                        SwitchScene.mainScene();
                    }
                    if (event.getCode() == KeyCode.LEFT && event.getEventType() == KeyEvent.KEY_PRESSED){
                        hero.left();
                    }
                    if (event.getCode() == KeyCode.RIGHT && event.getEventType() == KeyEvent.KEY_PRESSED){
                        hero.right();
                    }
                    if (event.getCode() == KeyCode.UP && event.getEventType() == KeyEvent.KEY_PRESSED){
                        SoundEffect.JUMP.play();
                        hero.jump();
                    }
                    if (event.getCode() == KeyCode.SPACE && event.getEventType() == KeyEvent.KEY_PRESSED){
                        hero.run();
                    }
                    if (event.getCode() == KeyCode.E && event.getEventType() == KeyEvent.KEY_PRESSED){
                        SoundEffect.SHOOT.play();
                        observer.addHeroProjectile(hero.shootProjectile());
                    }
                    if (event.getCode() == KeyCode.S && event.getEventType() == KeyEvent.KEY_PRESSED){
                        observer.addBarrier(new Barrier((int)hero.getHitbox().getMinX(),(int)hero.getHitbox().getMinY()));
                    }
                    if (event.getCode() == KeyCode.Q && event.getEventType() == KeyEvent.KEY_PRESSED){
                        hero.shieldingHero();
                    }
                    if (event.getCode() == KeyCode.W && event.getEventType() == KeyEvent.KEY_PRESSED){
                        SoundEffect.BUBBLED.play();
                        hero.setCharge();
                        observer.addBubble(new Bubble((int)hero.getHitbox().getMinX(),(int)hero.getHitbox().getMinY()));
                    }
                }
            }
        });
        stage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (hero != null) {
                    if (event.getCode() == KeyCode.LEFT && event.getEventType() == KeyEvent.KEY_RELEASED){
                        hero.stopX();
                    }
                    if (event.getCode() == KeyCode.RIGHT && event.getEventType() == KeyEvent.KEY_RELEASED){
                        hero.stopX();
                    }
                    if (event.getCode() == KeyCode.SPACE && event.getEventType() == KeyEvent.KEY_RELEASED){
                        hero.stopRun();
                    }
                    if (event.getCode() == KeyCode.E && event.getEventType() == KeyEvent.KEY_RELEASED){
                        hero.stopShoot();
                    }
                    if (event.getCode() == KeyCode.Q && event.getEventType() == KeyEvent.KEY_RELEASED){
                        hero.stopShieldingHero();
                    }
                    if (event.getCode() == KeyCode.W && event.getEventType() == KeyEvent.KEY_RELEASED){
                        hero.setCharge();
                    }
                }
            }
        });
    }


    /**
     * updates background of everything on screen
     */
    void updateBackGround() {
        if(isStorm) {
            if (Math.random() < 0.3) {
                root.setBackground(new Background(new BackgroundFill(WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            } else {
                root.setBackground(new Background(new BackgroundFill(BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }

    /**
     * refresh
     */
    private class Refresh extends AnimationTimer {
        @Override
        public void handle(long now) {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            if (isRunning) {
                updateBackGround();
                if (!observer.update(gc)){
                    refresh.stop();
                }
            }
        }
    }
}
