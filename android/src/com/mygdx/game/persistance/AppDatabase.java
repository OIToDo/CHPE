package com.mygdx.game.persistance;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mygdx.game.persistance.Coordinate.NNCoordinate;
import com.mygdx.game.persistance.Coordinate.NNCoordinateDAO;
import com.mygdx.game.persistance.Frame.NNFrame;
import com.mygdx.game.persistance.Frame.NNFrameDAO;
import com.mygdx.game.persistance.Relations.NNFrameCoordinate;
import com.mygdx.game.persistance.Relations.NNFrameCoordinateDAO;
import com.mygdx.game.persistance.Relations.NNSessionFrame;
import com.mygdx.game.persistance.Relations.NNSessionFrameDAO;
import com.mygdx.game.persistance.Session.NNSession;
import com.mygdx.game.persistance.Session.NNSessionDAO;


@Database(
        entities = {
                NNFrame.class,
                NNSession.class,
                NNCoordinate.class,

                // Many-to-many
                NNSessionFrame.class,
                NNFrameCoordinate.class,
        },
        version = 1,
        exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public abstract NNFrameDAO nnFrameDAO();

    public abstract NNSessionDAO nnSessionDAO();

    public abstract NNCoordinateDAO nnCoordinateDAO();

    public abstract NNSessionFrameDAO nnSessionFrameDAO();

    public abstract NNFrameCoordinateDAO nnFrameCoordinateDAO();
}

