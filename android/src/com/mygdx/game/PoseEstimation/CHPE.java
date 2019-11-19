package com.mygdx.game.PoseEstimation;


import android.content.Context;
import android.graphics.Bitmap;

import com.mygdx.game.PoseEstimation.NN.PoseModel;
import com.mygdx.game.persistance.AppDatabase;


/**
 * The type Chpe.
 */
public class CHPE {
    /**
     * The Resolution.
     */
    public Resolution resolution;
    private AppDatabase db;
    private PoseModel model;
    private String points;
    private Context context;

    /**
     * Instantiates a new Chpe.
     *
     * @param context    the context
     * @param resolution the resolution
     */
    public CHPE(Context context, Resolution resolution) {
        this.context = context;
        this.resolution = resolution;
    }

    /**
     * Process frame person.
     *
     * @param image the image
     * @return the person
     */
    public Person ProcessFrame(Bitmap image) {
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(image, this.resolution.modelWidth,
                this.resolution.modelHeight,
                true);
        // Perform inference.
        Posenet posenet = new Posenet(this.context,
                "posenet_model.tflite",
                // TODO: Benchmark function to check which device type will have the fastest execution type
                Device.GPU,
                this.resolution);
        return posenet.estimateSinglePose(scaledBitmap);
    }

    /**
     * Process frame person.
     *
     * @param image  the image
     * @param device the device
     * @return the person
     */
    public Person ProcessFrame(Bitmap image, Device device) {
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(image, this.resolution.modelWidth,
                this.resolution.modelHeight,
                true);
        // Perform inference.
        Posenet posenet = new Posenet(this.context,
                "posenet_model.tflite",
                device,
                this.resolution);
        return posenet.estimateSinglePose(scaledBitmap);
    }

}