package com.mygdx.game.PoseEstimation.NN.PoseNet;

import java.util.ArrayList;
import java.util.List;

public class Person {
    public Person(){}
    public List<KeyPoint> keyPoints = new ArrayList<>();
    public Float score = 0.0f;
    public List<KeyPoint> getKeyPoints(){
        return keyPoints;
    }

}





