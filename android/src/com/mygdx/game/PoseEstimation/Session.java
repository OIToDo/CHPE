package com.mygdx.game.PoseEstimation;


// Ensuring that sessions can be cancelled and continued later on.

import android.content.Context;
import android.util.Log;

import com.mygdx.game.Exceptions.InvalidFrameAccess;

public class Session {

    private int frameCount;
    private CHPE chpe;
    private Context context;
    private VideoSplicer videoSplicer;


    // TODO: Run benchmark configuration
    public Session(String uri, Context context) {
        this.videoSplicer = new VideoSplicer(uri);

        // TODO: Advise if resolution is large and may use more time (i.e. 4k footage)
        // TODO: Create resolution instance based on first frame
        // TODO: Create instance of CHPE class based on resolution data
        this.chpe = new CHPE(context, new Resolution(this.videoSplicer.getNextFrame(0)));
    }

    public void runVideo() {
        // TODO: Loop through individual frames
        // TODO: Convert frame to bitmap
        // TODO: Pass bitmap through CHPE instance
        while (this.videoSplicer.isNextFrameAvailable()) {
            try {
                Person p = this.chpe.ProcessFrame(this.videoSplicer.getNextFrame());

            } catch (InvalidFrameAccess invalidFrameAccess) {
                Log.e("runVideo -> PoseNet - Iterator", "runVideo: ", invalidFrameAccess);
            }
        }
    }


    private void PersonToFrame(Person person){
        // TODO: Convert Person values to Frame
        // TODO: Insert Frame
        // TODO: Insert Session
        // TODO: Insert Join between frame and session
        // TODO: Insert Coordinates
        // TODO: Insert Join between frame and coordinates
    }






}
