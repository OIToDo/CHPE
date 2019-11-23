package com.mygdx.game.PoseEstimation;


// Ensuring that sessions can be cancelled and continued later on.

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.mygdx.game.Exceptions.InvalidFrameAccess;
import com.mygdx.game.PoseEstimation.NN.ModelParser;
import com.mygdx.game.PoseEstimation.NN.PoseNet.Person;
import com.mygdx.game.Persistance.AppDatabase;
import com.mygdx.game.Persistance.PersistenceClient;
import com.mygdx.game.Persistance.Video.NNVideo;

/**
 * The type Session.
 */
public class Session {

    private NNInserts nnInsert;
    private CHPE chpe;
    private VideoSplicer videoSplicer;
    private AppDatabase appDatabase;
    private long videoId;
    private Resolution resolution;

    /**
     * Instantiates a new Session.
     *
     * @param uri     the uri
     * @param context the context
     */
// TODO: Run benchmark configuration
    public Session(String uri, Context context) {

        System.err.println(uri);

        this.videoSplicer = new VideoSplicer(uri);
        this.appDatabase = PersistenceClient.getInstance(context).getAppDatabase();
        this.resolution = new Resolution(this.videoSplicer.getNextFrame(0));
        this.chpe = new CHPE(context, this.resolution, ModelParser.POSENET_MODEL);

        this.initialiseDatabase(); // Preparing database for person entry
    }

    /**
     * Instantiates a new Session.
     *
     * @param uri     the uri
     * @param context the context
     */
    public Session(Uri uri, Context context) {

        this.videoSplicer = new VideoSplicer(uri, context);
        this.appDatabase = PersistenceClient.getInstance(context).getAppDatabase();
        this.resolution = new Resolution(this.videoSplicer.getNextFrame(0));
        this.chpe = new CHPE(context, this.resolution, ModelParser.POSENET_MODEL);

        this.initialiseDatabase(); // Preparing database for person entry
    }

    private void initialiseDatabase() {

        this.videoId = this.appDatabase.nnVideoDAO().insert(new NNVideo(
                this.videoSplicer.getFramesPerSecond(),
                this.videoSplicer.getFrameCount(),
                this.resolution.screenWidth,
                this.resolution.screenHeight
        ));
        this.nnInsert = new NNInserts(this.appDatabase, this.resolution);
    }

    /**
     * Run video.
     */
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

    private void normaliseData() {

    }
}
