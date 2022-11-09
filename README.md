# AE2DMS-CW-20214805

## How to run:
1. Open BubbleBobble project instead of the unzip "AE2DMS-CW-20214805" one
2. Use JDK16
3. run Main in AppStart

## Refactoring:
1. Change from Swing to Javafx
2. Fix bug: Hero out of boundary due to unrealized collide method
3. Encapsulate field with set and get
4. Rename GamePanel to MainScene, handle keyEvents here instead of in Hero 
5. InteractiveWorld is more like an Observer, drawing according to constant updates. Thus, rename it as UpdateObjectObserver extends IObject Interface. 
6. Encapsulate all game objects as AllGameObject which is being observed. This reduces the length of parametersList, avoid always passing 'world' when creating GameObjects.
7. Separate constants to Interfaces, avoid callings like "world.getWidth()".
8. Change Inheritance Relationships. As shown in figure below: <img src="/Diagram/Refactor_7.png"/>
9. Three Methods(CollideWith) in GameObject is moved down to CollidePossibleObject
10. Separate the process of Constructing World to a separate class Level, also allows constructing different levels
11. In UpdateObjectObserver(InteractiveWorld), use polymorphism and extract the collideWith method out from updatePosition;
12. Following10, polymorphism makes unifying collideWith method in Enemy


## Design Pattern:
1. MVC Pattern
model, view and control are separated and placed in different packages
2. Singleton Pattern
Used in GameFeature Class since only one instance is used globally in the project
```
    private static GameFeature instance = new GameFeature();
    public static GameFeature getInstance() {
        return instance;
    }
```
3. Observer Pattern
Used for updating all changes, draw updated positions
```
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
```
4. Factory Method Pattern
Used for constructing different kinds of enimies(boss, normal, fast, slow) <img src="/Diagram/factory.png" alt="factory" width="200" height="120" />
```
public interface EnemyFactory {
    Enemy newEnemy(int colNum, int rowNum,String speedType);
}
```
```
} else if (currentLine.charAt(col) == 'M') {
    enemies.add((new NormalEnemyFactory()).newEnemy(col*SceneConst.UNIT_SIZE, row*SceneConst.UNIT_SIZE,"normal"));
} else if (currentLine.charAt(col) == 'N') {
    enemies.add((new NormalEnemyFactory()).newEnemy(col*SceneConst.UNIT_SIZE, row*SceneConst.UNIT_SIZE,"slow"));
} else if (currentLine.charAt(col) == 'L') {
    enemies.add((new NormalEnemyFactory()).newEnemy(col*SceneConst.UNIT_SIZE, row*SceneConst.UNIT_SIZE,"fast"));
} else if (currentLine.charAt(col) == 'B') {
    enemies.add((new BossFactory()).newEnemy(col*SceneConst.UNIT_SIZE, row*SceneConst.UNIT_SIZE,"verySlow"));
} else if (currentLine.charAt(col) == 'O') {
    enemies.add((new BossFactory()).newEnemy(col*SceneConst.UNIT_SIZE, row*SceneConst.UNIT_SIZE,"extremelySlow"));
```
5. Decorator Pattern
Used for the behaviour of Barrier, which contains the combined functionality of Wall unit and Floor unit
```
public class Barrier extends WallUnit 
```
```
public void collideWith(CollidePossibleObject cpo) {
	super.collideWith(cpo);
	double top = cpo.getY();
	double bottom = top + cpo.getHeight();
	if (this.overlaps(cpo) && cpo.yVelocity > 0) {
		if (bottom < y + height) {
			moveAboveUnit(cpo);
               cpo.collideWithFloor();
		}
		if (top > y){
			moveBelowUnit(cpo);
			cpo.collideWithCeiling();
		}
	}
}
```
6. Strategy Pattern

In DrawLifeBehavior Interface
```
public interface DrawLifeBehavior {
    void drawLife(GraphicsContext g);
}
```
In DrawEnemyLife
```
public class DrawEnemyLife implements DrawLifeBehavior
```
In DrawHeroLife
```
public class DrawHeroLife implements DrawLifeBehavior 
```
In Character
```
public abstract class Character extends CollidePossibleObject{
    //...
    DrawLifeBehavior drawLifeBehavior;
    //...
     public Character(int x, int y, DrawLifeBehavior drawLifeBehavior) {
        super(x, y);
        this.drawLifeBehavior = drawLifeBehavior;
    }
}
```
In UpdateObjectObserver
```
    void drawLife(Character character, GraphicsContext gc) {
        character.drawLife(gc);
    }
```
## Additions:
1. Storm mode : 
Background switch rapidly between white and black, when it's black, enemy will be invisible <img src="/Diagram/Storm-1.png" alt="Storm_day" width="600" height="510" /> <img src="/Diagram/Storm-2.png" alt="Storm_night" width="600" height="510"/>
2. Three levels: can be chosen manually, or auto-switch after success in the previous level: enemies and map are different between levels <img src="/Diagram/level.png" alt="level" width="600" height="510"/> 
3. A new score counting mechanism:
     Each Fruit = 10 bonus , Kill a boss = 20 bonus, A time bonus will be given considering your speed at the end
4. Eliminable barrier(barrier prevents hero crossing it horizontally or from up to down, but allows crossing it from down to up)
5. Hero can also build barrier for protection or use it to help build its own path <img src="/Diagram/barrier.png" alt="barrier" width="600" height="510"/>
6. Sound controlling <img src="/Diagram/SoundOn.png" alt="soundOn" width="124" height="66"/> <img src="/Diagram/SoundOff.png" alt="soundOff" width="124" height="66"/>
7. Realize Enemy Projectiles, bubble, give hero several lives
8. Add Boss enemy with multiple lives but slower speed 
9. Replace hero and enemy with pictures <img src="/Diagram/MainAddition-1.png" alt="mainAddition" width="600" height="510"/>
10. Different types of hero and enemy with different jump speed
11. ESC can be used to go back to StartScene

## Tests:

### Class: HighScoreController

|Test|Inputs|Expected Outcome|Test Outcome|Result|Reason
|----|------|----------------|------------|------|:----------:|
|CheckListDisplay|(read from file)|0010 2021-11-30 02:24:05|0230 2021-11-30 03:05:51|Fail|Wrong expected result
|CheckListDisplay|(read from file)|0230 2021-11-30 03:05:51|0230 2021-11-30 03:05:51|Pass|Null

### Class: StartSceneController

|Test|Inputs|Expected Outcome|Test Outcome|Result|Reason
|----|------|----------------|------------|------|:----------:|
|ButtomTest_start|click|turn to mainscene|turn to mainscene|Pass|Null
|ButtomTest_storm|click|turn to mainscene storm mode|turn to mainscene storm mode|Pass|Null
|ButtomTest_info|click|turn to info|turn to info|Pass|Null
|ButtomTest_sound|click|turn off sound|turnoff sound|Pass|Null

### Class: Hero

|Test|Inputs|Expected Outcome|Test Outcome|Result|Reason
|----|------|----------------|------------|------|:----------:|
|Constructor|new Hero(0,0,3,0)|(0,0,3,0)|(0,0,3,0)|Pass|Null
|shootProjectile|KeyEvent|heroProjectile created|Error|Fail|wrong parameter in test code
|shootProjectile|KeyEvent|heroProjectile created|heroProjectile created|Pass|wrong parameter in test code
|buildBarrier|KeyEvent|barrier created|barrier created|Pass|Null
|die|set die|life = 2|life = 2|Pass|Null
