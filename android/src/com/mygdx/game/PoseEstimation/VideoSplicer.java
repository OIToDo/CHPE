package com.mygdx.game.PoseEstimation;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.util.Log;

import com.mygdx.game.Exceptions.InvalidFrameAccess;

public class VideoSplicer {

    private static final int VIDEO_FRAME_COUNT = 19;
    private static final int VIDEO_DURATION = 9;
    private int frameCount;
    private int framesProcessed = 0;
    private long iterTimeUs; // Used to indicate how long a single frame is on screen
    private long totalTime;
    private MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
    public String uri;


    public VideoSplicer(String uri) {
        this.uri = uri;

        // Accessing the file
        this.mediaMetadataRetriever.setDataSource(uri);


        // Getting the video duration
        this.getVideoDuration();

        // Getting the amount of frames in video
        this.getAmountOfFrames();

        // Calculating the iter frame count based on those values
        this.getFrameIterTime();

    }

    private void getVideoDuration(){
        try {
            String sTotalTime = this.mediaMetadataRetriever.extractMetadata(VIDEO_DURATION);
            this.totalTime = Long.parseLong(sTotalTime);
        }
        catch (NumberFormatException nfe)
        {
            // TODO: Notify user of invalid file.
            Log.e("VIDEOSPLICER: ","NumberFormatException: " + nfe.getMessage());
        }

    }

    private void getAmountOfFrames() {
        try
        {
            String sFrameCount = this.mediaMetadataRetriever.extractMetadata(VIDEO_FRAME_COUNT);
            this.frameCount = Integer.parseInt(sFrameCount);
        }
        catch (NumberFormatException nfe)
        {
            // TODO: Notify user of invalid file.
            Log.e("VIDEOSPLICER: ","NumberFormatException: " + nfe.getMessage());
        }
    }

    private void getFrameIterTime(){
        try {

            this.iterTimeUs = this.totalTime / this.frameCount;
        }
        catch (ArithmeticException ae)
        {
            // TODO: Notify user of invalid file.
            Log.e("VIDEOSPLICER: ","ArithmeticException: " + ae.getMessage());
        }
    }


    public boolean isNextFrameAvailable() {
        return this.framesProcessed + 1 <= this.frameCount;
    }

    public Bitmap getNextFrame()throws InvalidFrameAccess {
        if(isNextFrameAvailable()){

            // TODO: return this.mediaMetadataRetriever.getFrameAtIndex(this.framesProcessed);
            Bitmap mp =  this.mediaMetadataRetriever.getFrameAtTime(
                    this.framesProcessed * this.iterTimeUs);
            this.framesProcessed += 1;
            return mp;
        }
        throw new InvalidFrameAccess("InvalidFrameAccess", new Throwable("Next Frame doesn't exist."));
    }


}
