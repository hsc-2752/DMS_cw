package com.ae2dms.cw.model.enemyFatory;

/**
 * The normalEnemyFactory is the EnemyFactory that only produces normalEnemy (with different speed)
 */
public class NormalEnemyFactory implements EnemyFactory {
    @Override
    public Enemy newEnemy(int colNum, int rowNum, String speedType) {
        if (speedType.equals("slow")){
            return new NormalEnemy(colNum, rowNum, false);
        }
        else if(speedType.equals("fast")){
            return new NormalEnemy(colNum, rowNum, true);
        }
        else{
            return new NormalEnemy(colNum, rowNum);
        }

    }
}
