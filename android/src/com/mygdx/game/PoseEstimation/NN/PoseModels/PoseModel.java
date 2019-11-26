package com.mygdx.game.PoseEstimation.NN.PoseModels;

/**
 * The interface Pose model.
 */
public interface PoseModel {
    String getModel();

    String getProtocolBuffer();

    int[][] getPosePairs();

    int getPoints();

    String[] getBodyParts();


}
