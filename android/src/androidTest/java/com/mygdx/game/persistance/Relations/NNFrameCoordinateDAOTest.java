package com.mygdx.game.persistance.Relations;


import com.mygdx.game.persistance.AppDatabase;
import com.mygdx.game.persistance.PersistenceClient;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class NNFrameCoordinateDAOTest {

    private AppDatabase appDatabase;

    @Before
    public void createDb() {
        this.appDatabase = PersistenceClient.getInstance(ApplicationProvider.getApplicationContext(), "debugDB").getAppDatabase();
        this.appDatabase.clearAllTables();
    }

    @After
    public void closeDb() {
        this.appDatabase.close();
    }

}
