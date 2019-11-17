package com.mygdx.game.PoseEstimation;


import android.content.Context;
import android.graphics.Bitmap;
import com.mygdx.game.PoseEstimation.Person;

import com.mygdx.game.PoseEstimation.Device;

import com.mygdx.game.PoseEstimation.NN.PoseModel;
import com.mygdx.game.persistance.AppDatabase;


public class CHPE {
    private AppDatabase db;
    private PoseModel model;
    private String points;
    public Resolution resolution;
    private Context context;

    public CHPE(Context context, Resolution resolution) {
        this.context = context;
        this.resolution = resolution;
    }

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