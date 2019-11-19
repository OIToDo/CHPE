package com.mygdx.game.PoseEstimation.NN;

import com.mygdx.game.PoseEstimation.NN.PoseNet.Posenet;

public class ModelParser {


    public final static int COCO_MODEL = 1;
    public final static int MPI_MODEL = 2;
    public final static int POSENET_MODEL = 3;

    public static PoseModel parseModel(int modelId) {
        PoseModel model;
        switch (modelId) {
            case 2:
                model = new MPI();
                break;
            case 3:
                model = new Posenet();
                break;
            default:
                model = new COCO();
        }
        return model;
    }
}
