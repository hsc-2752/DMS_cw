package com.ae2dms.cw.model;


import com.ae2dms.cw.model.basic.GameObject;
import com.ae2dms.cw.model.behavior.BoundaryBehavior;
import javafx.geometry.Point2D;

/**
 * The FloorUnit class creates floor units to be used for the world.
 * A floor unit is a unit shaped like a square that is treated as a floor,
 * with collision on the top, left, and right sides.
 * The floor collides with any kind of game object.
 * When an enemy is bubbled, the enemy will still be stopped by a floor unit above it.
 */
public class FloorUnit extends BoundaryUnit implements BoundaryBehavior {

	/**
	 * constructor
	 * @param x x location
	 * @param y y location
	 */
	public FloorUnit(int x, int y) {
		super(x, y);
	}

	/**
	 * deal with collide, the floor with make the object move up
	 * @param cpo collide possible objects
	 */
	@Override
	public void collideWith(CollidePossibleObject cpo) {
		double top = cpo.getY();
		double bottom = top + cpo.getHeight();
		if (this.overlaps(cpo) && cpo.yVelocity > 0) {
			if (bottom < y + height) {
				moveAboveUnit(cpo);
				cpo.collideWithFloor();
			}
			if (top > y){
				moveBelowUnit(cpo);
				cpo.collideWithCeiling();
			}
		}
	}


	/**
	 * move the object up
	 * @param obj
	 */
	void moveAboveUnit(GameObject obj) {
		obj.moveTo(new Point2D(obj.getX(), y - obj.getHeight()));
	}

	/**
	 * move the object down
	 * @param obj
	 */
	void moveBelowUnit(GameObject obj) {
		obj.moveTo(new Point2D(obj.getX(), y + height));
	}

}
