package com.mygdx.game.persistance;

import android.content.Context;
import androidx.room.Room;

public class PersistenceClient {

    private Context mCtx;
    private static PersistenceClient mInstance;

    //our app database object
    private AppDatabase appDatabase;

    private PersistenceClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "MyToDos").build();
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
