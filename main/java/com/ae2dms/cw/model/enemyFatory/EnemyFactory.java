package com.ae2dms.cw.model.enemyFatory;

/**
 * The EnemyFactory class is the factory of enemies
 */
public interface EnemyFactory {
    Enemy newEnemy(int colNum, int rowNum,String speedType);
}
