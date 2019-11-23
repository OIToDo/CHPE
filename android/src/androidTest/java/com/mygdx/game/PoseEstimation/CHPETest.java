package com.mygdx.game.PoseEstimation;

import android.content.Context;

import com.mygdx.game.MockData;
import com.mygdx.game.Persistance.AppDatabase;
import com.mygdx.game.Persistance.Coordinate.NNCoordinate;
import com.mygdx.game.Persistance.Video.NNVideoDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class CHPETest {

    private AppDatabase appDatabase;
    private String databaseName = "CHPETest";

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        // Ensure that the database name is NOT the actual database name
        this.appDatabase = Room.databaseBuilder(context, AppDatabase.class, databaseName)
                .allowMainThreadQueries() // TODO: Multi-threaded agent
                .build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void ParseConstructor(){
        assertEquals(0,0);
    }

    @Test
    public void StaticConstructor(){
        assertEquals(0,0);
    }

    @Test
    public void ProcessFrameGPU(){
        assertEquals(0,0);
    }

    @Test
    public void ProcessFrameCPU(){
        assertEquals(0,0);
    }

    @Test
    public void ProcessFrameNNAPI() {
        assertEquals(0, 0);
    }
}