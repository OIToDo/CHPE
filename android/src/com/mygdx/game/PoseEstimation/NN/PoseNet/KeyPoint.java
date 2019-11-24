package com.mygdx.game.PoseEstimation.NN.PoseNet;

import com.mygdx.game.PoseEstimation.NN.Posenet;

public class KeyPoint {
    public Posenet.body_part bodyPart;
    public Position position;
    public Float score = 0.0f;
}
