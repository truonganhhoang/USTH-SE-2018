package com.example.minhduc.fitnessapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import database.DbHelper;
import database.DbSchema;

public class ChooseExercise extends AppCompatActivity {
    private ListView exerciseList;
    private DbHelper dbHelper;
    private SimpleCursorAdapter exerciseAdapter;
    private SQLiteDatabase db;
    private Cursor cursor;

    public Cursor getAllExercisesCursor() {
        return db.query(DbSchema.ExerciseTable.NAME,
                new String[]{DbSchema.ExerciseTable.Cols.ID, DbSchema.ExerciseTable.Cols.NAME},
                null,null,null,null,null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exercise);

        dbHelper = new DbHelper(this);
        db = dbHelper.getReadableDatabase();
        cursor = getAllExercisesCursor();

        exerciseAdapter = new SimpleCursorAdapter(this,
                R.layout.single_workout_row,
                cursor,
                new String[] {DbSchema.ExerciseTable.Cols.NAME},
                new int[] {R.id.workoutNameTextView},
                0);

        exerciseList = findViewById(R.id.listViewExercise);
        exerciseList.setAdapter(exerciseAdapter);

        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> parent,
                                            View itemView,
                                            int position,
                                            long id) {
                        Cursor cursor = (Cursor)parent.getItemAtPosition(position);
                        Intent intent = new Intent();
                        intent.putExtra("ID", (int) id);
                        intent.putExtra("ExerciseName", cursor.getString(cursor.getColumnIndex(DbSchema.ExerciseTable.Cols.NAME)));
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                };
        exerciseList.setOnItemClickListener(itemClickListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
