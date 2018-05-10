package database;

import android.os.Build;

import com.example.minhduc.fitnessapp.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class DatabaseTest {

    private DbHelper dbHelper;

    @Before
    public void setUp() {
        dbHelper = new DbHelper(RuntimeEnvironment.application);
    }

    @After
    public void tearDown() {
        dbHelper.close();
    }

    @Test
    public void testInsert() {
        String expectedWorkoutName = "Back & Biceps";
        int expectedRounds = 5;
        int expectedRestExercises = 20;
        int expectedRestRounds = 100;
        int workoutId = dbHelper.insertWorkout(expectedWorkoutName, expectedRounds, expectedRestExercises, expectedRestRounds);

        String expectedExerciseName = "push up";
        int exerciseId = dbHelper.insertExercise(expectedExerciseName);

        assertEquals(expectedWorkoutName, dbHelper.getWorkoutName(workoutId));
        assertEquals(expectedRounds, dbHelper.getRounds(workoutId));
        assertEquals(expectedRestExercises, dbHelper.getRestExercises(workoutId));
        assertEquals(expectedRestRounds, dbHelper.getRestRounds(workoutId));
        assertEquals(expectedExerciseName, dbHelper.getExerciseName(exerciseId));
    }
}
