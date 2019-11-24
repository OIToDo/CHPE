package com.mygdx.game.Persistance.Coordinate;

import com.mygdx.game.Persistance.AppDatabase;
import com.mygdx.game.Persistance.PersistenceClient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class NNCoordinateTest {
    public final int INSERT_QUERY_AMOUNT = 2; // IMPORTANT: KEEP IN MIND THAT THE SEQUENCE OF TESTS CAN SKEWED DUE TO TRANSACTIONS.
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
        nnCoordinate.raw_x = 2;
        nnCoordinate.raw_y = 20;

        NNCoordinate nnCoordinateInserted = nnCoordinateDAO.getById(
                nnCoordinateDAO.insert(nnCoordinate));

        assertEquals(1, nnCoordinateDAO.getCount(), INSERT_QUERY_AMOUNT);
        assertEquals(nnCoordinateInserted.raw_x, nnCoordinate.raw_x, 0.0);
        assertEquals(nnCoordinateInserted.raw_y, nnCoordinate.raw_y, 0.0);
    }

    @Test
    public void InsertAmountCounterCustomConstructor() {
        NNCoordinate nnCoordinate = new NNCoordinate(2, 20);

        NNCoordinate nnCoordinateInserted = nnCoordinateDAO.getById(
                nnCoordinateDAO.insert(nnCoordinate));

        assertEquals(1, nnCoordinateDAO.getCount(), INSERT_QUERY_AMOUNT);
        assertEquals(nnCoordinateInserted.raw_x, nnCoordinate.raw_x, 0.0);
        assertEquals(nnCoordinateInserted.raw_y, nnCoordinate.raw_y, 0.0);
    }
}

