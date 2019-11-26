package com.mygdx.game.PoseEstimation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mygdx.game.PoseEstimation.NN.PoseModels.*;
import com.mygdx.game.PoseEstimation.NN.NNInterpreter;
import com.mygdx.game.PoseEstimation.NN.PoseNet.Person;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;


public class CHPETest {
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    private Context context = InstrumentationRegistry.getInstrumentation().getContext();
    private Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private String examplePhoto = "example-human-pose.jpg";
    private Bitmap bitmap;

    @Before
    public void setUp() throws Exception {
        // Importing the Example image
        this.bitmap = BitmapFactory.decodeStream(this.context.getAssets().open(examplePhoto));
    }


    @Test
    public void validateSettings() {
        // Using multiple asserts are not good practice
        // because if the first one fail and the remaining asserts will not
        collector.checkThat(this.bitmap.getByteCount(), CoreMatchers.is(3724476));
        collector.checkThat(this.bitmap.getHeight(), CoreMatchers.is(1317));
        collector.checkThat(this.bitmap.getWidth(), CoreMatchers.is(707));
    }


    /**
     * Tear down.
     * Currently serves no purpose, because all manipulated data is accessed and modified locally.
     * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void ParseConstructor() {
        CHPE chpePoseNet = new CHPE(this.context, new Resolution(this.bitmap), 3);
        CHPE chpeMPI = new CHPE(this.context, new Resolution(this.bitmap), 2);
        CHPE chpe = new CHPE(this.context, new Resolution(this.bitmap), 99);


        collector.checkThat(chpeMPI.getPoseModel().getModel(),
                CoreMatchers.is("pose_iter_160000.caffemodel")
        );

        collector.checkThat(chpePoseNet.getPoseModel().getModel(),
                CoreMatchers.is("posenet_model.tflite")
        );

        collector.checkThat(chpe.getPoseModel().getModel(),
                CoreMatchers.is("pose/coco/pose_iter_440000.caffemodel")
        );
    }

    @Test
    public void StaticConstructor() {

        CHPE chpePoseNet = new CHPE(this.context, new Resolution(this.bitmap), new NNModelPosenet());
        CHPE chpeMPI = new CHPE(this.context, new Resolution(this.bitmap), new NNModelMPI());
        CHPE chpe = new CHPE(this.context, new Resolution(this.bitmap), new NNModelCOCO());


        collector.checkThat(chpeMPI.getPoseModel().getModel(),
                CoreMatchers.is("pose_iter_160000.caffemodel")
        );

        collector.checkThat(chpePoseNet.getPoseModel().getModel(),
                CoreMatchers.is("posenet_model.tflite")
        );

        collector.checkThat(chpe.getPoseModel().getModel(),
                CoreMatchers.is("pose/coco/pose_iter_440000.caffemodel")
        );
    }
    /*
    TODO: Enable: OpenGL ES 3.1
    @Test
    public void ProcessFrameGPU() {
        CHPE chpe = new CHPE(this.targetContext, new Resolution(this.bitmap), new NNModelPosenet());
        Person p = chpe.ProcessFrame(this.bitmap, NNInterpreter.GPU);
        assertEquals(new NNModelPosenet().points, p.getKeyPoints().size());
    }
    */
    @Test
    public void ProcessFrameCPU() {
        CHPE chpe = new CHPE(this.context, new Resolution(this.bitmap), new NNModelPosenet());
        Person p = chpe.ProcessFrame(this.bitmap, NNInterpreter.CPU);
        assertEquals(new NNModelPosenet().points, p.getKeyPoints().size());
    }

    @Test
    public void ProcessFrameNNAPI() {
        CHPE chpe = new CHPE(this.context, new Resolution(this.bitmap), new NNModelPosenet());
        Person p = chpe.ProcessFrame(this.bitmap, NNInterpreter.NNAPI);
        assertEquals(new NNModelPosenet().points, p.getKeyPoints().size());
    }
}