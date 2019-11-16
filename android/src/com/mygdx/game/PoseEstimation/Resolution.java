package com.mygdx.game.PoseEstimation;

public class Resolution {
    public int modelWidth;
    public int modelHeight;
    private int screenWidth;
    private int screenHeight;


    public Resolution(int width, int height, int modelWidth, int modelHeight) {
        this.screenWidth = width;
        this.screenHeight = height;
        this.modelWidth = modelWidth;
        this.modelHeight = modelHeight;
    }

    public Resolution(int width, int height, int modelRes) {
        this.screenWidth = width;
        this.screenHeight = height;
        this.modelWidth = modelRes;
        this.modelHeight = modelRes;
    }

    public int getWidthByRatio(float width) {
        return (int) (width * ((float)this.screenWidth / this.modelWidth));
    }

    public int getHeightByRatio(float height) {
        return (int) (height * ((float) this.screenHeight / this.modelHeight));
    }
}
