package com.ae2dms.cw.model;

import com.ae2dms.cw.model.enemyFatory.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The Bubble is one SmallComponent that handles everything with the Hero's special ability, named the bubble.
 * It begins at the hero, and grows covering the whole screen.
 * Once it collides with an enemy, that enemy is bubbled.
 */
public class Bubble extends SmallComponent {
	private int accel;

	/**
	 * Constructor
	 * @param x set x position
	 * @param y set y position
	 */
	public Bubble(int x, int y) {
		super(x, y, 0, 0);
		accel = 1;
	}

	/**
	 * This method controls the size of the bubble along with updates
	 */
	@Override
	public void update() {
		if (width >= 2500) {
			markToRemove();
		}
		x -= accel / 2;
		y -= accel / 2;
		width += accel;
		height += accel;
		accel += 1;
	}

	/**
	 * This method draws the bubble, the opaqueness of the bubble is co-related with its width
	 */
	@Override
	public void drawOn(GraphicsContext g) {
		if (width <= 2500) {
			g.setFill(Color.rgb(255, 204, 102, 1 - (int) (width * ((double) 1 / 2500))));
		} else {
			g.setFill(Color.rgb(255, 204, 102));
		}
		g.fillOval(x, y, width, height);
		g.setFill(Color.BLACK);
	}

	/**
	 * Nothing happens
	 */
	@Override
	public void collideWithFloor() {
		// Nothing happens
	}

	/**
	 * Nothing happens
	 */
	@Override
	public void collideWithCeiling() {
		// Nothing happens
	}

	/**
	 * Nothing happens
	 */
	@Override
	public void collideWithWall() {
		// Nothing happens
	}

	/**
	 * @param enemy to be collided
	 */
	public void collideWith(Enemy enemy) {
		if (this.overlaps(enemy)) {
			enemy.collideWithProjectile();
		}
	}

}
