package com.example.lugian.bird;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    private GameView gameView;
    private Handler handler = new Handler();
    private final static long TIME_INTERVAL = 20;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameView = new GameView(this);
        setContentView(gameView);


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                        isLost();
                    }
                });

            }
        }, 0 ,TIME_INTERVAL);
    }

    public void isLost(){
        if (gameView.getLifeCount() <= 0){
            timer.cancel();
            timer = null;
            Intent intent = new Intent(getApplicationContext(), EndGame.class);
            intent.putExtra("SCORE", gameView.getScore());
            startActivity(intent);
        }

    }
}
