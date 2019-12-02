package com.mygdx.game.Persistance.Video;
import android.content.Context;

import com.mygdx.game.Persistance.AppDatabase;
import com.mygdx.game.Persistance.PersistenceClient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class NNVideoDAOTest {
    private AppDatabase appDatabase;
    private int frameCount = 24 * 5;
    private int framesPerSecond = 24;
    private int width=1920;
    private int height=1080;
    private NNVideoDAO nnVideoDAO;
    private long insertId;

    @Before
    public void setUp() throws Exception {
        Context context = getApplicationContext();

        // Ensure that the database name is NOT the actual database name
        this.appDatabase = PersistenceClient.getInstance(ApplicationProvider.getApplicationContext(), "debugDB").getAppDatabase();
        NNVideo nnSession = new NNVideo();
        nnSession.frame_count = this.frameCount;
        nnSession.frames_per_second = this.framesPerSecond;
        nnSession.height = this.height;
        nnSession.width = this.width;
        this.nnVideoDAO = this.appDatabase.nnVideoDAO();
        this.insertId = nnVideoDAO.insert(nnSession);

    }

    @Test
    public void getFramesPerSecond() {
        assertEquals(this.framesPerSecond,
                this.nnVideoDAO.getLastSession().frames_per_second,
                0.0);
    }

    @Test
    public void getFrameCount() {
        assertEquals(this.frameCount, this.nnVideoDAO.getLastSession().frame_count, 0.0);
    }

    @Test
    public void getWidth(){
        assertEquals(this.width, this.nnVideoDAO.getLastSession().width, 0.0);
    }

    @Test
    public void getHeight(){
        assertEquals(this.height, this.nnVideoDAO.getLastSession().height, 0.0);
    }


    @After
    public void tearDown() throws Exception {
        this.nnVideoDAO.nukeTable();
    }
}