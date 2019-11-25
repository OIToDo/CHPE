package com.mygdx.game.VideoHandler;

import android.graphics.Bitmap;

import com.mygdx.game.Exceptions.InvalidFrameAccess;

public interface VideoSplicer {

    boolean isNextFrameAvailable();

    Bitmap getNextFrame(int frame);

    Bitmap getNextFrame() throws InvalidFrameAccess;

}
