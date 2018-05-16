package com.example.minhduc.fitnessapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Rest extends AppCompatActivity {
    private TextView textViewRoundInfo;
    private TextView textViewRestTimer;
    private TextView textViewNextExercise;
    private Button buttonQuit;
    private Button buttonSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);

        String roundInfo = getIntent().getStringExtra("roundInfo");
        int restTime = getIntent().getIntExtra("restTime", -1);
        String nextExerciseName = getIntent().getStringExtra("nextExerciseName");
        int nextExerciseRep = getIntent().getIntExtra("nextExerciseRep", -1);

        textViewRoundInfo = findViewById(R.id.textViewRoundInfo);
        textViewRoundInfo.setText(roundInfo);

        textViewNextExercise = findViewById(R.id.textViewNextExercise);
        textViewNextExercise.setText("Next: " + nextExerciseName + " x " + nextExerciseRep + " reps");

        textViewRestTimer = findViewById(R.id.textViewRestTimer);
        new CountDownTimer((restTime + 1) * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                textViewRestTimer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                setResult(Activity.RESULT_OK);
                finish();
            }

        }.start();

        buttonQuit = findViewById(R.id.buttonQuitWorkout);
        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQuitConfirmDialog();
            }
        });

        buttonSkip = findViewById(R.id.buttonSkip);
        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }

    public void onBackPressed() {
        createQuitConfirmDialog();
    }

    private void createQuitConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to quit?")
                .setTitle("Quit training");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                setResult(Activity.RESULT_CANCELED);
                finish();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
