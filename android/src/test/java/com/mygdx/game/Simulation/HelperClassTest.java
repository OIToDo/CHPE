package com.mygdx.game.Simulation;

import com.badlogic.gdx.math.Vector2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HelperClassTest {
    @Before
    public void before(){

    }

    @After
    public void after(){

    }

    @Test
    public void test(){
        Vector2 a = new Vector2(0f, 3f);
        Vector2 b = new Vector2(4f,0f);
        float c = HelperClass.PythagorasTheorem(a.x, a.y, b.x, b.y);
        assertEquals(c, 5f, 0f);
    }
}