package com.mygdx.game.persistance;

import android.content.Context;

import androidx.room.Room;

public class PersistenceClient {

    private Context mCtx;
    private static PersistenceClient mInstance;

    private static String databaseName = "CHPEv2";

    //our app database object
    private AppDatabase appDatabase;

    private PersistenceClient(Context mCtx) {
        this.mCtx = mCtx;
        // Ensure that the database name is NOT the actual database name
        //creating the app database with Room database builder
        // is the name of the database
        this.appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, databaseName)
                .allowMainThreadQueries().build();
    }

    private PersistenceClient(Context mCtx, String debugDatabaseName) {
        this.mCtx = mCtx;
        // Ensure that the database name is NOT the actual database name
        //creating the app database with Room database builder
        // is the name of the database
        this.appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, debugDatabaseName)
                .allowMainThreadQueries().build();
    }


    public static synchronized PersistenceClient getInstance(Context mCtx, String debugDatabaseName) {
        if (mInstance == null) {
            if (debugDatabaseName != null) {
                mInstance = new PersistenceClient(mCtx, debugDatabaseName);
            } else
                mInstance = new PersistenceClient(mCtx);
        }
        return mInstance;
    }

    public static synchronized PersistenceClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new PersistenceClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
