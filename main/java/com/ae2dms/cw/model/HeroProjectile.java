package com.ae2dms.cw.model;

import com.ae2dms.cw.model.enemyFatory.Enemy;
import javafx.scene.paint.Color;

/**
 * The HeroProjectiles class is one on the projectiles produced by hero
 */
public class HeroProjectile extends Projectile{
	/**
	 * constructor
	 * @param x x location
	 * @param y y location
	 * @param direction the movement direction
	 */
	public HeroProjectile(int x, int y, int direction) {
		super(x,y,direction);
		ColorBefore = Color.rgb(0, 204, 255);
		ColorAfter = Color.rgb(0, 204, 255,0.16);
	}

	/**
	 * hero projectile will collide with enemy
	 * @param enemy
	 */
	public void collideWith(Enemy enemy) {
		if (this.overlaps(enemy) && isActive) {
			enemy.collideWithProjectile();
		}
	}
}
