package com.ae2dms.cw.model;


import com.ae2dms.cw.consts.SceneConst;
import com.ae2dms.cw.model.basic.GameObject;
import javafx.scene.canvas.GraphicsContext;

/**
 * BoundaryUnit class is the abstract of wall unit, ceiling unit and floor unit classes
 */
public abstract class BoundaryUnit extends GameObject {
    /**
     * constructor
     * @param x
     * @param y
     */
    public BoundaryUnit(int x, int y) {
        super( x, y, SceneConst.UNIT_SIZE, SceneConst.UNIT_SIZE);
    }

    /**
     * draw on scene
     * @param gc
     */
    public void drawOn(GraphicsContext gc) {
		gc.fillRect(x, y, width, height);
	}

    /**
     * abstract method for collide with objects
     * @param cpo collide possible objects
     */
    public abstract void collideWith(CollidePossibleObject cpo); // later change GameObject obj into character obj

}
