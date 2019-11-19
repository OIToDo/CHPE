package com.mygdx.game.PoseEstimation.NN;

/**
 * The interface Pose model.
 */
public interface PoseModel {
    /**
     * The Pose pairs.
     */
    int[][] POSE_PAIRS =
            {
                    {0, 1}, {1, 2}, {2, 3},
                    {3, 4}, {1, 5}, {5, 6},
                    {6, 7}, {1, 14}, {14, 8}, {8, 9},
                    {9, 10}, {14, 11}, {11, 12}, {12, 13}
            };

    /**
     * The constant points.
     */
    int points = Integer.MAX_VALUE;
    /**
     * The constant protocol_buffer.
     */
    String protocol_buffer = "";
    /**
     * The constant model.
     */
    String model = "";

}
