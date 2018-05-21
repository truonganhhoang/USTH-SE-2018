package com.example.minhduc.fitnessapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import database.DbHelper;

public class CreateNewWorkout extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private MenuItem prevMenuItem;
    private Button buttonFinish;
    private ViewPager pager;
    private SectionsPagerAdapter pagerAdapter;
    private ExerciseFragment exerciseFragment;
    private SetupFragment setupFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_exercise:
                    pager.setCurrentItem(0);
                    return true;
                case R.id.navigation_setup:
                    pager.setCurrentItem(1);
                    return true;
            }
            return false;
        }
    };

    private View.OnClickListener finishButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            insertData();
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_workout);

        buttonFinish = findViewById(R.id.buttonFinish);
        buttonFinish.setOnClickListener(finishButtonListener);

        bottomNavigationView = findViewById(R.id.navigationCreateWorkout);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Attach the SectionsPagerAdapter to the ViewPager
        pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        pager = findViewById(R.id.viewPagerCreateWorkout);
        pager.setAdapter(pagerAdapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        exerciseFragment = new ExerciseFragment();
        setupFragment = new SetupFragment();
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public int getCount() {
            return 2;
        }
        @Override
        public Fragment getItem(int position) {
            //The fragment to be displayed on each page
            switch (position) {
                case 0:
                    return exerciseFragment;
                case 1:
                    return setupFragment;
            }
            return null;
        }
    }

    public void insertData() {
        ArrayList<Integer> exerciseIds = exerciseFragment.getExerciseIds();
        ArrayList<Integer> exerciseReps = exerciseFragment.getExerciseReps();

        DbHelper dbHelper = new DbHelper(this);
        String workoutName = getIntent().getStringExtra("WorkoutName");
        int rounds = setupFragment.getRounds();
        int restExercises = setupFragment.getRestExercises();
        int restRounds = setupFragment.getRestRounds();

        int workoutId = dbHelper.insertWorkout(workoutName, rounds, restExercises, restRounds);
        for (int i = 0; i < exerciseIds.size(); i++) {
            dbHelper.insertRelationship(workoutId, exerciseIds.get(i), exerciseReps.get(i));
        }
    }

    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
        super.onBackPressed();
    }
}
