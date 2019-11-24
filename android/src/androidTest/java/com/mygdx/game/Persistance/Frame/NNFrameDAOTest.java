package com.mygdx.game.Persistance.Frame;

import com.mygdx.game.Persistance.AppDatabase;
import com.mygdx.game.Persistance.PersistenceClient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import java.util.Random;

import static org.junit.Assert.assertEquals;


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
        this.appDatabase.close();
    }

    @Test
    public void InsertAmountCounter() {
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
    public void InsertAmountCounterCustomConstructor() {
        // Random integer to validate insert statements
        int randomInt = new Random().nextInt();

        // Creating a new frame instance
        NNFrame nnFrame = new NNFrame(randomInt);
        NNFrame insertedFrame = nnFrameDAO.getById(nnFrameDAO.insert(nnFrame));

        // Validating if the random frame_count value equals the actual frame count value
        assertEquals(insertedFrame.frame_count, randomInt);
    }
}


