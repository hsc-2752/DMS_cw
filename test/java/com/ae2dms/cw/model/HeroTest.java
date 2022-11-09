package com.ae2dms.cw.model;

import com.ae2dms.cw.scene.SwitchScene;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.testfx.api.FxRobot;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
    Hero hero;

    @Test
    void constructorTest(){
        hero = new Hero(0,0,3,0);
        assertEquals(0,hero.getX());
        assertEquals(0,hero.getY());
        assertEquals(3,hero.getLife());
    }


    @Test
    void shootProjectile() {
        hero = new Hero(0,0,3,0);
        HeroProjectile hp = hero.shootProjectile();
        assertTrue(hp!= null);
    }

    @Test
    void buildBarrier() {
        hero = new Hero(0,0,3,0);
        Barrier b = hero.buildBarrier();
        assertTrue(b!= null);
    }

    @Test
    void die() {
        hero = new Hero(0,0,3,0);
        hero.die();
        assertEquals(2,Hero.getLife());
    }
}