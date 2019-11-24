package com.mygdx.game.PoseEstimation;

import com.mygdx.game.Persistance.AppDatabase;
import com.mygdx.game.Persistance.Coordinate.NNCoordinate;
import com.mygdx.game.Persistance.Frame.NNFrame;
import com.mygdx.game.Persistance.Relations.NNFrameCoordinate;
import com.mygdx.game.Persistance.Relations.NNVideoFrame;
import com.mygdx.game.PoseEstimation.NN.PoseNet.KeyPoint;
import com.mygdx.game.PoseEstimation.NN.PoseNet.Person;

import java.util.Arrays;
import java.util.List;

/**
 * NNInsert is an example class used for inserting new videos to the database
 */
public class NNInserts {

    private Resolution resolution; // Used for normalisations
    private AppDatabase appDatabase;
    private long y_limit, x_limit;

    /**
     * Instantiates a new NN inserts.
     *
     * @param appDatabase the app database
     * @param resolution  the resolution
     */
    NNInserts(AppDatabase appDatabase, Resolution resolution) {
        this.appDatabase = appDatabase;
        this.resolution = resolution;
    }

    /**
     * Normalise ints list.
     *
     * @param integerList the raw integer values
     * @return the list
     */
    public List<Double> normaliseInts(List<Integer> integerList) {
        double x = 10.00, y = 10.00;
        return Arrays.asList(x, y);
    }


    /**
     * Normalise coordinates.
     *
     * @param sessionId the session id
     */
    public void normaliseCoordinates(long sessionId) {
        this.y_limit = this.appDatabase.nnVideoDAO().getMaxValuesX(sessionId);
        this.x_limit = this.appDatabase.nnVideoDAO().getMaxValuesY(sessionId);
    }


    /**
     * Insert person.
     *
     * @param person     the Person object used to extract points from
     * @param videoId    the video id based on the database insert
     * @param frameCount the nth frame count to ensure it's placed in order.
     */
    void insertPerson(Person person, long videoId, int frameCount) {

        // Creating new frame for the instance
        NNFrame nnFrame = new NNFrame();
        nnFrame.frame_count = frameCount;
        long frameId = this.appDatabase.nnFrameDAO().insert(nnFrame);

        linkFrameIdToVideo(frameId, videoId);

        for (KeyPoint keyPoint : person.getKeyPoints()) {
            linkFrameToCoordinate(
                    frameId,
                    this.appDatabase
                        .nnCoordinateDAO()
                        .insert(
                                new NNCoordinate(
                                        keyPoint.getPosition().getX(),
                                        keyPoint.getPosition().getY()
                                )
                    )
            );
        }
    }

    private void linkFrameToCoordinate(long frameId, long coordinateId) {
        this.appDatabase
                .nnFrameCoordinateDAO()
                .insert(
                        new NNFrameCoordinate(frameId, coordinateId)
                );
    }

    private void linkFrameIdToVideo(long frameId, long videoId) {
        // Linking to the video
        this.appDatabase
                .nnVideoFrame()
                .insert(
                        new NNVideoFrame(videoId, frameId)
                );
    }
}
