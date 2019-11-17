package com.mygdx.game.PoseEstimation;


// Ensuring that sessions can be cancelled and continued later on.

import android.content.Context;
import android.util.Log;

import com.mygdx.game.Exceptions.InvalidFrameAccess;
import com.mygdx.game.persistance.AppDatabase;
import com.mygdx.game.persistance.PersistenceClient;
import com.mygdx.game.persistance.Video.NNVideo;
import com.mygdx.game.PoseEstimation.*;

public class Session {

    private NNInserts nnInsert;
    private CHPE chpe;
    private Context context;
    private VideoSplicer videoSplicer;
    private AppDatabase appDatabase;
    private long videoId;
    private Resolution resolution;

    // TODO: Run benchmark configuration
    public Session(String uri, Context context) {
        this.videoSplicer = new VideoSplicer(uri);
        this.appDatabase = PersistenceClient.getInstance(context).getAppDatabase();
        this.resolution = new Resolution(this.videoSplicer.getNextFrame(0));
        this.chpe = new CHPE(context, this.resolution);

        this.initialiseDatabase(); // Preparing database for person entry
    }

    private void initialiseDatabase() {
        NNVideo emptyVideo = new NNVideo();
        emptyVideo.width = this.resolution.screenWidth;
        emptyVideo.height = this.resolution.screenHeight;
        emptyVideo.frame_count = this.videoSplicer.getFrameCount();
        emptyVideo.frames_per_second = this.videoSplicer.getFramesPerSecond();

        this.videoId = this.appDatabase.nnVideoDAO().insert(emptyVideo);
        this.nnInsert = new NNInserts(this.appDatabase);
    }

    public void runVideo() {
        while (this.videoSplicer.isNextFrameAvailable()) {
            try {
                Person p = this.chpe.ProcessFrame(this.videoSplicer.getNextFrame());

            } catch (InvalidFrameAccess invalidFrameAccess) {
                Log.e("runVideo -> PoseNet - Iterator", "runVideo: ", invalidFrameAccess);
            }
        }
    }


    private void PersonToFrame(Person person) {
        this.nnInsert.insertPerson(person, this.videoId, this.videoSplicer.getFramesProcessed());
    }


}
