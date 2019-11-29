package com.mygdx.game.Analysis;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.mygdx.game.DebugLog;

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

    @Before
    public void before() {
        // initialize the json data
        context = ApplicationProvider.getApplicationContext();
        AssetManager am = context.getAssets();
        InputStream is = null;
        try {
            is = am.open("data/wave.json");
        } catch (IOException e) {
            DebugLog.log("Unable to load asset norm json");
        }
        Reader r = new InputStreamReader(is);
        JSONLoader loader = new JSONLoader(r);
        data = new JSONData(loader);

        filter = new Filter(data);
    }

    @Test
    public void absAverageTest() {
        double a = 50;
        double b = 100;
        double result = filter.absAverage(a, b);
        assertEquals(result, 75, 0);
    }

    @Test
    public void testKernel() {

        double kernel[] = {1, 2, 1};
    }

    @After
    public void after() {

    }
}
