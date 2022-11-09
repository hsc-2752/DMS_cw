package com.ae2dms.cw.model;

/**
 * The SmallComponent class contains projectiles object class(EnemyProjectile and Hero Projectile)
 * and Fruit and Bubble
 */
public abstract class SmallComponent extends CollidePossibleObject{
    public SmallComponent(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

}
