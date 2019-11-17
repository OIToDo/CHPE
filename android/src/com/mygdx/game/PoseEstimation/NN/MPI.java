package com.mygdx.game.PoseEstimation.NN;

public class MPI implements PoseModel {
    public static enum body_part {
        head, neck, l_shoulder, l_elbow,
        l_wrist, r_shoulder, r_elbow,
        r_wrist, l_hip, l_knee, l_foot,
        r_hip, r_knee, r_foot, waist
    }

    public String[] body_parts = new String[]{
            "head", "neck", "l_shoulder", "l_elbow",
            "l_wrist", "r_shoulder", "r_elbow",
            "r_wrist", "l_hip", "l_knee", "l_foot",
            "r_hip", "r_knee", "r_foot", "waist"
    };


    public final int POSE_PAIRS[][] = {
            {0, 1},
            {1, 2},
            {2, 3},
            {3, 4},
            {1, 5},
            {5, 6},
            {6, 7},
            {8, 9},
            {9, 10},
            {11, 12},
            {12, 13},
            {1, 14},
            {14, 8},
            {14, 11},
    };
    public final int points = 15;
    public final String protocol_buffer = "pose_deploy_linevec_faster_4_stages.prototxt";
    public final String model = "pose_iter_160000.caffemodel";
}
