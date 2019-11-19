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


/**
 * The type App database.
 */
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

    /**
     * Nn frame dao nn frame dao.
     *
     * @return the nn frame dao
     */
    public abstract NNFrameDAO nnFrameDAO();

    /**
     * Nn video dao nn video dao.
     *
     * @return the nn video dao
     */
    public abstract NNVideoDAO nnVideoDAO();

    /**
     * Nn coordinate dao nn coordinate dao.
     *
     * @return the nn coordinate dao
     */
    public abstract NNCoordinateDAO nnCoordinateDAO();

    /**
     * Nn session frame dao nn video frame dao.
     *
     * @return the nn video frame dao
     */
    public abstract NNVideoFrameDAO nnVideoFrame();

    /**
     * Nn frame coordinate dao nn frame coordinate dao.
     *
     * @return the nn frame coordinate dao
     */
    public abstract NNFrameCoordinateDAO nnFrameCoordinateDAO();
}

