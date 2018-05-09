package com.example.minhduc.fitnessapp;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;


import org.junit.Test;

import database.DbHelper;

public class DatabaseTest extends AndroidTestCase{
    private DbHelper dbHelper;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        dbHelper = new DbHelper(context);
        assertNotNull(dbHelper);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        dbHelper.close();
    }


    public void testInsertExercise() {
        String expected = "abc";
        int id = dbHelper.insertExercise(expected);
        String result = dbHelper.getExerciseName(id);
        assertEquals(expected, result);
    }
}