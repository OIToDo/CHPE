package com.mygdx.game.PoseEstimation;

import android.content.Context;
import android.graphics.Bitmap;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import androidx.test.platform.app.InstrumentationRegistry;


public class VideoSplicerTest {
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    private Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private String exampleVideo = "example-human-pose.jpg";
    private Bitmap bitmap;
    //private CHPE chpe;

    @Before
    public void setUp() throws Exception {
        // Importing the Example image
    }


    @Test
    public void validateSettings() {
    }


    /**
     * Tear down.
     * Currently serves no purpose, because all manipulated data is accessed and modified locally.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void ParseConstructor() {


    }

}