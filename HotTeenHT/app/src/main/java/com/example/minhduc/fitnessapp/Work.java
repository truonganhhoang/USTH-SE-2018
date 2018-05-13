package com.example.minhduc.fitnessapp;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Work extends AppCompatActivity {
    private TextView preparationTimer;
    private Button buttonTapWhenDone;
    private String exerciseName;
    private int exerciseRep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        exerciseName = getIntent().getStringExtra("exerciseName");
        exerciseRep = getIntent().getIntExtra("exerciseRep", -1);

        buttonTapWhenDone = findViewById(R.id.buttonTapWhenDone);
        buttonTapWhenDone.setVisibility(View.INVISIBLE);
        buttonTapWhenDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        preparationTimer = findViewById(R.id.textViewPreparationTimer);

        final String exerciseDetail = (exerciseName + "\n" + exerciseRep + " reps").toUpperCase();

        int delay = 1;
        new CountDownTimer(delay * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                preparationTimer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                preparationTimer.setText(exerciseDetail);
                buttonTapWhenDone.setVisibility(View.VISIBLE);
            }

        }.start();
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }
}
