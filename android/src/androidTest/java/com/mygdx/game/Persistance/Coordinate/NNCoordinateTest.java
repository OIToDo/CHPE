package com.mygdx.game.Persistance.Coordinate;


import com.mygdx.game.Persistance.AppDatabase;
import com.mygdx.game.Persistance.PersistenceClient;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class NNCoordinateTest {
    @Rule
    public ErrorCollector collector = new ErrorCollector(); // Ensures multiple runs of checks

    private NNCoordinateDAO nnCoordinateDAO;
    private AppDatabase appDatabase;

    @Before
    public void createDb() {
        this.appDatabase = PersistenceClient.getInstance(
                ApplicationProvider.getApplicationContext(),
                "debugDB")
                .getAppDatabase();
        this.appDatabase.clearAllTables();
        this.nnCoordinateDAO = appDatabase.nnCoordinateDAO();
    }

    @After
    public void clearDb() {
        this.appDatabase.clearAllTables();
    }

    @Test
    public void InsertAmountCounter() {
        this.appDatabase.clearAllTables();
        NNCoordinate nnCoordinate = new NNCoordinate();
        nnCoordinate.x = 2;
        nnCoordinate.y = 20;

        NNCoordinate nnCoordinateInserted =
                nnCoordinateDAO.getById(
                    nnCoordinateDAO.insert(nnCoordinate) // The ID is returned upon insertion
                );

        collector.checkThat(1, CoreMatchers.is(nnCoordinateDAO.getCount()));
        collector.checkThat(nnCoordinateInserted.x, CoreMatchers.is(nnCoordinate.x));
        collector.checkThat(nnCoordinateInserted.y, CoreMatchers.is(nnCoordinate.y));
        this.appDatabase.clearAllTables();
    }

    @Test
    public void DeleteAmountCounter() {
        this.appDatabase.clearAllTables();
        NNCoordinate nnCoordinate = new NNCoordinate();
        nnCoordinate.x = 2;
        nnCoordinate.y = 20;

        NNCoordinate nnCoordinateInserted =
                nnCoordinateDAO.getById(
                        nnCoordinateDAO.insert(nnCoordinate) // The ID is returned upon insertion
                );
        nnCoordinateDAO.delete(nnCoordinateInserted);
        collector.checkThat(0, CoreMatchers.is(nnCoordinateDAO.getCount()));
        this.appDatabase.clearAllTables();
    }

    @Test
    public void UpdateCoordinateTest() {
        this.appDatabase.clearAllTables();
        NNCoordinate nnCoordinate = new NNCoordinate();
        nnCoordinate.x = 2;
        nnCoordinate.y = 20;

        NNCoordinate nnCoordinateInserted =
                nnCoordinateDAO.getById(
                        nnCoordinateDAO.insert(nnCoordinate) // The ID is returned upon insertion
                );
        nnCoordinateInserted.x = 20;
        nnCoordinateInserted.y = 2;
        nnCoordinateDAO.update(nnCoordinateInserted);

        collector.checkThat(1, CoreMatchers.is(nnCoordinateDAO.getCount()));
        collector.checkThat(nnCoordinateInserted.x, CoreMatchers.is(nnCoordinate.y));
        collector.checkThat(nnCoordinateInserted.y, CoreMatchers.is(nnCoordinate.x));
        this.appDatabase.clearAllTables();
    }


}

