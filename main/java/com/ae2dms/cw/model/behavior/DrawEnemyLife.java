package com.ae2dms.cw.model.behavior;

import com.ae2dms.cw.model.enemyFatory.BossEnemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class is the subclass of DrawLifeBehavior, defines the unique draw life behavior of enemy
 */
public class DrawEnemyLife implements DrawLifeBehavior{
    @Override
    public void drawLife(GraphicsContext g) {
        for(int i = 0; i < BossEnemy.getLife(); i++) {
            g.setFill(Color.rgb(150, 140, 200));
            g.fillRect(750-i*(10), 30, (5), (5));
            g.setFill(Color.BLACK);
        }
    }
}
