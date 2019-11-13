package com.mygdx.game.persistance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class BasePersistenceDABTest {
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

    @Test
    public void exampleTest() {
        assertTrue(true);
    }

}
