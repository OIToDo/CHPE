package com.mygdx.game.Persistance.Frame;

import com.mygdx.game.Persistance.AppDatabase;
import com.mygdx.game.Persistance.Coordinate.NNCoordinate;
import com.mygdx.game.Persistance.Frame.*;
import com.mygdx.game.Persistance.PersistenceClient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;


@RunWith(AndroidJUnit4.class)
public class NNFrameDAOTest {
    private NNFrameDAO nnFrameDAO;
    private AppDatabase appDatabase;

    @Before
    public void createDb() {
        this.appDatabase = PersistenceClient.getInstance(ApplicationProvider.getApplicationContext(), "debugDB").getAppDatabase();
        this.appDatabase.clearAllTables();
        this.nnFrameDAO = appDatabase.nnFrameDAO();
    }

    @After
    public void closeDb() {
        this.appDatabase.clearAllTables();
    }

    @Test
    public void InsertAmountCounter() {

        this.appDatabase.clearAllTables();
        // Random integer to validate insert statements
        int randomInt = new Random().nextInt();

        // Creating a new frame instance
        NNFrame nnFrame = new NNFrame();
        nnFrame.frame_count = randomInt;
        NNFrame insertedFrame = nnFrameDAO.getById(nnFrameDAO.insert(nnFrame));
        // Validating if the random frame_count value equals the actual frame count value
        assertEquals(insertedFrame.frame_count, randomInt);
    }

    @Test
    public void UpdateAmountCounter() {

        this.appDatabase.clearAllTables();
        // Random integer to validate insert statements
        int randomInt = new Random().nextInt();
        int secondRandomInt = new Random().nextInt();
        // Creating a new frame instance
        NNFrame nnFrame = new NNFrame();
        nnFrame.frame_count = randomInt;


        NNFrame insertedFrame = nnFrameDAO.getById(nnFrameDAO.insert(nnFrame));
        insertedFrame.frame_count = secondRandomInt;
        nnFrameDAO.update(insertedFrame);

        NNFrame updatedFrame = nnFrameDAO.getById(insertedFrame.id);
        // Validating if the random frame_count value equals the actual frame count value
        assertEquals(updatedFrame.frame_count, secondRandomInt);
    }

}


