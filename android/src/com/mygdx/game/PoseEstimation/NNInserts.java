package com.mygdx.game.PoseEstimation;

import com.mygdx.game.persistance.AppDatabase;
import com.mygdx.game.persistance.Coordinate.NNCoordinate;
import com.mygdx.game.persistance.Frame.NNFrame;
import com.mygdx.game.persistance.Relations.NNFrameCoordinate;
import com.mygdx.game.persistance.Relations.NNVideoFrame;

import java.util.Arrays;
import java.util.List;

/**
 * The type Nn inserts.
 */
public class NNInserts {

    private Resolution resolution;
    private AppDatabase appDatabase;
    private long y_limit, x_limit;

    /**
     * Instantiates a new Nn inserts.
     *
     * @param appDatabase the app database
     * @param resolution  the resolution
     */
    public NNInserts(AppDatabase appDatabase, Resolution resolution) {
        this.appDatabase = appDatabase;
        this.resolution = resolution;
    }


    /**
     * Normalise ints list.
     *
     * @param raw the raw
     * @return the list
     */
    public List<Double> normaliseInts(List<Integer> raw) {
        double x = 10.00, y = 10.00;
        List<Double> list = Arrays.asList(x, y);
        return list;
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
     * @param p          the p
     * @param videoId    the video id
     * @param frameCount the frame count
     */
    public void insertPerson(Person p, long videoId, int frameCount) {

        // Creating new frame for the instance
        NNFrame nnFrame = new NNFrame();
        nnFrame.frame_count = frameCount;
        long frameId = this.appDatabase.nnFrameDAO().insert(nnFrame);

        // Linking to the video
        NNVideoFrame nnVideoFrame = new NNVideoFrame();
        nnVideoFrame.frame_id = frameId;
        nnVideoFrame.video_id = videoId;
        this.appDatabase.nnSessionFrameDAO().insert(nnVideoFrame); // TODO: Refactor nnSessionFrameDAO

        for (KeyPoint keyPoint : p.getKeyPoints()) {
            NNCoordinate nnCoordinate = new NNCoordinate();
            nnCoordinate.raw_x = keyPoint.getPosition().getX();
            nnCoordinate.raw_y = keyPoint.getPosition().getY();
            long coordinateId = this.appDatabase.nnCoordinateDAO().insert(nnCoordinate);

            NNFrameCoordinate nnFrameCoordinate = new NNFrameCoordinate();
            nnFrameCoordinate.frame_id = frameId;
            nnFrameCoordinate.coordinate_id = coordinateId;
            this.appDatabase.nnFrameCoordinateDAO().insert(nnFrameCoordinate);
        }
    }
}
