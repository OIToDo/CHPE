package com.mygdx.game.persistance.Session;

import android.content.Context;

import com.mygdx.game.MockData;
import com.mygdx.game.persistance.AppDatabase;
import com.mygdx.game.persistance.Coordinate.NNCoordinate;
import com.mygdx.game.persistance.Coordinate.NNCoordinateDAO;
import com.mygdx.game.persistance.Relations.NNSessionFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.List;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class NNSessionDAOTest {
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

        NNSession nnSession = new NNSession();
        nnSession.frame_count = this.frameCount;
        nnSession.frames_per_second = this.framesPerSecond;
        this.nnSessionDAO = this.appDatabase.nnSessionDAO();
        this.insertId = nnSessionDAO.insert(nnSession);

        // Initialising with mock data
    }

    @After
    public void tearDown() throws Exception {
        nnSessionDAO.nukeTable();
    }

    @Test
    public void getFramesPerSecond() {
        assertEquals(this.framesPerSecond,
                this.nnSessionDAO.getLastSession().frames_per_second,
                0.0);
    }

    @Test
    public void getFrameCount() {
        assertEquals(this.frameCount, this.nnSessionDAO.getLastSession().frame_count, 0.0);
    }

}