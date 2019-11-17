package com.mygdx.game.PoseEstimation;

import android.content.Context;

import com.mygdx.game.persistance.AppDatabase;
import com.mygdx.game.persistance.Coordinate.NNCoordinate;
import com.mygdx.game.persistance.Frame.NNFrame;
import com.mygdx.game.persistance.PersistenceClient;
import com.mygdx.game.persistance.Relations.NNFrameCoordinate;
import com.mygdx.game.persistance.Relations.NNVideoFrame;
import com.mygdx.game.PoseEstimation.*;

public class NNInserts {

    private AppDatabase appDatabase;

    public NNInserts(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;

    }

    public NNInserts(Context context) {
        this.appDatabase = PersistenceClient.getInstance(context).getAppDatabase();
    }


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
