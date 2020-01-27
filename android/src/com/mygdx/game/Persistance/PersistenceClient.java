package com.mygdx.game.Persistance;

import android.content.Context;

import com.mygdx.game.DebugLog;
import com.mygdx.game.MockData;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * The type Persistence client.
 */
public class PersistenceClient {

    private static PersistenceClient mInstance;
    private static String databaseName = "CHPEv3";
    private Context mCtx;
    //our app database object
    private AppDatabase appDatabase;

    private PersistenceClient(final Context mCtx) {
        this.mCtx = mCtx;
        // Ensure that the database name is NOT the actual database name
        //creating the app database with Room database builder
        // is the name of the database
        this.appDatabase = Room.databaseBuilder(mCtx,
                AppDatabase.class,
                databaseName)
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(mCtx).getAppDatabase();

                            }
                        });
                    }
                })
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


    /**
     * Gets instance.
     *
     * @param mCtx              the m ctx
     * @param debugDatabaseName a database name
     * @return the instance
     */
    public static synchronized PersistenceClient getInstance(Context mCtx, String debugDatabaseName) {
        if (mInstance == null) {
            if (debugDatabaseName != null) {
                mInstance = new PersistenceClient(mCtx, debugDatabaseName);
            } else
                mInstance = new PersistenceClient(mCtx);
        }
        return mInstance;
    }

    /**
     * Gets instance.
     *
     * @param mCtx the m ctx
     * @return the instance
     */
    public static synchronized PersistenceClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new PersistenceClient(mCtx);
        }
        return mInstance;
    }

    /**
     * Gets app database.
     *
     * @return the app database
     */
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
