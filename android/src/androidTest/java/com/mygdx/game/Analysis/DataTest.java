package com.mygdx.game.Analysis;

import android.content.res.AssetManager;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.DebugLog;
import com.mygdx.game.MockData;
import com.mygdx.game.PoseEstimation.nn.MPI;
import com.mygdx.game.persistance.AppDatabase;
import com.mygdx.game.persistance.Coordinate.NNCoordinate;
import com.mygdx.game.persistance.PersistenceClient;
import com.mygdx.game.persistance.Video.NNVideoDAO;
import android.content.Context;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DataTest {
    Data data;
    Data dbData;
    Data jsonData;
    Context context;
    AppDatabase appDatabase;
    JSONLoader loader;
    @Before
    public void before() {
        // initialize the json data
        context = ApplicationProvider.getApplicationContext();
        AssetManager am = context.getAssets();
        InputStream is = null;
        try {
            is = am.open("data/norm.json");
        } catch (IOException e) {
            DebugLog.log("Unable to load asset norm json");
        }
        Reader r = new InputStreamReader(is);
        JSONLoader loader = new JSONLoader(r);
        jsonData = new JSONData(loader);

        // initialize the database
        this.appDatabase = PersistenceClient.getInstance(ApplicationProvider.getApplicationContext(), "debugDB").getAppDatabase();
        this.appDatabase.clearAllTables();
        MockData mockData = new MockData(this.appDatabase, loader.getArray());
        mockData.executeInserts();
        dbData = new DatabaseData(this.appDatabase);
    }

    @Test
    public void JSONTest() {
        data = jsonData;
        runAll();
    }

    @Test
    public void DBTest() {
        data = dbData;
        runAll();
    }

    public void runAll() {
        setTest();
        getCoordTest();
    }

    public void getCoordTest() {
        Vector3 first_coord = data.getCoord(0, MPI.body_part.head);
        assertEquals(first_coord.x, 5.0f,0.0f);
        assertEquals(first_coord.y, 10.0f, 0.0f);
        assertEquals(first_coord.z, 0.0f, 0.0f);
    }


    public void setTest() {
        Vector3 to_set = new Vector3(5,10,0);
        data.setX(0, MPI.body_part.head, to_set.x);
        data.setY(0, MPI.body_part.head, to_set.y);
        Vector3 coord = data.getCoord(0, MPI.body_part.head);
        assertEquals(to_set, coord);
    }

    @After
    public void after() {
        this.appDatabase.close();
    }

}
