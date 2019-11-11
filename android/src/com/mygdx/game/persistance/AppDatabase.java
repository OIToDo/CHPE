package com.mygdx.game.persistance;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mygdx.game.persistance.Coordinate.NNCoordinate;
import com.mygdx.game.persistance.Coordinate.NNCoordinateDAO;
import com.mygdx.game.persistance.Frame.NNFrame;
import com.mygdx.game.persistance.Frame.NNFrameDAO;
import com.mygdx.game.persistance.Relations.NNFrameCoordinate;
import com.mygdx.game.persistance.Relations.NNFrameCoordinateDAO;
import com.mygdx.game.persistance.Relations.NNVideoFrame;
import com.mygdx.game.persistance.Relations.NNVideoFrameDAO;
import com.mygdx.game.persistance.Video.NNVideo;
import com.mygdx.game.persistance.Video.NNVideoDAO;


@Database(
        entities = {
                NNFrame.class,
                NNVideo.class,
                NNCoordinate.class,

                // Many-to-many
                NNVideoFrame.class,
                NNFrameCoordinate.class,
        },
        version = 2,
        exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public abstract NNFrameDAO nnFrameDAO();

    public abstract NNVideoDAO nnVideoDAO();

    public abstract NNCoordinateDAO nnCoordinateDAO();

    public abstract NNVideoFrameDAO nnSessionFrameDAO();

    public abstract NNFrameCoordinateDAO nnFrameCoordinateDAO();
}

