package com.example.minhduc.fitnessapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import database.DbHelper;
import database.DbSchema;

public class CustomWorkout extends AppCompatActivity {
    private DbHelper dbHelper;
    private RecyclerView customWorkoutRecycleView;
    private Button addButton;
    private WorkoutAdapter workoutAdapter;
    private ArrayList<String> workoutNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_workout);

        addButton = findViewById(R.id.addWorkoutButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NameYourWorkout.class);
                startActivity(i);
            }
        });


        dbHelper = new DbHelper(this);

        workoutNames = dbHelper.getAllWorkouts();
        workoutAdapter = new WorkoutAdapter(workoutNames);

        customWorkoutRecycleView = findViewById(R.id.workoutRecyclerView);
        customWorkoutRecycleView.setLayoutManager(new LinearLayoutManager(this));
        customWorkoutRecycleView.setAdapter(workoutAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        workoutNames.clear();
        workoutNames.addAll(dbHelper.getAllWorkouts());
        workoutAdapter.notifyDataSetChanged();
    }
}
