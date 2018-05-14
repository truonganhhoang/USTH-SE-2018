package com.example.lugian.bird;

import android.support.v7.app.AppCompatActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;
@RunWith(Parameterized.class)
public class HitCheckTest extends AppCompatActivity {

    private GameView gameView;
    private int x;
    private int y;
    private boolean expectedResult;
    private static int birdWidth = 116;
    private static int birdHeight = 88;

    //static variable




    @Before
    public void setUp() throws Exception {
        gameView = new GameView(this);
    }

    public HitCheckTest(int x, int y, boolean expectedResult){
        this.x = x;
        this.y = y;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection hitCheck() {

        return Arrays.asList(new Object[][] {
                {12, 50, false},
                {11, 501, true},
        });
    }

    @Test
    public void testHitCheck() throws Exception {
        assertEquals(expectedResult, gameView.hitCheck(x, y));
    }
}