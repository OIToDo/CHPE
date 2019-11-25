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
public class VideoSplicerUriLegacy extends VideoSplicerUri {
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
    public Uri uri;
    private long iterTimeUs; // Used to indicate how long a single frame is on screen
    private long totalTime;

    private MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();


    /**
     * Instantiates a new Video splicer.
     *
     * @param uri the uri
     */
    VideoSplicerUriLegacy(String uri) {
        super(uri);
    }

    VideoSplicerUriLegacy(Uri uri, Context context) {
        super(uri, context);
    }

    private void initialiseVideoSplicerLegacy(){
        this.totalTime = this.getVideoDuration();
        // Calculating the iter frame count based on those values
        this.iterTimeUs = this.getFrameIterTime();
    }


    /**
     * Gets next frame.
     *
     * @param frame the frame
     * @return the next frame
     */
    @Override
    public Bitmap getNextFrame(int frame) {
        return this.mediaMetadataRetriever.getFrameAtTime(
                frame * this.iterTimeUs);
    }



    /**
     * Gets next frame.
     *
     * @return the next frame
     * @throws InvalidFrameAccess the invalid frame access
     */
    @Override
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
