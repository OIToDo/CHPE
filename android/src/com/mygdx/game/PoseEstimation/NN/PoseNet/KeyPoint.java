package com.mygdx.game.PoseEstimation.NN.PoseNet;

import com.mygdx.game.PoseEstimation.NN.PoseModels.NNModelPosenet;

public class KeyPoint {
    public NNModelPosenet.bodyPart bodyPart;
    public Position position = new Position();
    public Float score = 0.0f;

    public NNModelPosenet.bodyPart getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(NNModelPosenet.bodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
