package com.mygdx.game.VideoHandler;

import android.graphics.Bitmap;

import com.mygdx.game.Exceptions.InvalidFrameAccess;

public interface VideoSplicer {

    boolean isNextFrameAvailable();

    int getFramesProcessed();

    int getFrameCount();
    long getFramesPerSecond();


    Bitmap getNextFrame(int frame);

    Bitmap getNextFrame() throws InvalidFrameAccess;

}
