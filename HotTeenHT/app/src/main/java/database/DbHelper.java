package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.minhduc.fitnessapp.WorkoutDetail;

import java.util.ArrayList;

import database.DbSchema.ExerciseTable;
import database.DbSchema.RelationshipTable;
import database.DbSchema.WorkoutTable;

public class DbHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "fitnessDb.db";
    public static final String[] EXERCISES = new String[] {"Push ups", "Chin ups", "Pull ups", "Squats" , "Crunches", "Jumping Jacks", "Sit Ups", "Dips"};

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
                WorkoutTable.Cols.NAME + " TEXT," +
                WorkoutTable.Cols.ROUNDS + " INTEGER," +
                WorkoutTable.Cols.RESTEXERCISES + " INTEGER," +
                WorkoutTable.Cols.RESTROUNDS + " INTEGER" +
                ");"
        );

        db.execSQL("CREATE TABLE " + RelationshipTable.NAME + "(" +
                RelationshipTable.Cols.WORKOUTID + " INTEGER," +
                RelationshipTable.Cols.EXERCISEID + " INTEGER," +
                RelationshipTable.Cols.REPS + " INTEGER," +
                "FOREIGN KEY (" + RelationshipTable.Cols.WORKOUTID + ") REFERENCES " +
                WorkoutTable.NAME + "(" + WorkoutTable.Cols.ID + ") ON DELETE CASCADE," +
                "FOREIGN KEY (" + RelationshipTable.Cols.EXERCISEID + ") REFERENCES " +
                ExerciseTable.NAME + "(" + ExerciseTable.Cols.ID + ")" +
                ");"
        );

        for (int i = 0; i < EXERCISES.length; i++) {
            ContentValues values = new ContentValues();
            values.put(ExerciseTable.Cols.NAME, EXERCISES[i]);
            db.insert(ExerciseTable.NAME, ExerciseTable.Cols.NAME, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WorkoutTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ExerciseTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RelationshipTable.NAME);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON");
    }

    public int getWorkoutCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, WorkoutTable.NAME);
        return numRows;
    }

    public int insertExercise(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ExerciseTable.Cols.NAME, name);
        int id = (int) db.insert(ExerciseTable.NAME, ExerciseTable.Cols.NAME, values);
        db.close();
        return id;
    }

    public int insertWorkout(String name, int rounds, int restExercises, int restRounds) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WorkoutTable.Cols.NAME, name);
        values.put(WorkoutTable.Cols.ROUNDS, rounds);
        values.put(WorkoutTable.Cols.RESTEXERCISES, restExercises);
        values.put(WorkoutTable.Cols.RESTROUNDS, restRounds);
        int id = (int) db.insert(WorkoutTable.NAME, WorkoutTable.Cols.NAME, values);
        db.close();
        return id;
    }

    public void insertRelationship(int workoutId, int exerciseId, int reps) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RelationshipTable.Cols.WORKOUTID, workoutId);
        values.put(RelationshipTable.Cols.EXERCISEID, exerciseId);
        values.put(RelationshipTable.Cols.REPS, reps);
        db.insert(RelationshipTable.NAME, null,values);
        db.close();
    }

    public String getWorkoutName(int workoutId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(WorkoutTable.NAME,
                new String[] {WorkoutTable.Cols.NAME},
                WorkoutTable.Cols.ID + " = ?",
                new String[] {Integer.toString(workoutId)},
                null,null,null);
        cursor.moveToFirst();
        String name = cursor.getString(cursor.getColumnIndex(WorkoutTable.Cols.NAME));
        cursor.close();
        db.close();
        return name;
    }

    public String getExerciseName(int exerciseId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(ExerciseTable.NAME,
                new String[] {ExerciseTable.Cols.NAME},
                ExerciseTable.Cols.ID + " = ?",
                new String[] {Integer.toString(exerciseId)},
                null,null,null);
        cursor.moveToFirst();
        String name = cursor.getString(cursor.getColumnIndex(ExerciseTable.Cols.NAME));
        cursor.close();
        db.close();
        return name;
    }

    public int getRounds(int workoutId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(WorkoutTable.NAME,
                new String[] {WorkoutTable.Cols.ROUNDS},
                WorkoutTable.Cols.ID + " = ?",
                new String[] {Integer.toString(workoutId)},
                null,null,null);
        cursor.moveToFirst();
        int rounds = cursor.getInt(cursor.getColumnIndex(WorkoutTable.Cols.ROUNDS));
        cursor.close();
        db.close();
        return rounds;
    }

    public int getRestExercises(int workoutId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(WorkoutTable.NAME,
                new String[] {WorkoutTable.Cols.RESTEXERCISES},
                WorkoutTable.Cols.ID + " = ?",
                new String[] {Integer.toString(workoutId)},
                null,null,null);
        cursor.moveToFirst();
        int restExercises = cursor.getInt(cursor.getColumnIndex(WorkoutTable.Cols.RESTEXERCISES));
        cursor.close();
        db.close();
        return restExercises;
    }

    public int getRestRounds(int workoutId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(WorkoutTable.NAME,
                new String[] {WorkoutTable.Cols.RESTROUNDS},
                WorkoutTable.Cols.ID + " = ?",
                new String[] {Integer.toString(workoutId)},
                null,null,null);
        cursor.moveToFirst();
        int restRounds = cursor.getInt(cursor.getColumnIndex(WorkoutTable.Cols.RESTROUNDS));
        cursor.close();
        db.close();
        return restRounds;
    }

    public void renameWorkout(int workoutId, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WorkoutTable.Cols.NAME, name);
        db.update(WorkoutTable.NAME,
                values,
                WorkoutTable.Cols.ID + " = ?",
                new String[] {Integer.toString(workoutId)});
    }

    public void deleteWorkout(int workoutId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(WorkoutTable.NAME,
                WorkoutTable.Cols.ID + "=" + Integer.toString(workoutId),
                null);
    }
}
