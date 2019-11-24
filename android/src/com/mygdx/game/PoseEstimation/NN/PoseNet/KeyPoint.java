package com.mygdx.game.PoseEstimation.NN.PoseNet;

import com.mygdx.game.PoseEstimation.NN.Posenet;

public class KeyPoint {
    public Posenet.body_part bodyPart;
    public Position position = new Position();
    public Float score = 0.0f;

    public Posenet.body_part getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(Posenet.body_part bodyPart) {
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
