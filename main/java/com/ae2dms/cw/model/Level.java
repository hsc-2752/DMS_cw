package com.ae2dms.cw.model;

import com.ae2dms.cw.Main;
import com.ae2dms.cw.consts.SceneConst;
import com.ae2dms.cw.consts.WorldConst;
import com.ae2dms.cw.model.enemyFatory.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Level class reads from the World file and put elements inside corresponding Arraylists
 */
public class Level {

    private static Level instance = new Level();
    public static Level getInstance(){
        return instance;
    }

    /**
     * initial all game objects with the level maps
     * @param lv the integer index for levels ranging from 1-3
     * @param life give hero lives
     * @param score pass the score
     * @return
     */
    public static AllGameObject setLevel(int lv, int life, int score){
        AllGameObject allGameObject = new AllGameObject();
        ArrayList<BoundaryUnit> ceilingUnits= new ArrayList<>();
        ArrayList<BoundaryUnit> floorUnits= new ArrayList<>();
        ArrayList<BoundaryUnit> wallUnits= new ArrayList<>();
        ArrayList<Hero> heroes= new ArrayList<>();
        ArrayList<Enemy> enemies= new ArrayList<>();
        ArrayList<Barrier> barriers = new ArrayList<>();
        String worldUrl = "";
        if (lv == 1) {
            worldUrl = WorldConst.level1;
        }else if(lv == 2){
            worldUrl = WorldConst.level2;
        }else{
            worldUrl = WorldConst.level3;
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader( new FileReader(Main.class.getResource(worldUrl).getPath()));
            String currentLine;
            int row = 0;
            do {
                currentLine = reader.readLine();
                if( currentLine != null ) {
                    for (int col = 0; col < SceneConst.WIDTH; col++) {
                        //System.out.println(col+" "+row);
                        try{
                            currentLine.charAt(col);
                        }catch(Exception e){
                            System.out.println(col+" "+row);
                        }
                        if (currentLine.charAt(col) == '*') {
                            //System.out.println(col+" "+row);
                            floorUnits.add(new FloorUnit(col*SceneConst.UNIT_SIZE, row*SceneConst.UNIT_SIZE));
                        } else if (currentLine.charAt(col) == 'H') {
                            heroes.add(new Hero( col*SceneConst.UNIT_SIZE, row*SceneConst.UNIT_SIZE,life,score));
                        } else if (currentLine.charAt(col) == '|') {
                           wallUnits.add(new WallUnit(col*SceneConst.UNIT_SIZE, row*SceneConst.UNIT_SIZE));
                        } else if (currentLine.charAt(col) == '_') {
                            ceilingUnits.add(new CeilingUnit(col*SceneConst.UNIT_SIZE, row*SceneConst.UNIT_SIZE));
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
                        } else if (currentLine.charAt(col) == ']'){
                            barriers.add(new Barrier(col*SceneConst.UNIT_SIZE,row*SceneConst.UNIT_SIZE));
                        }
                    }
                    row = row+1;
                }
            } while( currentLine != null );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if( reader != null )
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
        }
        allGameObject.setCeilingUnits(ceilingUnits);
        allGameObject.setFloorUnits(floorUnits);
        allGameObject.setWallUnits(wallUnits);
        allGameObject.setHeroes(heroes);
        allGameObject.setEnemies(enemies);
        allGameObject.setBarriers(barriers);
        allGameObject.setLevel(lv);
        return allGameObject;
    }
}
