package com.mygdx.game.Analysis;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Debug;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.DebugLog;
import com.mygdx.game.PoseEstimation.nn.MPI;

import org.json.JSONArray;
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
public class FilterTest {
    Data data;
    Filter filter;
    Context context;

    /**
     * Read in a JSON file to filter.
     */
    @Before
    public void before() {
        // initialize the json data
        context = ApplicationProvider.getApplicationContext();
        AssetManager am = context.getAssets();
        InputStream is = null;
        try {
            is = am.open("data/tedtalk.json");
        } catch (IOException e) {
            DebugLog.log("Unable to load asset norm json");
        }
        Reader r = new InputStreamReader(is);
        JSONLoader loader = new JSONLoader(r);
        data = new JSONData(loader);

        filter = new Filter(data);
    }

    /**
     * Test the absolute average method of Filter.
     */
    @Test
    public void absAverageTest() {
        double a = 50;
        double b = 100;
        double result = filter.absAverage(a, b);
        assertEquals(result, 75, 0);
    }

    /**
     * Tests if the resolvezeros method actually resolves all zeros.
     */
    @Test
    public void TestZeros() {
        filter.resolveZeros();
        for(int f = 0; f < data.getFrameCount(); f++) {
            for(MPI.body_part bp : MPI.body_part.values()) {
                Vector3 coord = data.getCoord(f, bp);
                assertEquals(coord.isZero(), false);
            }
        }
    }

    /**
     * Test if kernel has the right result.
     * TODO: Fix error, has to do with getCoord not giving the correct frame
     */
    @Test
    public void testKernel() {
        DebugLog.log(data.getCoord(3, MPI.body_part.l_wrist).toString());

        double kernel[] = {1, 1, 1};
        filter.kernelFilter(kernel);

        DebugLog.log(data.getCoord(3, MPI.body_part.l_wrist).toString());
        Vector3 v = data.getCoord(3, MPI.body_part.l_wrist);
        assertEquals(278.0, v.x, 0);
        assertEquals(93.0, v.y, 0);
    }

    /**
     * Cleanup.
     */
    @After
    public void after() {}
}
