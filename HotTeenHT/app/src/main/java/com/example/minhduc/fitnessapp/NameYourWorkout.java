package com.example.minhduc.fitnessapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import database.DbHelper;

public class NameYourWorkout extends AppCompatActivity {
    private EditText workoutNameField;
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_your_workout);

        workoutNameField = findViewById(R.id.workoutNameEditText);

        okButton = findViewById(R.id.confirmWorkoutNameButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateNewWorkout.class);
                i.putExtra("WorkoutName", workoutNameField.getText().toString());
                startActivity(i);
                finish();
            }
        });
    }
}
