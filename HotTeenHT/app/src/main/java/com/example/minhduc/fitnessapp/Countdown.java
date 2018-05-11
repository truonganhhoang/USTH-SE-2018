package com.example.minhduc.fitnessapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import database.DbHelper;
import database.DbSchema;

public class Countdown extends AppCompatActivity {

    private CountDownTimer Timer;
    private TextView viewnumber;
    private TextView activityname;
    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private ArrayList<Integer> exerciseReps;
    private ArrayList<String> exerciseNames;
    private int workoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        viewnumber = (TextView) findViewById(R.id.viewnumber);
        activityname = (TextView) findViewById(R.id.activityname);

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

        Timer = new CountDownTimer(6000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                viewnumber.setText((String.valueOf(millisUntilFinished/1000)));
                activityname.setText("Ready ?");
            }

            @Override
            public void onFinish() {
                for (int a = dbHelper.getRounds(workoutId); a>=0; a--){
                    Timer = new CountDownTimer(10000,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            viewnumber.setText(String.valueOf(millisUntilFinished/1000));
                            activityname.setText("Test");
                        }

                        @Override
                        public void onFinish() {

                        }
                    }.start();
                }
            }
        }.start();

    }
    public Cursor getExercisesFromWorkoutCursor(int workoutId) {
        Cursor cursor = db.query(DbSchema.RelationshipTable.NAME,
                new String[]{DbSchema.RelationshipTable.Cols.EXERCISEID, DbSchema.RelationshipTable.Cols.REPS},
                DbSchema.RelationshipTable.Cols.WORKOUTID + " = ?",
                new String[] {Integer.toString(workoutId)},
                null,null,null);
        return cursor;
    }
}
