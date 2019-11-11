package com.mygdx.game.Analysis;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.mygdx.game.MockData;
import com.mygdx.game.persistance.AppDatabase;
import com.mygdx.game.persistance.Coordinate.NNCoordinate;
import com.mygdx.game.persistance.Session.NNSessionDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;



@RunWith(AndroidJUnit4.class)
public class AnalysisTest {

    private AppDatabase appDatabase;
    private String databaseName = "test";
    private int frameCount = 24 * 5;
    private int framesPerSecond = 24;
    private NNSessionDAO nnSessionDAO;
    private long insertId;

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();

        // Ensure that the database name is NOT the actual database name
        this.appDatabase = Room.databaseBuilder(context, AppDatabase.class, databaseName)
                .allowMainThreadQueries() // TODO: Multi-threaded agent
                .build();

        MockData mockData = new MockData(this.appDatabase);
        this.nnSessionDAO = this.appDatabase.nnSessionDAO();


        // Initialising with mock data
    }

    @After
    public void tearDown() throws Exception {
        this.nnSessionDAO.nukeTable();
    }

    @Test
    public void get_coordinates() {

        NNCoordinate coordinate = this.nnSessionDAO.get_coordinates(2,0,0);
        System.out.println(coordinate.x);
        System.out.println(coordinate.y);
        assertEquals(361, coordinate.x,0.0);
        assertEquals(62, coordinate.y, 0.0);
    }
}