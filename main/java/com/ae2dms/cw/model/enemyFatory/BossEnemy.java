package com.ae2dms.cw.model.enemyFatory;

import com.ae2dms.cw.consts.ImageConst;
import com.ae2dms.cw.model.behavior.DrawEnemyLife;
import com.ae2dms.cw.model.behavior.DrawLifeBehavior;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A bossEnemy is a kind Enemy that have multiple lives
 */
public class BossEnemy extends Enemy{

    private static int life;
    DrawLifeBehavior drawLifeBehavior  = new DrawEnemyLife();;
    /**
     * boss constructor with default jump speed
     * @param x set the exact x position
     * @param y set the exact y position
     */
    public BossEnemy(int x, int y) {
        super(x, y);
        life = 20;

    }

    /**
     * boss constructor, with input jump speed type
     * @param x set the exact x position
     * @param y set the exact y position
     * @param isVerySlow sets the input jump speed type
     */
    public BossEnemy(int x, int y, boolean isVerySlow) {
        super(x, y);
        life = 20;
        if(!isVerySlow){jumpSpeed = 30;}
        else{jumpSpeed = 20;}
        isBoss = true;
    }

    /**
     * @return the life value
     */
    public static int getLife() {
        return life;
    }

    /**
     * draw life
     * @param g
     */
    @Override
    public void drawLife(GraphicsContext g) {
        drawLifeBehavior.drawLife(g);
    }

    /**
     * handles collision with projectiles
     * if life has been reduced to half, notify all normal enemies to speed up
     * if all life run out, die
     */
    @Override
    public void collideWithProjectile() {
        life = life -1;
        if(life == 0){
            die();
        }
    }

    /**
     * draw boss on the graphics context
     * @param g graphics context for the Enemy to draw on
     */
    public void drawOn(GraphicsContext g) {
        image = new Image(ImageConst.BOSS);
        g.drawImage(image,x,y-25,50,50);
    }

////    /**
////     * update the jump speed of the existing normal enemy
////     */
//    public void notifyAllObservers(){
//        //observer pattern
//        for (NormalEnemy enemy : observers) {
//            enemy.observerUpdate();
//        }
//    }
}
