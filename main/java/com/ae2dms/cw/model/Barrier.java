package com.ae2dms.cw.model;

import com.ae2dms.cw.model.basic.GameObject;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The Barrier class handles how the barrier is created and interacts with the hero.
 * The barriers are created when hero pressed "S"
 */
public class Barrier extends WallUnit {

    private int life; //A barrier has 5 lives and will be eliminated by 5 times of shooting by hero projectiles

	/**
	 * constructor
	 * @param x x location
	 * @param y y location
	 */
	public Barrier(int x, int y) {
		super(x,y);
		life = 5;
		yVelocity = 0;
		yAccel = 0 ;
	}

	/**
	 * draw on scene
	 * @param gc
	 */
	public void drawOn(GraphicsContext gc) {
		gc.setFill(Color.rgb(120,120,120));
		gc.fillRect(x, y, width, height);
	}

	/**
	 * Every time the barrier collide with a projectile, it will lose a life
	 * When all life are lost, il will be mark to remove
	 */
	public void collideWithProjectile() {
		life = life -1;
		if(life == 0){
			markToRemove();
		}
	}

	/**
	 * This method handles how barrier react with other collide-possible objects
	 * @param cpo objects collide with the barrier
	 *  Collide rule:
	 *  1. cpo cannot cross horizontally through the barrier
	 *  2. cpo can step upon it or cross it vertically from down to up
	 */
	public void collideWith(CollidePossibleObject cpo) {
		super.collideWith(cpo);
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
	 * move the obj up with collide
	 * @param obj
	 */
	void moveAboveUnit(GameObject obj) {
		obj.moveTo(new Point2D(obj.getX(), y - obj.getHeight()));
	}
}
