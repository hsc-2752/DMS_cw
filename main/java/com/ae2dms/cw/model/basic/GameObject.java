package com.ae2dms.cw.model.basic;

import com.ae2dms.cw.consts.SceneConst;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;


/**
 * GameObjects are the objects on the game main screen.
 * Every GameObject has a velocity, acceleration, position, direction, and dimensions.
 * GameObjects can detect if they are overlapping another GameObject
 *
 */
public abstract class GameObject {
	private static final double STATIC_FRICTION = 0.1;
	protected static final int GRAVITY = 1;
	private static final int TERMINAL_FALL_SPEED = 20;

	public int x, y;
	public int width, height;
	
	public double xVelocity, yVelocity;
	public double xAccel, yAccel;
	public int terminal_xVelocity, terminal_yVelocity;
	
	public boolean canRemove;
	public int direction;

	/**
	 * constructor for SmallComponent
	 * @param x set the exact x position
	 * @param y set the exact y position
	 * @param width set the width
	 * @param height set the height
	 */
	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		xVelocity = 0;
		yVelocity = 0;
		xAccel = 0;
		yAccel = GRAVITY;
		terminal_xVelocity = 0;
		terminal_yVelocity = TERMINAL_FALL_SPEED;
		canRemove = false;
		direction = -1;
	}

	/**
	 * draw
	 * @param g
	 */
	public abstract void drawOn(GraphicsContext g);

	/**
	 * general update method of every game object
	 */
	public void update() {
		if (Math.abs(xVelocity) < terminal_xVelocity) {
			xVelocity += xAccel;
		}
		if (Math.abs(xVelocity) > STATIC_FRICTION) {
			if (xVelocity < 0) {
				xVelocity += 1;
			} else {
				xVelocity -= 1;
			}
			x += xVelocity;
		}
		
		if (yVelocity < terminal_yVelocity) {
			yVelocity += yAccel;
		}
		y += yVelocity;
		
		if (isOffScreen()) {
			if (y > SceneConst.MAX_WIDTH) {
				y = 0;
			} else {
				y = (int) SceneConst.MAX_HEIGHT;
			}
		}
	}

	/**
	 * reverses game object's direction
	 */
	public void reverseDirection() {
		xAccel *= -1;
		direction *= -1;
	}

	/**
	 * @return x coordinate of upper left corner
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return y coordinate of upper left corner
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @return height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * sets whether or not something can be removed
	 */
	protected void markToRemove() {
		canRemove = true;
	}

	/**
	 * sets hitbox for each game object
	 * @return hitbox
	 */
	public Rectangle2D getHitbox(){
		return new Rectangle2D(x, y, width, height);
	}

	/**
	 * @param obj that is checked for overlaps
	 * @return whether two objects overlap(collide)
	 */
	protected boolean overlaps(GameObject obj) {
		return getHitbox().intersects(obj.getHitbox());
	}

	/**
	 * @return if something is offscreen
	 */
	protected boolean isOffScreen() {
		boolean xLow = x + width < 0;
		boolean xHigh = x > SceneConst.MAX_WIDTH;
		boolean yLow = y + height < 0;
		boolean yHigh = y > SceneConst.MAX_HEIGHT;
		return xLow || xHigh || yLow || yHigh;
	}

	/**
	 * moves object to a point
	 * @param point: the location to move the object to
	 */
	public void moveTo(Point2D point) {
		x = (int) point.getX();
		y = (int) point.getY();
	}
}
