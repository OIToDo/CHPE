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
        // is the name of the database
        // TODO: change name 'MyToDos3'
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "MyToDos3")
               .allowMainThreadQueries().build();
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
