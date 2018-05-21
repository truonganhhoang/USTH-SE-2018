package com.example.lugian.bird;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        TextView scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        TextView highScoreTextView = (TextView) findViewById(R.id.highScoreTextView);

        int score = getIntent().getIntExtra("SCORE", 0);
        scoreTextView.setText(score + "");

        SharedPreferences settings = getSharedPreferences("GAME DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH SCORE", 0);

        if(score > highScore){
            highScoreTextView.setText("High Score: "+score);
            //Save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH SCORE", score);
            editor.commit();
        }
        else {
            highScoreTextView.setText("High Score: " + highScore);
        }

    }

    public void tryAgain(View view){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
