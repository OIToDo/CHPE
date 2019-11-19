package com.mygdx.game.PoseEstimation;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
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
    public String mUri;
    public Uri uri;


    public VideoSplicer(String uri) {
        this.mUri = uri;

        // Accessing the file
        this.mediaMetadataRetriever.setDataSource(uri);

        // Getting the video duration
        this.getVideoDuration();

        // Getting the amount of frames in video
        this.getAmountOfFrames();

        // Calculating the iter frame count based on those values
        this.getFrameIterTime();
    }

    public VideoSplicer(Uri uri, Context context) {
        this.uri = uri;

        // Accessing the file
        this.mediaMetadataRetriever.setDataSource(context, uri);

        // Getting the video duration
        this.getVideoDuration();

        // Getting the amount of frames in video
        this.getAmountOfFrames();

        // Calculating the iter frame count based on those values
        this.getFrameIterTime();
    }


    public long getIterTimeUs() {
        return iterTimeUs;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public int getFramesProcessed() {
        return framesProcessed;
    }

    public float getFramesPerSecond(){
        return Float.parseFloat(Long.toString(this.iterTimeUs));
    }


    private void getVideoDuration() {
        System.out.println(this.uri.toString());
        try {
            String sTotalTime = this.mediaMetadataRetriever.extractMetadata(VIDEO_DURATION);
            this.totalTime = Long.parseLong(sTotalTime);
        } catch (NumberFormatException nfe) {
            // TODO: Notify user of invalid file.
            Log.e("VIDEOSPLICER: ", "NumberFormatException: " + nfe.getMessage());
        }

    }

    private void getAmountOfFrames() {
        try {
            String sFrameCount = this.mediaMetadataRetriever.extractMetadata(VIDEO_FRAME_COUNT);
            this.frameCount = Integer.parseInt(sFrameCount);
        } catch (NumberFormatException nfe) {
            // TODO: Notify user of invalid file.
            Log.e("VIDEOSPLICER: ", "NumberFormatException: " + nfe.getMessage());
        }
    }

    private void getFrameIterTime() {
        try {

            this.iterTimeUs = this.totalTime / this.frameCount;
        } catch (ArithmeticException ae) {
            // TODO: Notify user of invalid file.
            Log.e("VIDEOSPLICER: ", "ArithmeticException: " + ae.getMessage());
        }
    }

    public boolean isNextFrameAvailable() {
        return this.framesProcessed + 1 <= this.frameCount;
    }

    public Bitmap getNextFrame(int frame) {
        // TODO: return this.mediaMetadataRetriever.getFrameAtIndex(this.framesProcessed);
        Bitmap mp = this.mediaMetadataRetriever.getFrameAtTime(
                frame * this.iterTimeUs);
        return mp;
    }


    public Bitmap getNextFrame() throws InvalidFrameAccess {
        if (isNextFrameAvailable()) {

            // TODO: return this.mediaMetadataRetriever.getFrameAtIndex(this.framesProcessed);
            Bitmap mp = this.mediaMetadataRetriever.getFrameAtTime(
                    this.framesProcessed * this.iterTimeUs);
            this.framesProcessed += 1;
            return mp;
        }
        throw new InvalidFrameAccess("InvalidFrameAccess", new Throwable("Next Frame doesn't exist."));
    }


}
