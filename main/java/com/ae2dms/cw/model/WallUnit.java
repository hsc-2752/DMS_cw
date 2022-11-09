package com.ae2dms.cw.model;

import com.ae2dms.cw.model.basic.GameObject;
import com.ae2dms.cw.model.behavior.BoundaryBehavior;
import javafx.geometry.Point2D;

/**
 * The WallUnit class creates wall units to be used for the world.
 * A wall unit is a unit shaped like a square that is treated as a wall,
 * with collision on all four sides.
 * The wall collides with any kind of game object.
 */
public class WallUnit extends BoundaryUnit implements BoundaryBehavior {


	public WallUnit(int x, int y) {
		super(x, y);
	}

	/**
	 * deal with collide, the wall with make the object move left or right
	 * @param cpo collide possible objects
	 */
	@Override
	public void collideWith(CollidePossibleObject cpo) {
		double center = (cpo.getHitbox().getMinX()+cpo.getHitbox().getMaxX())/2.0;
		double center_this = (this.getHitbox().getMinX()+this.getHitbox().getMaxX())/2.0;
		if (this.overlaps(cpo)) {
			if (center > center_this) {
				moveRightOfUnit(cpo);
				cpo.collideWithWall();
			} else if (center < center_this){
				moveLeftOfUnit(cpo);
				cpo.collideWithWall();}
			}
		}


	void moveBelowUnit(GameObject obj) {
		obj.moveTo(new Point2D(obj.getX(), y + height));
	}

	void moveLeftOfUnit(GameObject obj) {
		obj.moveTo(new Point2D(x - obj.getWidth(), obj.getY()));
	}

	void moveRightOfUnit(GameObject obj) {
		obj.moveTo(new Point2D(x + width, obj.getY()));
	}

}
