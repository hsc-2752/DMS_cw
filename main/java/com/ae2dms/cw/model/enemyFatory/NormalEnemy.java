package com.ae2dms.cw.model.enemyFatory;

import javafx.scene.canvas.GraphicsContext;
import com.ae2dms.cw.util.SoundEffect;

/**
 * A normalEnemy is a kind Enemy that will be bubbled
 * and is able to free themselves from these bubbles after a period of time.
 * A normalEnemy only has one life
 */
public class NormalEnemy extends Enemy {

    /**
     * normal enemy constructor with default jump speed
     * @param x set the exact x position
     * @param y set the exact y position
     */
    public NormalEnemy( int x, int y) {
        super(x, y);
        isBoss = false;
    }

    /**
     * normal enemy constructor, with input jump speed type
     * @param x set the exact x position
     * @param y set the exact y position
     * @param isFast sets the input jump speed type
     */
    public NormalEnemy(int x, int y, boolean isFast) {
        super( x, y);
        if (! isFast){
            jumpSpeed = 10;
        }
        else{
            jumpSpeed = 25;
        }
        isBoss = false;
    }

    /**
     * handles collision with projectiles
     * will be bubbled if hit a projectile
     * but can  free themselves from these bubbles after a period of time.
     */
    @Override
    public void collideWithProjectile() {
        if (!isBubbled) {
			SoundEffect.BUBBLED.setToLoud();
			SoundEffect.BUBBLED.play();
			isBubbled = true;
			yVelocity = 0;
			xAccel = 0;
			yAccel = -0.1;
		}
    }

    /**
     *  method set to do nothing since normal enemy only have one life
     * @param g
     */
    @Override
    public void drawLife(GraphicsContext g) {

    }

}
