package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import database.DbSchema.ExerciseTable;
import database.DbSchema.RelationshipTable;
import database.DbSchema.WorkoutTable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "fitnessDb.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + ExerciseTable.NAME + "(" +
                    ExerciseTable.Cols.ID + " INTEGER PRIMARY KEY," +
                    ExerciseTable.Cols.NAME + " TEXT" +
                    ");"
        );

        db.execSQL("CREATE TABLE " + WorkoutTable.NAME + "(" +
                    WorkoutTable.Cols.ID + " INTEGER PRIMARY KEY," +
                    WorkoutTable.Cols.NAME + " TEXT" +
                    ");"
        );

        db.execSQL("CREATE TABLE " + RelationshipTable.NAME + "(" +
                RelationshipTable.Cols.WORKOUTID + " INTEGER," +
                RelationshipTable.Cols.EXERCISEID + " INTEGER," +
                RelationshipTable.Cols.REPS + " INTEGER," +
                "FOREIGN KEY (" + RelationshipTable.Cols.WORKOUTID + ") REFERENCES " +
                WorkoutTable.NAME + "(" + WorkoutTable.Cols.ID + ")," +
                "FOREIGN KEY (" + RelationshipTable.Cols.EXERCISEID + ") REFERENCES " +
                ExerciseTable.NAME + "(" + ExerciseTable.Cols.ID + ")" +
                ");"
        );
        addWorkout(db,"HIIT");
        addWorkout(db,"Full body hype");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WorkoutTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ExerciseTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RelationshipTable.NAME);
        onCreate(db);
    }

    public void addExercise(String name) {
        ContentValues values = new ContentValues();
        values.put(ExerciseTable.Cols.NAME,name);
        this.getWritableDatabase().insert(ExerciseTable.NAME, ExerciseTable.Cols.NAME, values);
    }

    public void addWorkout(SQLiteDatabase db, String name) {
        ContentValues values = new ContentValues();
        values.put(WorkoutTable.Cols.NAME,name);
        db.insert(WorkoutTable.NAME, WorkoutTable.Cols.NAME, values);
    }

    public ArrayList<String> getAllWorkouts() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + WorkoutTable.NAME, null);
        ArrayList<String> workoutNames = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                workoutNames.add(cursor.getString(cursor.getColumnIndex(WorkoutTable.Cols.NAME)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return workoutNames;
    }
}
