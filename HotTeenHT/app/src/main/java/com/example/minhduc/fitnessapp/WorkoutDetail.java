package com.example.minhduc.fitnessapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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
    private Button buttonStartWorkout;
    private int workoutId;
    private int rounds;
    private int restExercises;
    private int restRounds;
    private int currentRound;
    private int currentExercise;
    private boolean timeToRest;

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

        buttonStartWorkout = findViewById(R.id.buttonStartWorkout);
        buttonStartWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentRound = 1;
                currentExercise = 0;
                startWorkActivity();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                if (timeToRest) {
                    startRestActivity();
                } else {
                    startWorkActivity();
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                finish();
            }
        }
    }

    private void startWorkActivity() {
        String exerciseName = exerciseNames.get(currentExercise);
        int exerciseRep = exerciseReps.get(currentExercise);
        Intent workIntent = new Intent(getApplicationContext(), Work.class);
        workIntent.putExtra("exerciseName", exerciseName);
        workIntent.putExtra("exerciseRep", exerciseRep);
        timeToRest = true;
        startActivityForResult(workIntent, 1);
    }

    private void startRestActivity() {
        int restTime, nextExerciseRep;
        String nextExerciseName;
        String roundInfo;
        if (currentExercise != exerciseNames.size() - 1) {
            nextExerciseName = exerciseNames.get(currentExercise + 1);
            nextExerciseRep = exerciseReps.get(currentExercise + 1);
            restTime = restExercises;
            roundInfo = "Round " + currentRound + "/" + rounds;
            currentExercise++;
        } else {
            nextExerciseName = exerciseNames.get(0);
            nextExerciseRep = exerciseReps.get(0);
            restTime = restRounds;
            roundInfo = "Round " + currentRound + " done !";
            currentExercise = 0;
            currentRound++;

        }
        if (currentRound > rounds) {
            Intent i = new Intent(getApplicationContext(), FinishWorkout.class);
            i.putExtra("workoutName", dbHelper.getWorkoutName(workoutId));
            startActivity(i);
            finish();
        } else {
            timeToRest = false;
            Intent restIntent = new Intent(getApplicationContext(), Rest.class);
            restIntent.putExtra("roundInfo", roundInfo);
            restIntent.putExtra("restTime", restTime);
            restIntent.putExtra("nextExerciseName", nextExerciseName);
            restIntent.putExtra("nextExerciseRep", nextExerciseRep);
            startActivityForResult(restIntent, 1);
        }
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