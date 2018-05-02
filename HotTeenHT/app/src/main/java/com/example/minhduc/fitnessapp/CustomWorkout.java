package com.example.minhduc.fitnessapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import database.DbHelper;
import database.DbSchema;

public class CustomWorkout extends AppCompatActivity {
    private DbHelper dbHelper;
    private ListView workoutListView;
    private Button addButton;
    private Cursor cursor;
    private SQLiteDatabase db;
    private SimpleCursorAdapter workoutAdapter;

    public Cursor getAllWorkoutsCursor() {
        return db.query(DbSchema.WorkoutTable.NAME,
                new String[]{DbSchema.WorkoutTable.Cols.ID, DbSchema.WorkoutTable.Cols.NAME},
                null,null,null,null,null);
    }

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

        /*dbHelper.insertExercise("Push ups");
        dbHelper.insertExercise("Pull ups");
        dbHelper.insertExercise("Squats");
        dbHelper.insertExercise("Crunches");*/

        db = dbHelper.getReadableDatabase();
        cursor = getAllWorkoutsCursor();

        workoutAdapter = new SimpleCursorAdapter(this,
                R.layout.single_workout_row,
                cursor,
                new String[]{DbSchema.WorkoutTable.Cols.NAME},
                new int[] {R.id.workoutNameTextView},
                0);

        workoutListView = findViewById(R.id.listViewWorkout);
        workoutListView.setAdapter(workoutAdapter);

        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> parent,
                                            View itemView,
                                            int position,
                                            long id) {
                        Intent intent = new Intent(getApplicationContext(), WorkoutDetail.class);
                        intent.putExtra("WorkoutId", (int) id);
                        startActivity(intent);
                    }
                };
        workoutListView.setOnItemClickListener(itemClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cursor = getAllWorkoutsCursor();
        workoutAdapter.changeCursor(cursor);
        workoutAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
