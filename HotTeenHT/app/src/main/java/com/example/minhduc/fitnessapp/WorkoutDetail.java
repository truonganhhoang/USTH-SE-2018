package com.example.minhduc.fitnessapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import database.DbHelper;
import database.DbSchema;

public class WorkoutDetail extends AppCompatActivity {
    private ArrayList<String> exerciseNames;
    private ArrayList<Integer> exerciseReps;
    private ExerciseWithRepsAdapter exerciseWithRepsAdapter;
    private RecyclerView exerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        exerciseNames = new ArrayList<>();
        exerciseReps = new ArrayList<>();

        DbHelper dbHelper = new DbHelper(this);
        Cursor cursor = dbHelper.getExercisesFromWorkoutCursor(getIntent().getIntExtra("WorkoutId", -1));
        int exerciseId;
        if (cursor.moveToFirst()) {
            do {
                exerciseId = cursor.getInt(cursor.getColumnIndex(DbSchema.RelationshipTable.Cols.EXERCISEID));
                exerciseNames.add(dbHelper.getExerciseName(exerciseId));
                exerciseReps.add(cursor.getInt(cursor.getColumnIndex(DbSchema.RelationshipTable.Cols.REPS)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        exerciseWithRepsAdapter = new ExerciseWithRepsAdapter(1, exerciseNames, exerciseReps);
        exerciseList = findViewById(R.id.recyclerViewWorkoutDetail);
        exerciseList.setLayoutManager(new LinearLayoutManager(this));
        exerciseList.setAdapter(exerciseWithRepsAdapter);
    }
}