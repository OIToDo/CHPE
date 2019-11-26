package com.mygdx.game.PoseEstimation.NN;

import com.mygdx.game.PoseEstimation.NN.PoseModels.*;

public class ModelParser {

    public final static int COCO_MODEL = 1;
    public final static int MPI_MODEL = 2;
    public final static int POSENET_MODEL = 3;

    public static PoseModel parseModel(int modelId) {
        PoseModel model;
        switch (modelId) {
            case 2:
                model = new NNModelMPI();
                break;
            case 3:
                model = new NNModelPosenet();
                break;
            default:
                model = new NNModelCOCO();
        }
        return model;
    }
}
