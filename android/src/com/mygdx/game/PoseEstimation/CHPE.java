package com.mygdx.game.PoseEstimation;


import android.content.Context;
import android.graphics.Bitmap;

import com.mygdx.game.PoseEstimation.NN.ModelParser;
import com.mygdx.game.PoseEstimation.NN.PoseModel;
import com.mygdx.game.PoseEstimation.NN.PoseNet.Person;
import com.mygdx.game.PoseEstimation.NN.PoseNet.Posenet.Device;
import com.mygdx.game.persistance.AppDatabase;


/**
 * The type Chpe.
 */
public class CHPE {
    private Resolution resolution;
    private Context context;
    private PoseModel poseModel;
    private static final boolean BILINEAR_INTERPOLATION = true;

    /**
     * Instantiates a new CHPE.
     *
     * @param context    The context
     * @param resolution The resolution used for scaling
     */
    CHPE(Context context, Resolution resolution, PoseModel model) {
        this.context = context;
        this.resolution = resolution;
        this.poseModel = model;
    }

    CHPE(Context context, Resolution resolution, final int model) {
        this.context = context;
        this.resolution = resolution;
        parseModel(model);
    }

    private void parseModel(final int model) {
        this.poseModel = ModelParser.parseModel(model);
    }


    /**
     * Process frame person based on the
     *
     * @param image  The supplied bitmap image
     * @param device The device type (i.e. CPU/GPU/NNAPI)
     * @return Instance of a Person found on the image
     */
    Person ProcessFrame(Bitmap image, Device device) {
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(
                image,
                this.resolution.modelWidth,
                this.resolution.modelHeight,
                BILINEAR_INTERPOLATION // Simple interpolation to fill 'empty' spaces on image
        );

        Posenet posenet = new Posenet(this.context,
                this.poseModel.model, // Instance of the model used
                device, // Device on which the execution will take place
                this.resolution); // Instance of resolution used for scaling
        return posenet.estimateSinglePose(scaledBitmap); //
    }

    /**
     * Over loader, uses GPU as default device
     *
     * @param image The supplied bitmap image
     * @return Instance of a Person found on the image
     */
    Person ProcessFrame(Bitmap image) {
        return ProcessFrame(image, Device.GPU);
    }
}