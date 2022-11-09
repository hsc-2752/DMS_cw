package com.ae2dms.cw.model;

import com.ae2dms.cw.model.behavior.DrawLifeBehavior;
import javafx.scene.canvas.GraphicsContext;

/**
 * A Character is a GameObject, including Hero and Enemy
 * It is a collection of jumpable objects with certain life value
 */
public abstract class Character extends CollidePossibleObject{
    public boolean isOnAPlatform;
    public int jumpSpeed;
    DrawLifeBehavior drawLifeBehavior;

    /**
     * Constructor that initialize a Character that determines their own draw life behavior
     * @param x set the exact x position
     * @param y set the exact y position
     * @param drawLifeBehavior set different draw life behaviour for Enemy and Hero
     */
    public Character(int x, int y, DrawLifeBehavior drawLifeBehavior) {
        super(x, y);
        this.drawLifeBehavior = drawLifeBehavior;
    }

    /**
     * handles jumping
     */
    public void jump() {
        if (isOnAPlatform) {
            y -= 1;
            yVelocity = -jumpSpeed;
            isOnAPlatform = false;
        }
    }

    /**
     * draw life on the scene
     * @param g
     */
    public void drawLife(GraphicsContext g){
        drawLifeBehavior.drawLife(g);
    }

}
