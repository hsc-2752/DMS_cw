package com.ae2dms.cw.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The Projectile class handles the specificities with the projectile being shot from a hero or an enemy.
 * The hero's projectile has a different color than the projectile of an enemy.
 * It also can hurt an enemy and can eliminate the barrier
 */
public class Projectile extends SmallComponent {
    private static final int SIZE = 20;
    private static final int SPEED = 15;
    private static final int TERMINAL_VELOCITY_Y = 5;

    boolean isActive;
    private int activeFrames;
    private int timer;

    static Color ColorBefore;
    static Color ColorAfter;

    /**
     * constructor
     * @param x x location
     * @param y y location
     * @param direction the direction of movements
     */
    public Projectile(int x, int y, int direction) {
        super(x, y, SIZE, SIZE);
        this.direction = direction;

        xVelocity = SPEED;
        yAccel = 0;

        isActive = true;
        activeFrames = 35;
        timer = activeFrames;
    }

    /**
     * set the color according to if it is activated
     * specific color is determined by its subclass attribute
     * @param g graphics context to write on
     */
    @Override
    public void drawOn(GraphicsContext g) {
        if (isActive) {
            g.setFill(ColorBefore);
        } else {
            g.setFill(ColorAfter);
        }
        g.fillOval(x, y, width, height);
        g.setFill(Color.BLACK);
    }

    /**
     * This method updates location
     */
    @Override
    public void update() {
        y += yVelocity;
        x += xVelocity * direction;
        updateVelocity();

        if(y < 25) {
            y = 25;
        }

        if (timer < 0) {
            isActive = false;
        }

        if (timer < -200) {
            markToRemove();
        }
        timer -= 1;
    }

    /**
     * This method update the velocity of the projectile so that it moves slower at both x and y direction
     */
    private void updateVelocity() {
        if (xVelocity > 0) {
            xVelocity -= 0.25;
        } else {
            xVelocity = 0;
        }
        if (Math.abs(yVelocity) < TERMINAL_VELOCITY_Y && !isActive) {
            yVelocity -= 0.1;
        }
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
     * projectiles can collide with barrier
     * @param barrier
     */
    public void collideWith(Barrier barrier) {
        if (this.overlaps(barrier) && isActive) {
            barrier.collideWithProjectile();
        }
    }
}