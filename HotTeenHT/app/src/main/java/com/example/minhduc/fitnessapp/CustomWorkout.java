package com.example.minhduc.fitnessapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

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

    private Cursor getAllWorkoutsCursor() {
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
        workoutListView.setLongClickable(true);

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

        registerForContextMenu(workoutListView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.listViewWorkout) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.workout_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int workoutId = (int) info.id;
        switch(item.getItemId()) {
            case R.id.menu_rename:
                createRenameDialog(workoutId);
                return true;
            case R.id.menu_delete:
                createDeleteConfirmDialog(workoutId);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void createDeleteConfirmDialog(final int workoutId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to delete it?")
                .setTitle("Delete workout");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dbHelper.deleteWorkout(workoutId);
                refreshListView();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void createRenameDialog(final int workoutId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter a new name:");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing, you will be overriding this anyway
            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(Dialog.BUTTON_POSITIVE).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newName = input.getText().toString();
                    if (newName.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
                    } else {
                        dbHelper.renameWorkout(workoutId, newName);
                        refreshListView();
                        dialog.dismiss();
                    }
                }
            });
    }

    private void refreshListView() {
        cursor = getAllWorkoutsCursor();
        workoutAdapter.changeCursor(cursor);
        workoutAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
