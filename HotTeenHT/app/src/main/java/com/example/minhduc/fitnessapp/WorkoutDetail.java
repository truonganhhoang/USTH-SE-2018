package com.example.minhduc.fitnessapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import database.DbHelper;
import database.DbSchema;

public class WorkoutDetail extends AppCompatActivity {
    private ArrayList<String> exerciseNames;
    private ArrayList<Integer> exerciseReps;
    private WorkoutDetailExerciseAdapter workoutDetailExerciseAdapter;
    private RecyclerView exerciseList;
    private SQLiteDatabase db;
    private DbHelper dbHelper;
    private TextView textViewSetupDetail;
    private TextView textViewWorkoutName;
    private int workoutId;
    private int rounds;
    private int restExercises;
    private int restRounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        workoutId = getIntent().getIntExtra("WorkoutId", -1);
        exerciseNames = new ArrayList<>();
        exerciseReps = new ArrayList<>();

        dbHelper = new DbHelper(this);
        db = dbHelper.getReadableDatabase();
        Cursor cursor = getExercisesFromWorkoutCursor(workoutId);
        int exerciseId;
        if (cursor.moveToFirst()) {
            do {
                exerciseId = cursor.getInt(cursor.getColumnIndex(DbSchema.RelationshipTable.Cols.EXERCISEID));
                exerciseNames.add(dbHelper.getExerciseName(exerciseId));
                exerciseReps.add(cursor.getInt(cursor.getColumnIndex(DbSchema.RelationshipTable.Cols.REPS)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        workoutDetailExerciseAdapter = new WorkoutDetailExerciseAdapter(exerciseNames, exerciseReps);
        exerciseList = findViewById(R.id.recyclerViewWorkoutDetail);
        exerciseList.setLayoutManager(new LinearLayoutManager(this));
        exerciseList.setAdapter(workoutDetailExerciseAdapter);

        textViewWorkoutName = findViewById(R.id.textViewWorkoutName);
        textViewWorkoutName.setText(dbHelper.getWorkoutName(workoutId));

        rounds = dbHelper.getRounds(workoutId);
        restExercises = dbHelper.getRestExercises(workoutId);
        restRounds = dbHelper.getRestRounds(workoutId);
        String setupDetail = "Rounds: " + dbHelper.getRounds(workoutId) +
                "\nRest between exercises: " + dbHelper.getRestExercises(workoutId) + " secs" +
                "\nRest between rounds: " + dbHelper.getRestRounds(workoutId) + " secs";
        textViewSetupDetail = findViewById(R.id.textViewSetupDetail);
        textViewSetupDetail.setText(setupDetail);
    }

    private Cursor getExercisesFromWorkoutCursor(int workoutId) {
        Cursor cursor = db.query(DbSchema.RelationshipTable.NAME,
                new String[]{DbSchema.RelationshipTable.Cols.EXERCISEID, DbSchema.RelationshipTable.Cols.REPS},
                DbSchema.RelationshipTable.Cols.WORKOUTID + " = ?",
                new String[] {Integer.toString(workoutId)},
                null,null,null);
        return cursor;
    }
}