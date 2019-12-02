package com.mygdx.game.Persistance.Coordinate;

import android.content.Context;

import com.mygdx.game.BuildConfig;
import com.mygdx.game.Persistance.AppDatabase;
import com.mygdx.game.Persistance.PersistenceClient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class NNCoordinateTest {
    private NNCoordinateDAO nnCoordinateDAO;
    private AppDatabase appDatabase;

    @Before
    public void createDb() {
        this.appDatabase = PersistenceClient.getInstance(ApplicationProvider.getApplicationContext(), "debugDB").getAppDatabase();
        this.appDatabase.clearAllTables();
        this.nnCoordinateDAO = appDatabase.nnCoordinateDAO();
    }

    @After
    public void closeDb() {
        this.appDatabase.close();
    }

    @Test
    public void InsertAmountCounter() {
        NNCoordinate nnCoordinate = new NNCoordinate();
        nnCoordinate.x = 2;
        nnCoordinate.y = 20;

        NNCoordinate nnCoordinateInserted = nnCoordinateDAO.getById(
                nnCoordinateDAO.insert(nnCoordinate));

        assertEquals(1, nnCoordinateDAO.getCount());
        assertEquals(nnCoordinateInserted.x, nnCoordinate.x, 0.0);
        assertEquals(nnCoordinateInserted.y, nnCoordinate.y, 0.0);
    }
}

