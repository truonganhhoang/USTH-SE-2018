package com.example.minhduc.fitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinishWorkout extends AppCompatActivity {
    TextView textViewFinishWorkout;
    Button buttonOkFinishWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_workout);

        String workoutName = getIntent().getStringExtra("workoutName");
        String congrats = "Congratulations!" + "\n" + "You finished " + workoutName;
        textViewFinishWorkout = findViewById(R.id.textViewFinishWorkout);
        textViewFinishWorkout.setText(congrats);

        buttonOkFinishWorkout = findViewById(R.id.buttonOkFinishWorkout);
        buttonOkFinishWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
