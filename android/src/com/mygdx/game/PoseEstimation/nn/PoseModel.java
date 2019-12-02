package com.mygdx.game.PoseEstimation.nn;

public interface PoseModel {
    int[][] POSE_PAIRS =
            {
                    {0, 1}, {1, 2}, {2, 3},
                    {3, 4}, {1, 5}, {5, 6},
                    {6, 7}, {1, 14}, {14, 8}, {8, 9},
                    {9, 10}, {14, 11}, {11, 12}, {12, 13}
            };

    int points = Integer.MAX_VALUE;
    String protocol_buffer = "";
    String model = "";

}
