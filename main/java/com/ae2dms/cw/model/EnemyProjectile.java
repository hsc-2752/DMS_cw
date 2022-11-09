package com.ae2dms.cw.model;


import javafx.scene.paint.Color;

import java.io.IOException;

/**
 * The HeroProjectiles class is one on the projectiles produced by enemy
 */
public class EnemyProjectile extends Projectile{
	/**
	 * constructor
	 * @param x x position
	 * @param y y position
	 * @param direction movement direction
	 */
	public EnemyProjectile( int x, int y, int direction) {
		super(x,y,direction);
		ColorBefore = Color.rgb(0, 102, 0);
		ColorAfter = Color.rgb(0, 102, 0,0.16);
	}

	/**
	 * enemy projectile will kill a hero
	 * @param hero
	 * @throws IOException
	 */
	public void collideWith(Hero hero) throws IOException {
		if (this.overlaps(hero) && isActive) {
			hero.collideWithProjectile();
		}
	}

}