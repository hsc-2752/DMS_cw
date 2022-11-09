package com.ae2dms.cw.model;

import com.ae2dms.cw.consts.ImageConst;
import com.ae2dms.cw.util.SoundEffect;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The Fruit class handles how the fruit is created and interacts with the hero.
 * The fruits are created after a bubble containing an enemy is popped.
 */
public class Fruit extends SmallComponent {
	private static final int SIZE = 15;
	private static final int TERMINAL_VELOCITY_Y = 10;
	
	private boolean readyToCollect;

	/**
	 * @param x x location
	 * @param y y location
	 * Constructor for initialization
	 */
	public Fruit(int x, int y) {
		//initializes fruit
		super(x, y, SIZE, SIZE);
		terminal_yVelocity = TERMINAL_VELOCITY_Y;
		readyToCollect = false;
	}

	/**
	 * draw on the scene
	 * @param g
	 */
	@Override
	public void drawOn(GraphicsContext g) {
		Image image = new Image(ImageConst.FRUIT);
		g.drawImage(image,x,y,SIZE,SIZE);
	}

	/**
	 * checks for collision with hero and tells it what to do if it is colliding
	 * @param hero
	 */
	public void collideWith(Hero hero) {
		if (this.overlaps(hero) && readyToCollect) {
			SoundEffect.FRUIT.setToLoud();
			SoundEffect.FRUIT.play();
			readyToCollect = false;
			hero.score.setMark(hero.score.getMark()+10);// each "fruit" collected will give hero 10 mark as reward
			markToRemove();
		}
	}

	/**
	 * collide with floor
	 */
	@Override
	public void collideWithFloor() {
		yVelocity = 0;
		if (!canRemove) {
			readyToCollect = true;
		}
	}

	/**
	 * fruit will not collide with ceiling
	 */
	@Override
	public void collideWithCeiling() {
		// Nothing happens
	}

	/**
	 * fruit will not collide with wall
	 */
	@Override
	public void collideWithWall() {
		// Nothing happens
	}


}
