package com.mygdx.game.PoseEstimation.NN;

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
