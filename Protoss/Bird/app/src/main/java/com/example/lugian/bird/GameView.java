package com.example.lugian.bird;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {

    //Canvas
    private int canvasWidth;
    private int canvasHeight;
    //Score
    private int score;
    private Paint scorePaint = new Paint();
    //private Bitmap bird;
    private Bitmap bird[] = new Bitmap[2];
    private int birdX = 10;
    private int birdY = 500;
    private int birdSpeed;

    // Blue ball
    private int blueX;
    private int blueY;
    private int blueSpeed = 20;
    private Paint bluePaint = new Paint();
    //Black ball
    private int blackX;
    private int blackY;
    private int blackSpeed = 22;
    private Paint blackPaint = new Paint();
    //Status
    private boolean touched = false;

    private Bitmap background;



    private Paint levelPaint = new Paint();

    //Health
    private Bitmap life[] = new Bitmap[2];
    private int life_count ;

    public GameView(Context context) {
        super(context);



        bird[0] = BitmapFactory.decodeResource(getResources(), R.drawable.bird1);
        bird[1] = BitmapFactory.decodeResource(getResources(), R.drawable.bird2);

        background = BitmapFactory.decodeResource(getResources(), R.drawable.bg);

        bluePaint.setColor(Color.GRAY);
        bluePaint.setAntiAlias(false);

        blackPaint.setColor(Color.RED);
        blackPaint.setAntiAlias(false);

        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(32+16);
        scorePaint.setAntiAlias(true);

        levelPaint.setColor(Color.BLACK);
        levelPaint.setTextSize(32+16);
        levelPaint.setTextAlign(Paint.Align.CENTER);
        levelPaint.setAntiAlias(true);

        life[0]= BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        life[1]= BitmapFactory.decodeResource(getResources(), R.drawable.heart_g);

        //First position

        score = 0;
        life_count = 3;
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();


        canvas.drawBitmap(background, 0 ,0, null);




       //Bird
        int minBirdY = bird[0].getHeight();
        int maxBirdY = canvasHeight - bird[0].getHeight()*3;
        birdY += birdSpeed;
        if(birdY < minBirdY){
            birdY = minBirdY;
        }
        if(birdY > maxBirdY){
            birdY = maxBirdY;
        }
        birdSpeed += 2;
        if(touched){
            //Flap
            canvas.drawBitmap(bird[1], birdX, birdY, null);
            touched = false;
        }else{
            canvas.drawBitmap(bird[0], birdX, birdY, null);
        }
        //canvas.drawBitmap(bird, 0, 0, null);
        //Blue ball

        blueX -= blueSpeed;
        if(hitCheck(blueX, blueY)){
            score += 10;
            blueX = -100;
        }

        if(blueX < 0){
            blueX = canvasWidth+30;
            blueY = (int)Math.floor(Math.random() * (maxBirdY - minBirdY)) + minBirdY;

        }

        canvas.drawCircle(blueX, blueY, 20, bluePaint);

        //Black ball
        blackX -= blackSpeed;
        if(hitCheck(blackX, blackY)){
            blackX = -100;
            life_count--;

            if(life_count < 0){
                life_count = 0;
            }
        }
        if(blackX < 0){
            blackX = canvasWidth+100;
            blackY = (int)Math.floor(Math.random() * (maxBirdY - minBirdY)) + minBirdY;
        }
        canvas.drawCircle(blackX, blackY,30, blackPaint);

        canvas.drawText("SCORE: " + score, 20, 60, scorePaint);

        canvas.drawText("Lv: 1", canvasWidth/2, 60, levelPaint);

        for(int i = 0; i < 3; ++i){
            int x = 560 +60*i;
            if(i < life_count){
                canvas.drawBitmap(life[0], x, 30, null);
            }
            else{
                canvas.drawBitmap(life[1], x, 30, null);
            }
        }




    }


    public boolean hitCheck(int x, int y){

        if(birdX < x && x < birdX + 116 && birdY < y && y < birdY + 88) {

            return true;
        }
        return false;
    }

    public int add(int x, int y){
        return x+y;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            touched = true;
            birdSpeed = -25;
            return true;
        }
        return false;

    }
    public int getLifeCount(){
        return this.life_count;
    }
    public int getScore(){
        return this.score;
    }

    public int getBirdX(){
        return this.birdX;
    }
    public int getBirdY(){
        return this.birdY;
    }
    public int getBirdWidth(){
        return bird[0].getWidth();
    }
    public int getBirdHeight(){
        return bird[0].getHeight();
    }

}
