package com.mygdx.game.PoseEstimation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;

/*
 * The Current VideoSplicer only works based on intents.
 * Therefore the SessionTest will not work using a junit.
 * The test suite needs to be adapted for this.
 */
public class SessionTest {

    /**
     * The Collector is used to enable multiple asserts
     */
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    private Context context = InstrumentationRegistry.getInstrumentation().getContext();
    private Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private String examplePhoto = "example-human-pose.jpg";
    private Bitmap bitmap;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        // Initialising the database for later use
        // Initialising the session entry

    }

}
