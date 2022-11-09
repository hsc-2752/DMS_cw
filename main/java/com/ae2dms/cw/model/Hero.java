package com.ae2dms.cw.model;


import com.ae2dms.cw.consts.FxmlConst;
import com.ae2dms.cw.consts.ImageConst;
import com.ae2dms.cw.consts.SceneConst;
import com.ae2dms.cw.model.behavior.DrawHeroLife;
import com.ae2dms.cw.scene.SwitchScene;
import com.ae2dms.cw.util.SoundEffect;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.IOException;

/**
 * A Hero is a Character that is controllable by the player.
 * Hero can shoot HeroProjectiles, shield from attacks, trigger a special attack,
 * collect Fruits for points, and build barrier for hide from enemies or use it as stairs
 */
public class Hero extends Character{
	private static final int JUMP_SPEED = 22;
	private static final int TERMINAL_VELOCITY_X = 6;
	private static final int SIZE = 30;
	private static final int WALK = 5;
	private static final int RUN = 10;
	private static final double RUN_ACCEL = 20;
	private static final int SHIELD_TIME = 100;
	private static int life = 3;

	public Score score;
	private boolean isShielding;
	private int shieldTimer;
	private boolean isStunned;
	private int stunTimer;
	private int shootDelay;
	private boolean readyToCharge;
	private Image image;
	private Timer bubbleTimer;
	private boolean rightWard;
	private int tmpX,tmpY;

	/**
	 * @param x set the exact x position
	 * @param y set the exact y position
	 * @param life set the life of the hero
	 * @param sc initialize the score
	 * This method initializes a hero and defines its life drawing behavior
	 */
	public Hero(int x, int y,int life,int sc) {
		super(x, y,new DrawHeroLife()); //defines hero draw life pictures behavior using Strategy pattern
		this.life = life;
		isOnAPlatform = false;
        score = new Score(); //keep a record of the score of the hero
		score.setMark(sc);
		terminal_xVelocity = TERMINAL_VELOCITY_X;
		jumpSpeed = JUMP_SPEED;
		isShielding = false;
		shieldTimer = SHIELD_TIME;
		isStunned = false;
		stunTimer = 250;
		shootDelay = 0;
		readyToCharge = false;
		rightWard = true;
		bubbleTimer = new Timer();
		tmpX = x;
		tmpY = y;
	}

	/**
	 * get life
	 * @return life
	 */
	public static int getLife() {
		return life;
	}

	/**
	 * get size
	 * @return size
	 */
	public static int getSIZE() {
		return SIZE;
	}

	/**
	 * set life
	 * @param life
	 */
	public void setLife(int life) {
		Hero.life = life;
	}


	/**
	 * This method draw the hero based on the direction it is facing
	 * @param g graphics context for the hero to draw on
	 */
	public void drawOn(GraphicsContext g) {
		if(rightWard){
			image = new Image(ImageConst.HERORIGHT);
		}
		else{
			image = new Image(ImageConst.HEROLEFT);
		}

		g.drawImage(image,x,y-19,SIZE*1.5,SIZE*1.5);
		if (isShielding) {
			g.setFill(Color.rgb(0, (int) (shieldTimer * ((double) 255 / SHIELD_TIME)), (int) (shieldTimer * ((double) 255 / SHIELD_TIME)), 0.745));
			g.fillOval(x - 10, y - 10, SIZE + 20, SIZE + 20);
		} else if (isStunned) {
			g.setFill(Color.MAGENTA);
			g.fillRect(x, y, SIZE, SIZE);
		}
		g.setFill(Color.BLACK);

	}

	/**
	 * shoot projectile
	 * @return the projectile
	 */
	public HeroProjectile shootProjectile() {
		if (!isShielding && !isStunned) {
			shootDelay -= 1;
			if (shootDelay <= 0) {

				shootDelay = 10;
				return new HeroProjectile(x, y, direction);
			}
		}
		return null;
	}

	/**
	 * build the barrier
	 * @return
	 */
	public Barrier buildBarrier() {
		return new Barrier(x/ SceneConst.UNIT_SIZE, y/SceneConst.UNIT_SIZE);
	}

	/**
	 * This method decide if a hero will die or not considering whether it is shielding
	 * @throws IOException
	 */
	public void collideWithMook() throws IOException {
		if (!isShielding) {
			die();
		}
	}

	/**
	 * collide With Wall, bug fixed here
	 */
	@Override
	public void collideWithWall() {
		xVelocity = 0;
	}

	/**
	 * go left
	 */
	public void left(){
		if (!isShielding && !isStunned) {
			xAccel = -RUN_ACCEL;
			direction = -1;
			rightWard = false;
		}
	}

	/**
	 * stop
	 */
	public void stopX(){
		xAccel = 0;
	}

	/**
	 * go right
	 */
	public void right(){
		if (!isShielding && !isStunned) {
			xAccel = RUN_ACCEL;
			direction = 1;
			rightWard = true;
		}
	}

	/**
	 * jump
	 */
	public void jump(){
		if (!isShielding && !isStunned) {
			super.jump();
		}
	}

	/**
	 * run
	 */
	public void run(){
		if (!isShielding && !isStunned) {
			terminal_xVelocity = RUN;
		}
	}

	/**
	 * return to walk from running
	 */
	public void stopRun(){
		if (!isShielding && !isStunned) {
			terminal_xVelocity = WALK;
		}
	}

	/**
	 * shield
	 */
	public void shieldingHero(){
		if (!isStunned) {
			xVelocity = 0;
			xAccel = 0;
			isShielding = true;
		}
	}

	/**
	 * make hero no longer shielding
	 */
	public void stopShieldingHero(){
		isShielding = false;
	}

	/**
	 * stop shooting
	 */
	public void stopShoot(){
		shootDelay = 0;
	}
	/**
	 * This method handles death
	 * It decreases life by one
	 * when life run out, it would jump to the high score page
	 */
	public void die()  {
		SoundEffect.DEATH.play();
		if(life > 0){
			life = life-1;
			this.moveTo(new Point2D(tmpX,tmpY));
		}
		if (life == 0){
//			world.isRunning = false;
			score.store();
//			life =3;
			SwitchScene.switchScene(FxmlConst.HEIGHSCORESCENCE);
		}
//		else{
//			world.markToReset();
//		}
	}

	/**
	 * deal with collision with projectile
	 * @throws IOException
	 */
	public void collideWithProjectile() throws IOException {
		//handles collision with projectiles
		if (!isShielding) {
			die();
		}
	}

	/**
	 * This method updates position of hero, according to many variables
	 * including whether the hero is shielding,
	 * or if the hero is stunned
	 */
	@Override
	public void update() {
		super.update();
		if (isShielding) {
			shieldTimer -= 1;
			if (shieldTimer <= 0) {
				shieldTimer = 0;
				isShielding = false;
				isStunned = true;
			}
		}
		else {
			if (shieldTimer < SHIELD_TIME && !isStunned) {
				shieldTimer += 1;
			}
		}
		if (isStunned) {
			stunTimer -= 1;
			if (stunTimer <= 0) {
				isStunned = false;
				stunTimer = 250;
				shieldTimer = SHIELD_TIME;
			}
		}
	}

	/**
	 * This method handles collision with floor
	 */
	@Override
	public void collideWithFloor() {
		yVelocity = 0;
		if (!isOnAPlatform) {
			isOnAPlatform = true;
			SoundEffect.LAND.play();
		}
	}

	/**
	 * collide With Ceiling
	 */
	@Override
	public void collideWithCeiling() {}

	/**
	 * @return if the hero is shielding on this frame
	 */
	public boolean getShielding() {
		return isShielding;
	}

	/**
	 * This method sets whether the hero is ready to charge the charge shot
	 * 5 second is used for the charging duration
	 */
	public void setCharge() {
		if (bubbleTimer.getDuration()>=5){
			readyToCharge = true;
		}
		this.bubbleTimer = new Timer(); //reset the timer

		if (readyToCharge) {
			readyToCharge = false;
		}
	}

}