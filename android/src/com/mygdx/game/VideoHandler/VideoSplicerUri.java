package com.mygdx.game.VideoHandler;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;

import com.mygdx.game.Exceptions.InvalidFrameAccess;

/**
 * The type Video splicer.
 */
public class VideoSplicerUri implements VideoSplicer {
    private static final String TAG = VideoSplicerUri.class.getSimpleName();
    private static final int VIDEO_FRAME_COUNT = 19;
    private static final int VIDEO_DURATION = 9;
    /**
     * The M uri.
     */
    private String mUri;
    /**
     * The Uri.
     */
    private Uri uri;

    private MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
    int frameCount = -1;
    int framesProcessed = -1;

    /**
     * Instantiates a new Video splicer.
     *
     * @param uri the uri
     */
    VideoSplicerUri(String uri) {
        this.mUri = uri;

        // Accessing the file
        this.mediaMetadataRetriever.setDataSource(uri);


        // Getting the amount of frames in video
        this.getAmountOfFrames();


    }


    /**
     * Instantiates a new Video splicer.
     *
     * @param uri     the uri
     * @param context the context
     */
    VideoSplicerUri(Uri uri, Context context) {
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


    /**
     * Gets iter time us.
     *
     * @return the iter time us
     */
    long getIterTimeUs() {
        return getIterTimeUs();
    }

    /**
     * Gets frame count.
     *
     * @return the frame count
     */
    public int getFrameCount() {
        return this.frameCount;
    }

    /**
     * Gets frames processed.
     *
     * @return the frames processed
     */
    public int getFramesProcessed() {
        return framesProcessed;
    }

    /**
     * Get frames per second float.
     *
     * @return the float
     */
    public float getFramesPerSecond() {
        return Float.parseFloat(Long.toString(this.getFrameIterTime()));
    }


    long getVideoDuration() throws NumberFormatException {
        try {
            String sTotalTime = this.mediaMetadataRetriever.extractMetadata(VIDEO_DURATION);
            return Long.parseLong(sTotalTime);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException();
        }

    }

    long getFrameIterTime() throws ArithmeticException {
        try {
            return this.getVideoDuration() / this.frameCount;
        } catch (ArithmeticException ae) {
            // TODO: Notify user of invalid file.
            throw new ArithmeticException(ae.toString());
        }
    }

    private void getAmountOfFrames() {
        try {
            String sFrameCount = this.mediaMetadataRetriever.extractMetadata(VIDEO_FRAME_COUNT);
            this.frameCount = Integer.parseInt(sFrameCount);
        } catch (NumberFormatException nfe) {
            // TODO: Notify user of invalid file.
            Log.e(TAG, "NumberFormatException: " + nfe.getMessage());
        }
    }


    /**
     * Is next frame available boolean.
     *
     * @return the boolean
     */
    public boolean isNextFrameAvailable() {
        return this.framesProcessed + 1 <= this.frameCount;
    }

    /**
     * Gets next frame.
     *
     * @param frame the frame
     * @return the next frame
     */
    public Bitmap getNextFrame(int frame) {
        // TODO: return this.mediaMetadataRetriever.getFrameAtIndex(this.framesProcessed);

        Bitmap mp = this.mediaMetadataRetriever.getFrameAtIndex(
                frame);
        return mp;
    }


    /**
     * Gets next frame.
     *
     * @return the next frame
     * @throws InvalidFrameAccess the invalid frame access
     */
    public Bitmap getNextFrame() throws InvalidFrameAccess {
        if (isNextFrameAvailable()) {

            // TODO: return this.mediaMetadataRetriever.getFrameAtIndex(this.framesProcessed);
            Bitmap mp = this.mediaMetadataRetriever.getFrameAtIndex(
                    this.framesProcessed);
            this.framesProcessed += 1;
            return mp;
        }
        throw new InvalidFrameAccess("InvalidFrameAccess", new Throwable("Next Frame doesn't exist."));
    }


}
