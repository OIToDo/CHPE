package com.mygdx.game.PoseEstimation;

import android.graphics.Bitmap;

/**
 * The type Resolution.
 */
public class Resolution {
    /**
     * The Model width.
     */
    private int modelWidth = 257;
    /**
     * The Model height.
     */
    private int modelHeight = 257;
    /**
     * The Screen width.
     */
    private int screenWidth;
    /**
     * The Screen height.
     */
    private int screenHeight;

    int getModelWidth() {
        return modelWidth;
    }
    
    int getModelHeight() {
        return modelHeight;
    }

    int getScreenWidth() {
        return screenWidth;
    }

    int getScreenHeight() {
        return screenHeight;
    }


    /**
     * Instantiates a new Resolution.
     *
     * @param bitmap the bitmap
     */
    Resolution(Bitmap bitmap) {
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
    Resolution(int width, int height, int modelWidth, int modelHeight) {
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
    Resolution(int width, int height, int modelRes) {
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
    int getWidthByRatio(float width) throws NumberFormatException {

        if (width > this.screenWidth) {
            throw new NumberFormatException("Width higher than image");
        }

        if (width < 0) {
            throw new NumberFormatException("Width lower than 0");
        }

        return (int) (width * ((float) this.screenWidth / this.modelWidth));
    }

    /**
     * Gets height by ratio.
     *
     * @param height the height
     * @return the height by ratio
     */
    int getHeightByRatio(float height) {
        if (height > this.screenHeight) {
            throw new NumberFormatException("Height higher than image");
        }

        if (height < 0) {
            throw new NumberFormatException("Height lower than 0");
        }
        return (int) (height * ((float) this.screenHeight / this.modelHeight));
    }
}
