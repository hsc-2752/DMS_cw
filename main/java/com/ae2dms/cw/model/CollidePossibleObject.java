package com.ae2dms.cw.model;

import com.ae2dms.cw.consts.SceneConst;
import com.ae2dms.cw.model.basic.GameObject;

/**
 * CollidePossibleObject class is the GameObject that do have some colliding behaviors
 * It is classified to Character and SmallComponent
 */
public abstract class CollidePossibleObject extends GameObject {

    /**
     * constructor for Character
     * @param x set the exact x position
     * @param y set the exact y position
     */
    public CollidePossibleObject(int x, int y) {
        super(x, y, SceneConst.UNIT_SIZE, SceneConst.UNIT_SIZE);
    }

    /**
     * constructor for SmallComponent
     * @param height set height
     * @param width set width
     * @param x set x position
     * @param y set y position
     */
    public CollidePossibleObject(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * collide With Floor
     */
    public abstract void collideWithFloor();

    /**
     * collide With Ceiling
     */
    public abstract void collideWithCeiling();

    /**
     * collide With Wall
     */
    public abstract void collideWithWall();


}
