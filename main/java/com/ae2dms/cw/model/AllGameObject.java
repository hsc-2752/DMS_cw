package com.ae2dms.cw.model;

import com.ae2dms.cw.model.basic.GameObject;
import com.ae2dms.cw.model.enemyFatory.Enemy;

import java.util.ArrayList;

/**
 * The collection of all game objects and their set and get methods
 */
public class AllGameObject {
    private ArrayList<BoundaryUnit> ceilingUnits= new ArrayList<BoundaryUnit>();
    private ArrayList<BoundaryUnit> floorUnits= new ArrayList<BoundaryUnit>();
    private ArrayList<BoundaryUnit> wallUnits= new ArrayList<BoundaryUnit>();
    private ArrayList<Hero> heroes= new ArrayList<>();
    private ArrayList<Enemy> enemies= new ArrayList<>();
    private ArrayList<HeroProjectile> heroProjectiles= new ArrayList<>();
    private ArrayList<EnemyProjectile> enemyProjectiles= new ArrayList<>();
    private ArrayList<Fruit> fruits= new ArrayList<>();
    private ArrayList<GameObject> toBeRemoved= new ArrayList<>();
    private ArrayList<Bubble> bubbles= new ArrayList<>();
    private ArrayList<Barrier> barriers = new ArrayList<>();
    private int level = 0;

    /**
     * get ceilingUnits
     * @return ceilingUnits
     */
    public ArrayList<BoundaryUnit> getCeilingUnits() {
        return ceilingUnits;
    }

    /**
     * set ceilingUnits
     * @param ceilingUnits
     */
    public void setCeilingUnits(ArrayList<BoundaryUnit> ceilingUnits) {
        this.ceilingUnits = ceilingUnits;
    }

    /**
     * get floorUnits
     * @return floorUnits
     */
    public ArrayList<BoundaryUnit> getFloorUnits() {
        return floorUnits;
    }

    /**
     * set floorUnits
     * @param floorUnits
     */
    public void setFloorUnits(ArrayList<BoundaryUnit> floorUnits) {
        this.floorUnits = floorUnits;
    }

    /**
     * get wallUnits
     * @return wallUnits
     */
    public ArrayList<BoundaryUnit> getWallUnits() {
        return wallUnits;
    }

    /**
     * set floorUnits
     * @param wallUnits
     */
    public void setWallUnits(ArrayList<BoundaryUnit> wallUnits) {
        this.wallUnits = wallUnits;
    }

    /**
     * get heroes
     * @return heroes
     */
    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    /**
     * set heroes
     * @param heroes
     */
    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

    /**
     * get enemies
     * @return enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * set enemies
     * @param enemies
     */
    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    /**
     * get heroProjectiles
     * @return heroProjectiles
     */
    public ArrayList<HeroProjectile> getHeroProjectiles() {
        return heroProjectiles;
    }

    /**
     * get enemyProjectiles
     * @return enemyProjectiles
     */
    public ArrayList<EnemyProjectile> getEnemyProjectiles() {
        return enemyProjectiles;
    }

    /**
     * get fruits
     * @return fruits
     */
    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    /**
     * set toBeRemoved
     * @param toBeRemoved
     */
    public void setToBeRemoved(ArrayList<GameObject> toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }

    /**
     * get bubbles
     * @return bubbles
     */
    public ArrayList<Bubble> getBubbles() {
        return bubbles;
    }

    /**
     * get barriers
     * @return barriers
     */
    public ArrayList<Barrier> getBarriers() {
        return barriers;
    }

    /**
     * set barriers
     * @param barriers
     */
    public void setBarriers(ArrayList<Barrier> barriers) {
        this.barriers = barriers;
    }

    /**
     * get level
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * set level
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }
}
