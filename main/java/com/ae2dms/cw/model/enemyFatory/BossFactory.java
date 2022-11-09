package com.ae2dms.cw.model.enemyFatory;

/**
 * The bossFactory is the EnemyFactory that only produces boss (with different speed)
 */

public class BossFactory implements EnemyFactory {
    @Override
    public Enemy newEnemy(int x, int y,String speedType) {
        if (speedType.equals("verySlow")){
            return new BossEnemy(x, y, false);
        }
        else if(speedType.equals("extremelySlow")){
            return new BossEnemy(x, y, true);
        }
        else{
            return new BossEnemy(x, y);
        }

    }
}
