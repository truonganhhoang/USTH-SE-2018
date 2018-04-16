package com.example.lugian.bird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
    //Canvas
    private int canvasWidth;
    private int canvasHeight;


    //private Bitmap bird;
    private Bitmap bird[] = new Bitmap[2];
    private int birdX = 10;
    private int birdY;
    private int birdSpeed;
    //Status
    private boolean touched = false;

    private Bitmap background;

    private Paint scorePaint = new Paint();

    private Paint levelPaint = new Paint();

    private Bitmap life[] = new Bitmap[2];

    public GameView(Context context) {
        super(context);

        bird[0] = BitmapFactory.decodeResource(getResources(), R.drawable.bird1);
        bird[1] = BitmapFactory.decodeResource(getResources(), R.drawable.bird2);

        background = BitmapFactory.decodeResource(getResources(), R.drawable.bg);

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
        birdY = 500;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();



        canvas.drawBitmap(background, 0 ,0, null);
        canvas.drawText("SCORE: 0", 20, 60, scorePaint);
        canvas.drawText("Lv: 1", canvasWidth/2, 60, levelPaint);
        canvas.drawBitmap(life[0], 560, 30, null);
        canvas.drawBitmap(life[0], 560+60, 30, null);
        canvas.drawBitmap(life[0], 560+60*2, 30, null);
       //Bird
        int minBirdY = bird[0].getHeight();
        int maxBirdY = canvasHeight - bird[0].getHeight();
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
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            touched = true;
            birdSpeed = -25;
        }
        return true;
    }
}
