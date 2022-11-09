package com.ae2dms.cw.model.behavior;

import com.ae2dms.cw.model.Hero;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class is the subclass of DrawLifeBehavior, defines the unique draw life behavior of hero
 */
public class DrawHeroLife implements DrawLifeBehavior {

   @Override
   public void drawLife(GraphicsContext g) {
       for(int i = 0; i < Hero.getLife(); i++) {
           g.setFill(Color.rgb(255, 0, 0));
           g.fillRect(i*(Hero.getSIZE()-5)+30, 30, (Hero.getSIZE()-10), (Hero.getSIZE()-10));
           g.setFill(Color.BLACK);
       }
   }

}
