package com.mygdx.game.persistance.Coordinate;

import android.content.Context;

import com.mygdx.game.persistance.AppDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class InsertCoordinateTest {
    private NNCoordinateDAO nnCoordinateDAO;
    private AppDatabase appDatabase;
    private String databaseName = "test";

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();

        // Ensure that the database name is NOT the actual database name
        this.appDatabase = Room.databaseBuilder(context, AppDatabase.class, databaseName)
                .allowMainThreadQueries() // TODO: Multi-threaded agent
                .build();
        this.nnCoordinateDAO = this.appDatabase.nnCoordinateDAO();
    }

    @After
    public void closeDb(){
        this.appDatabase.close();
    }



    @Test
    public void InsertAmountCounter() {
        NNCoordinate nnCoordinate = new NNCoordinate();
        nnCoordinate.id = 0;
        nnCoordinate.x = 0;
        nnCoordinate.y = 0;
        nnCoordinateDAO.insert(nnCoordinate);

        assertEquals (1, nnCoordinateDAO.getCount());
    }
}

