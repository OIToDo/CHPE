package com.mygdx.game.PoseEstimation;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.io.InputStream;

import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;


public class VideoSplicerTest {
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    private Context context = InstrumentationRegistry.getInstrumentation().getContext();
    private String exampleVideo = "example-video.mp4";


    @Before
    public void setUp() throws Exception {
        // Importing the Example video
        InputStream inputStream = this.context.getAssets().open(exampleVideo);


        AssetManager assetManager = this.context.getAssets();
        System.out.println("BEGIN");

        while(inputStream.read() != -1){
            System.out.println(inputStream.read());
        }
        for (String s :
                assetManager.list("")) {
            System.out.println(s);
        }

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