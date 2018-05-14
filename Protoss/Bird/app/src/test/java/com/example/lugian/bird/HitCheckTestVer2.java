package com.example.lugian.bird;

import android.support.v7.app.AppCompatActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;



public class HitCheckTestVer2 extends AppCompatActivity {
    private GameView gameView;
    private static int birdWidth = 116;
    private static int birdHeight = 88;
    @Before
    public void setUp(){

    }




    @Test
    public void hitCheck() {
        int birdX = gameView.getBirdX();
        int birdY = gameView.getBirdY();
        boolean expected = true;
        assertEquals(expected, gameView.hitCheck(birdX + birdWidth-3, birdY + 2));

    }
}