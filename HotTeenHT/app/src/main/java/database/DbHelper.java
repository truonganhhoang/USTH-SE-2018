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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WorkoutTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ExerciseTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RelationshipTable.NAME);
        onCreate(db);
    }

    public void insertExercise(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ExerciseTable.Cols.NAME,name);
        db.insert(ExerciseTable.NAME, ExerciseTable.Cols.NAME, values);
        db.close();
    }

    public long insertWorkout(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WorkoutTable.Cols.NAME,name);
        long id = db.insert(WorkoutTable.NAME, WorkoutTable.Cols.NAME, values);
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


    public Cursor getAllExercisesCursor() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(ExerciseTable.NAME,
                new String[]{ExerciseTable.Cols.ID, ExerciseTable.Cols.NAME},
                null,null,null,null,null);
        return cursor;
    }

    public Cursor getExercisesFromWorkoutCursor(int workoutId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(RelationshipTable.NAME,
                new String[]{RelationshipTable.Cols.EXERCISEID, RelationshipTable.Cols.REPS},
                RelationshipTable.Cols.WORKOUTID + " = ?",
                new String[] {Integer.toString(workoutId)},
                null,null,null);
        return cursor;
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
}
