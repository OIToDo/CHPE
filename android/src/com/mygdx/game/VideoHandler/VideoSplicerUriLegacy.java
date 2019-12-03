package com.mygdx.game.VideoHandler;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;

import com.mygdx.game.Exceptions.InvalidFrameAccess;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Random;

import static android.media.MediaMetadataRetriever.OPTION_CLOSEST;
import static android.media.MediaMetadataRetriever.OPTION_CLOSEST_SYNC;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

/**
 * The type Video splicer.
 */
public class VideoSplicerUriLegacy extends VideoSplicerUri {
    private static final String TAG = VideoSplicerUri.class.getSimpleName();
    /**
     * The M uri.
     */
    private String mUri;
    /**
     * The Uri.
     */
    public Uri uri;
    /**
     * The Iter time us.
     */
    public long iterTimeUs; // Used to indicate how long a single frame is on screen
    /**
     * The Total time.
     */
    public long totalTime; // TODO: Update to allow longer video's. The current limit would be
    private long usToS, sToUS = 1000000; // Converting microseconds to seconds, double assignment for usability reasons.

    private MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();


    /**
     * Instantiates a new Video splicer.
     *
     * @param uri the uri
     */
    VideoSplicerUriLegacy(String uri) {
        super(uri);
    }

    /**
     * Instantiates a new Video splicer uri legacy.
     *
     * @param uri     the uri
     * @param context the context
     */
    public VideoSplicerUriLegacy(Uri uri, Context context) {
        super(uri, context);
        this.uri = uri;

        initialiseVideoSplicerLegacy();
    }

    /**
     * Instantiates a new Video splicer uri legacy.
     *
     * @param uri the uri
     * @param map the map
     */
    public VideoSplicerUriLegacy(String uri, HashMap<String, String> map) {
        super(Uri.parse(uri), getApplicationContext());
        initialiseVideoSplicerLegacy();
    }



    private void initialiseVideoSplicerLegacy(){
        this.totalTime = getVideoDuration();
        // Calculating the iter frame count based on those values
        this.iterTimeUs = getFrameIterTime();

    }



    /**
     * Gets next frame.
     *
     * @param frame the frame, MUST be atleast 1
     * @return the next frame
     */
    @Override
    public Bitmap getNextFrame(int frame) {

        if(frame == 0 || frame < 0 || frame > this.frameCount){
            throw new InvalidParameterException("Value must between 1 - " + this.frameCount);
        }
        return this.mediaMetadataRetriever.getFrameAtTime(
                frame * this.iterTimeUs);
    }


    /**
     * Gets random frame.
     *
     * @return the random frame
     */
    public Bitmap getRandomFrame() {
        return this.mediaMetadataRetriever.getFrameAtTime(
                (new Random().nextInt(this.frameCount) + 1) // getting a random int.
                        // Ensuring that the frame isn't zero.
                        * this.iterTimeUs, OPTION_CLOSEST_SYNC );
    }


    /**
     * Gets frame iter time.
     *
     * @return the frame iter time
     * @throws ArithmeticException the arithmetic exception
     */
    public long getFrameIterTime() throws ArithmeticException {
        try {
            return (this.totalTime / this.frameCount);
        } catch (ArithmeticException ae) {
            // TODO: Notify user of invalid file.
            throw new ArithmeticException(ae.toString());
        }
    }

    private void calculateFramesPerSecond() throws ArithmeticException {
        try {
            this.iterTimeUs = (this.sToUS * 60) / (this.getFrameIterTime());
        } catch (ArithmeticException ae) {
            // TODO: Notify user of invalid file.
            throw new ArithmeticException(ae.toString());
        }
    }

    @Override
    public float getFramesPerSecond() {
        try {
            return this.sToUS / (this.getFrameIterTime());
        } catch (ArithmeticException ae) {
            // TODO: Notify user of invalid file.
            throw new ArithmeticException(ae.toString());
        }
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
