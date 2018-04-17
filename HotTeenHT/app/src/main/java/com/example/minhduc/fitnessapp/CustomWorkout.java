package com.example.minhduc.fitnessapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import database.DbHelper;
import database.DbSchema;

public class CustomWorkout extends AppCompatActivity {
    private DbHelper dbHelper;
    private RecyclerView customWorkoutRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_workout);


        dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        WorkoutAdapter workoutAdapter = new WorkoutAdapter(dbHelper.getAllWorkouts());

        customWorkoutRecycleView = findViewById(R.id.workoutRecyclerView);
        customWorkoutRecycleView.setLayoutManager(new LinearLayoutManager(this));
        customWorkoutRecycleView.setAdapter(workoutAdapter);
    }
}
