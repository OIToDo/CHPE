package com.mygdx.game.PoseEstimation.NN;

public class COCO implements PoseModel {
    public int[][] POSE_PAIRS =
            {
                    {1, 0},
                    {1, 2},
                    {1, 5},
                    {2, 3},
                    {3, 4},
                    {5, 6},
                    {6, 7},
                    {1, 8},
                    {8, 9},
                    {9, 10},
                    {1, 11},
                    {11, 12},
                    {12, 13},
                    {0, 14},
                    {0, 15},
                    {14, 16},
                    {15, 17}
            };

    public int points = 18;
    public final String protocol_buffer = "pose/coco/pose_deploy_linevec.prototxt";
    public final String model = "pose/coco/pose_iter_440000.caffemodel";
}
