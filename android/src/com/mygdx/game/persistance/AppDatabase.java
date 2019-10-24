package com.mygdx.game.persistance;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.mygdx.game.persistance.Coordinate.NNCoordinate;
import com.mygdx.game.persistance.Coordinate.NNCoordinateDAO;
import com.mygdx.game.persistance.Coordinates.NNCoordinates;
import com.mygdx.game.persistance.Coordinates.NNCoordinatesDAO;
import com.mygdx.game.persistance.Relations.NNCoordinatesCoordinate;
import com.mygdx.game.persistance.Relations.NNCoordinatesCoordinateDAO;
import com.mygdx.game.persistance.Relations.NNSessionCoordinates;
import com.mygdx.game.persistance.Relations.NNSessionCoordinatesDAO;
import com.mygdx.game.persistance.Session.NNSession;
import com.mygdx.game.persistance.Session.NNSessionDAO;


@Database(
        entities = {
                NNCoordinates.class,
                NNSession.class,
                NNCoordinate.class,

                // Many-to-many
                NNSessionCoordinates.class,
                NNCoordinatesCoordinate.class,

        },
        version = 1,
        exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public abstract NNCoordinatesDAO nnCoordinatesDAO();

    public abstract NNSessionDAO nnSessionDAO();

    public abstract NNCoordinateDAO nnCoordinateDAO();

    public abstract NNSessionCoordinatesDAO nnSessionCoordinatesDAO();

    public abstract NNCoordinatesCoordinateDAO nnCoordinatesCoordinateDAO();

}

