package com.mygdx.game.persistance;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import test.com.example.coordinatesdao.persistance.Coordinate.NNCoordinate;
import test.com.example.coordinatesdao.persistance.Coordinate.NNCoordinateDAO;
import test.com.example.coordinatesdao.persistance.Coordinates.NNCoordinates;
import test.com.example.coordinatesdao.persistance.Coordinates.NNCoordinatesDAO;
import test.com.example.coordinatesdao.persistance.Relations.NNCoordinatesCoordinate;
import test.com.example.coordinatesdao.persistance.Relations.NNCoordinatesCoordinateDAO;
import test.com.example.coordinatesdao.persistance.Relations.NNSessionCoordinates;
import test.com.example.coordinatesdao.persistance.Relations.NNSessionCoordinatesDAO;
import test.com.example.coordinatesdao.persistance.Session.NNSession;
import test.com.example.coordinatesdao.persistance.Session.NNSessionDAO;


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

