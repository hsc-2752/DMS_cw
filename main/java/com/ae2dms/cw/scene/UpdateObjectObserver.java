package com.ae2dms.cw.scene;

import com.ae2dms.cw.GameFeature;
import com.ae2dms.cw.consts.FxmlConst;
import com.ae2dms.cw.consts.SceneConst;
import com.ae2dms.cw.model.Character;
import com.ae2dms.cw.model.*;
import com.ae2dms.cw.model.basic.GameObject;
import com.ae2dms.cw.model.enemyFatory.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

import static javafx.scene.paint.Color.BLACK;

/**
 * Observer Design Pattern
 * This object is used to update all objects, extends an observer interface IObserver
 */
public class UpdateObjectObserver implements IObserver {

    private ArrayList<BoundaryUnit> ceilingUnits = new ArrayList<BoundaryUnit>();
    private ArrayList<BoundaryUnit> floorUnits = new ArrayList<BoundaryUnit>();
    private ArrayList<BoundaryUnit> wallUnits = new ArrayList<BoundaryUnit>();
    private ArrayList<Hero> heroes = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<HeroProjectile> heroProjectiles = new ArrayList<>();
    private ArrayList<EnemyProjectile> enemyProjectiles = new ArrayList<>();
    private ArrayList<Fruit> fruits = new ArrayList<>();
    private ArrayList<GameObject> toBeRemoved = new ArrayList<>();
    private ArrayList<Bubble> bubbles = new ArrayList<>();
    private ArrayList<Barrier> barriers = new ArrayList<>();
    Timer timer;
    private boolean isRunning;
    private int lv = 0;

    /**
     * get all objects and store in the corresponding ArrayLists of this class
     * @param allGameObject all objects in the game
     */
    public UpdateObjectObserver(AllGameObject allGameObject) {
        this.ceilingUnits = allGameObject.getCeilingUnits();
        this.floorUnits = allGameObject.getFloorUnits();
        this.wallUnits = allGameObject.getWallUnits();
        this.heroes = allGameObject.getHeroes();
        this.enemies = allGameObject.getEnemies();
        this.heroProjectiles = allGameObject.getHeroProjectiles();
        this.enemyProjectiles = allGameObject.getEnemyProjectiles();
        this.fruits = allGameObject.getFruits();
        this.bubbles = allGameObject.getBubbles();
        this.barriers = allGameObject.getBarriers();
        isRunning = false;
        timer = new Timer();
        this.lv = allGameObject.getLevel();
    }

    /**
     * update and paint
     * @param gc the graphics context to draw on
     * @return whether the game is still running
     */
    @Override
    public boolean update(GraphicsContext gc) {
        updatePosition();
        paintComponent(gc);
        return isRunning;
    }

    /**
     * draw the Timer on the gc
     * @param gc
     */
    void drawTimer(GraphicsContext gc) {
        gc.setFill(BLACK);
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.fillText(timer.getTimeString(), SceneConst.MAX_WIDTH-50, 50);
    }

    /**
     * draw the Score on the gc
     * @param gc
     */
    void drawScore(GraphicsContext gc) {
        gc.setFill(BLACK);
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.fillText("Score: " + String.valueOf(heroes.get(0).score.getMark()), SceneConst.MAX_WIDTH - 50, 100);
    }

    /**
     * draw the life of both hero and enemy (polymorphism, different behavior depending on the input character)
     * @param gc
     */
    void drawLife(Character character, GraphicsContext gc) {
        character.drawLife(gc);
    }

    public void start() {
        isRunning = true;
    }

    /**
     * This game sticks to single player
     * @return the hero in the first place of the list
     */
    public Hero getHero() {
        if (heroes != null) {
            return heroes.get(0);
        }
        return null;
    }

    public void addHeroProjectile(HeroProjectile heroProjectile) {
        heroProjectiles.add(heroProjectile);
    }

    public void addEnemyProjectile(EnemyProjectile enemyProjectile) {
        enemyProjectiles.add(enemyProjectile);
    }

    public void addFruit(Fruit fruit) {
        fruits.add(fruit);
    }

    public void addBubble(Bubble bubble) {
        bubbles.add(bubble);
    }

    public void addBarrier(Barrier barrier) {
        barriers.add(barrier);
    }

    /**
     * draws all drawable elements
     */
    public void paintComponent(GraphicsContext gc) {
        if (!isRunning) {
            return;
        }
        drawTimer(gc);
        for (BoundaryUnit ceilingUnit : ceilingUnits) {
            ceilingUnit.drawOn(gc);
        }
        for (BoundaryUnit floorUnit : floorUnits) {
            floorUnit.drawOn(gc);
        }
        for (BoundaryUnit wallUnit : wallUnits) {
            wallUnit.drawOn(gc);
        }
        for (Hero hero : heroes) {
            hero.drawOn(gc);
            drawLife(hero, gc);
        }
        for (Enemy enemy : enemies) {
            enemy.drawOn(gc);
            drawLife(enemy, gc);
        }
        for (EnemyProjectile enemyProjectile : enemyProjectiles) {
            enemyProjectile.drawOn(gc);
        }
        for (HeroProjectile heroProjectile : heroProjectiles) {
            heroProjectile.drawOn(gc);
        }
        for (Fruit fruit : fruits) {
            fruit.drawOn(gc);
        }
        for (Bubble bubble : bubbles) {
            bubble.drawOn(gc);
        }
        for (Barrier barrier : barriers) {
            barrier.drawOn(gc);
        }
        drawScore(gc);
    }

    /**
     * update all positions according to movements and collisions
     */
    public void updatePosition() {
        if (!isRunning) {
            return;
        }
        if (enemies.size() == 0) {
            isRunning = false;
            if (lv < 3) {
                SwitchScene.buildGame(lv + 1, GameFeature.getStorm(), heroes.get(0).getLife(), heroes.get(0).score.getMark());
            } else {
                for (Hero hero : heroes) {
                    hero.score.setMark(hero.score.getMark()+200/timer.getDuration());
                    hero.score.store();
                   /// hero.setLife(3);
                }
                SwitchScene.switchScene(FxmlConst.HEIGHSCORESCENCE);
            }
        }
        for (Hero hero : heroes) {
            if (hero.getLife() == 0) {
                isRunning = false;
            }
            hero.update();
        }
        for (Enemy enemy : enemies) {
            EnemyProjectile projectile = enemy.randomMove();
            if (projectile != null) {
                addEnemyProjectile(projectile);
            }
            enemy.update();
            if (enemy.canRemove) {
                toBeRemoved.add(enemy);
            }
        }
        for (EnemyProjectile enemyProjectile : enemyProjectiles) {
            enemyProjectile.update();
            if (enemyProjectile.canRemove) {
                toBeRemoved.add(enemyProjectile);
            }
        }
        for (HeroProjectile heroProjectile : heroProjectiles) {
            heroProjectile.update();
            if (heroProjectile.canRemove) {
                toBeRemoved.add(heroProjectile);
            }
        }
        for (Fruit fruit : fruits) {
            fruit.update();
            if (fruit.canRemove) {
                toBeRemoved.add(fruit);
            }
        }
        for (Bubble bubble : bubbles) {
            bubble.update();
            if (bubble.canRemove) {
                toBeRemoved.add(bubble);
            }
        }
        for (Barrier barrier : barriers) {
            barrier.update();
            if (barrier.canRemove) {
                toBeRemoved.add(barrier);
            }
        }
        // Colliding...
        // Units initiate collisions with Heroes, Enemies, and Fruits

        UnitCollide(ceilingUnits);
        UnitCollide(wallUnits);
        UnitCollide(floorUnits);

        for (Barrier barrier : barriers) {
            for (Hero hero : heroes) {
                barrier.collideWith(hero);
            }
            for (Enemy enemy : enemies) {
                barrier.collideWith(enemy);
                enemy.collideWith(barrier);
            }
            for (EnemyProjectile enemyProjectile : enemyProjectiles) {
                barrier.collideWith(enemyProjectile);
            }
        }


        //Enemies initiate collisions with Heroes
        try {
            for (Enemy enemy : enemies) {
                for (Hero hero : heroes) {
                    Fruit fruit = enemy.collideWith(hero);
                    if (fruit != null) {
                        fruits.add(fruit);
                    }
                }
            }
        } catch (Exception ignored) {
        }
        // HeroProjectiles initiate collisions with Heroes and Enemies
        for (HeroProjectile heroProjectile : heroProjectiles) {
            for (Enemy enemy : enemies) {
                heroProjectile.collideWith(enemy);
            }
            for (Barrier barrier : barriers) {
                heroProjectile.collideWith(barrier);
            }
        }
        try {
            for (EnemyProjectile enemyProjectile : enemyProjectiles) {
                for (Hero hero : heroes) {
                    enemyProjectile.collideWith(hero);
                }
            }
        } catch (Exception ignored) {
        }
        // Fruits intiate collisions with Heroes
        for (Fruit fruit : fruits) {
            for (Hero hero : heroes) {
                fruit.collideWith(hero);

            }
        }
        for (Bubble bubble : bubbles) {
            for (Enemy enemy : enemies) {
                bubble.collideWith(enemy);
            }
        }
        // Removing...
        for (GameObject obj : toBeRemoved) {
            remove(obj);
        }
        toBeRemoved.removeAll(toBeRemoved);
    }

    /**
     * removes a single object from the screen
     *
     * @param obj
     */
    public void remove(GameObject obj) {
        ceilingUnits.remove(obj);
        floorUnits.remove(obj);
        wallUnits.remove(obj);
        heroes.remove(obj);
        enemies.remove(obj);
        enemyProjectiles.remove(obj);
        heroProjectiles.remove(obj);
        fruits.remove(obj);
        bubbles.remove(obj);
        if (obj instanceof Fruit) {
            heroes.get(0).score.setMark(heroes.get(0).score.getMark() + 10); // each "fruit" collected will give hero 10 mark as reward
        }
        barriers.remove(obj);
    }

    /**
     * deal with collision with boundary units
     * @param units
     */
    public void UnitCollide(ArrayList<BoundaryUnit> units) {
        for (BoundaryUnit unit : units) {
            for (Hero hero : heroes) {
                unit.collideWith(hero);
            }
            for (Enemy enemy : enemies) {
                unit.collideWith(enemy);
                enemy.collideWith(unit);
            }
            for (Fruit fruit : fruits) {
                unit.collideWith(fruit);
            }
            for (EnemyProjectile enemyProjectile : enemyProjectiles) {
                unit.collideWith(enemyProjectile);
            }
            for (HeroProjectile heroProjectile : heroProjectiles) {
                unit.collideWith(heroProjectile);
            }
        }
    }
}

