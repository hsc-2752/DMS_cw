package com.ae2dms.cw.model.enemyFatory;

import com.ae2dms.cw.consts.ImageConst;
import com.ae2dms.cw.consts.SceneConst;
import com.ae2dms.cw.model.Character;
import com.ae2dms.cw.model.*;
import com.ae2dms.cw.model.basic.GameObject;
import com.ae2dms.cw.model.behavior.DrawEnemyLife;
import com.ae2dms.cw.util.SoundEffect;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.IOException;

/**
 * An Enemy is a non-controllable Character that kills the Hero whenever it or its projectile comes in contact.
 * Enemies change direction at random intervals, when hitting a wall, and when hitting the Hero's shield.
 * Enemies jump at random intervals as well.
 * There are two kinds of Enemies: normal enemy and boss
 */
public abstract class Enemy extends Character {
	private static int WIDTH = SceneConst.UNIT_SIZE + 10;
	private static int HEIGHT = SceneConst.UNIT_SIZE + 10;
	private static final int TERMINAL_VELOCITY_X = 4;
	private static final int BUBBLED_FRAMES = 300;
	private static final double CHANGE_MOVEMENT_CHANCE = 0.01;
	private static final int SIZE = 30;

	private boolean turningAwayFromShield;
	private int turningAwayCount;


	boolean isBubbled;
	int timer;
	int pointValue;
	boolean isBoss;
	Image image;

//	public ArrayList<normalEnemy> observers;//....

	/**
	 * @param x set the exact x position
	 * @param y set the exact y position
	 * This method initializes an Enemy and defines its life drawing behavior
	 */
	public Enemy(int x, int y) {
		//initializes enemy
		super(x, y,new DrawEnemyLife());
		isOnAPlatform = false;
		jumpSpeed = 15;
		terminal_xVelocity = TERMINAL_VELOCITY_X;
		
		xAccel = 1.5;
		direction = 1;
		if (Math.random() < 0.5) { //let the enemy randomly change directions
			reverseDirection();
		}
		
		isBubbled = false;
		timer = BUBBLED_FRAMES;
		pointValue = 150;
		turningAwayFromShield = false;
		turningAwayCount = 10;
	}

	/**
	 * This method draw the Enemy based on if it is bubbled
	 * @param g graphics context for the Enemy to draw on
	 */
	@Override
	public void drawOn(GraphicsContext g) {
		image = new Image(ImageConst.ENEMY);
		g.drawImage(image,x,y-5,SIZE,SIZE);
		if (isBubbled) {
			g.setFill(Color.rgb(0, 255, 255, (int) (timer * ((double) 1 / 300))));
			g.fillRect(x - 5, y - 5, WIDTH + 10, HEIGHT + 10);
		}
		g.setFill(Color.BLACK);
	}

	/**
	 * This method handles floor collision values
	 */
	@Override
	public void collideWithFloor() {
		yVelocity = 0;
		if (!isOnAPlatform) {
			isOnAPlatform = true;
		}
	}

	/**
	 * This method handles ceiling collision values
	 */
	@Override
	public void collideWithCeiling() {
		yVelocity = 0;
	}

	/**
	 * This method updates enemy, handling movement
	 */
	@Override
	public void update() {
		super.update();
		if (isBubbled) {
			timer -= 1;
			if (timer <= 0) {
				isBubbled = false;
				timer = BUBBLED_FRAMES;
				xAccel = 1.5;
				direction = 1;
				if (Math.random() < 0.5) {
					reverseDirection();
				}
				yAccel = GameObject.GRAVITY;
			}
		}
	}

	/**
	 * set random move of enemy
	 * @return projectile at some random intervals
	 */
	public EnemyProjectile randomMove(){
		EnemyProjectile enemyProjectile = null;
		if (!isBubbled) {
			if (Math.random() < CHANGE_MOVEMENT_CHANCE) {
				jump();
			}
			if (Math.random() < CHANGE_MOVEMENT_CHANCE) {
				reverseDirection();
			}
			if (Math.random() < CHANGE_MOVEMENT_CHANCE) {
				enemyProjectile = shootProjectile();
			}
		}
		return enemyProjectile;
	}

	/**
	 * This method add projectiles
	 */
	private EnemyProjectile shootProjectile() {
		return new EnemyProjectile( x, y, direction);
	}

	/**
	 * normal enemy and boss all have projectile collide behavior but is very different
	 */
    public abstract void collideWithProjectile();

	/**
	 * handles what to do on collision with a wall
	 */
	public void collideWithWall() {
		reverseDirection();
	}

	/**
	 * a fruit will appear when a enemy die
	 */
	Fruit die() {
		markToRemove();
		return new Fruit(x, y);
	}

	/**
	 * handles collision with hero
	 * @param hero that collides with the current hero
	 * @throws IOException
	 * @see IOException
	 */
	public Fruit collideWith(Hero hero) throws IOException {
		Fruit fruit = null;
		if (this.overlaps(hero)) {
			if (!isBubbled) {
				hero.collideWithMook();

				if (hero.getShielding() && !turningAwayFromShield) {
					turningAwayFromShield = true;
					reverseDirection();
				}
			}
			else if (!canRemove){
				SoundEffect.POP.play();
				fruit = die();
			}
		}
		if (turningAwayFromShield) {
			if (turningAwayCount <= 0) {
				turningAwayCount = 10;
				turningAwayFromShield = false;
			}
			turningAwayCount -= 1;
		}
		return fruit;
	}

	/**
	 * handles celling unit collision
	 * @param boundaryUnit that the hero is overlap with
	 */
	public void collideWith(BoundaryUnit boundaryUnit) {
		if (this.overlaps(boundaryUnit)) {
			if (isBubbled) {
				yVelocity = 0;
				yAccel = 0;
			}
		}
	}



}

