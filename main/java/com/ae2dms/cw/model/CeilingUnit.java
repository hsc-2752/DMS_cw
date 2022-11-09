package com.ae2dms.cw.model;

import com.ae2dms.cw.model.basic.GameObject;
import com.ae2dms.cw.model.behavior.BoundaryBehavior;
import javafx.geometry.Point2D;

/**
 * The CeilingUnit class creates ceiling units to be used for the world.
 * A ceiling unit is a unit shaped like a square that is treated as a ceiling, with collision on all four sides.
 * The ceiling collides with any kind of game object.
 * Even if a game object is on top of a ceiling, the game object will be pushed down.
 */
public class CeilingUnit extends BoundaryUnit implements BoundaryBehavior {
	/**
	 * constructor
	 * @param x
	 * @param y
	 */
	public CeilingUnit(int x, int y) {
		super(x, y);
	}

	/**
	 * deal with collide, the ceiling with make the object move down
	 * @param cpo collide possible objects
	 */
	@Override
	public void collideWith(CollidePossibleObject cpo) {
		if (this.overlaps(cpo)) {
			moveBelowUnit(cpo);
			cpo.collideWithCeiling();
		}
	}

	/**
	 * move the object obj down
	 * @param obj
	 */
	void moveBelowUnit(GameObject obj) {
		obj.moveTo(new Point2D(obj.getX(), y + height));
	}

}
