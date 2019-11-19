package com.mygdx.game.PoseEstimation;

import android.graphics.Bitmap;

/**
 * The type Resolution.
 */
public class Resolution {
    /**
     * The Model width.
     */
    public int modelWidth = 257;
    /**
     * The Model height.
     */
    public int modelHeight = 257;
    /**
     * The Screen width.
     */
    public int screenWidth;
    /**
     * The Screen height.
     */
    public int screenHeight;


    /**
     * Instantiates a new Resolution.
     *
     * @param bitmap the bitmap
     */
    public Resolution(Bitmap bitmap) {
        this.screenWidth = bitmap.getWidth();
        this.screenHeight = bitmap.getHeight();
    }

    /**
     * Instantiates a new Resolution.
     *
     * @param width       the width
     * @param height      the height
     * @param modelWidth  the model width
     * @param modelHeight the model height
     */
    public Resolution(int width, int height, int modelWidth, int modelHeight) {
        this.screenWidth = width;
        this.screenHeight = height;
        this.modelWidth = modelWidth;
        this.modelHeight = modelHeight;
    }

    /**
     * Instantiates a new Resolution.
     *
     * @param width    the width
     * @param height   the height
     * @param modelRes the model res
     */
    public Resolution(int width, int height, int modelRes) {
        this.screenWidth = width;
        this.screenHeight = height;
        this.modelWidth = modelRes;
        this.modelHeight = modelRes;
    }

    /**
     * Gets width by ratio.
     *
     * @param width the width
     * @return the width by ratio
     */
    public int getWidthByRatio(float width) {
        return (int) (width * ((float) this.screenWidth / this.modelWidth));
    }

    /**
     * Gets height by ratio.
     *
     * @param height the height
     * @return the height by ratio
     */
    public int getHeightByRatio(float height) {
        return (int) (height * ((float) this.screenHeight / this.modelHeight));
    }
}
