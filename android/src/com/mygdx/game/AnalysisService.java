package com.mygdx.game;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.util.Log;

import com.mygdx.game.PoseEstimation.Session;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class AnalysisService extends JobIntentService {
    private static final String TAG = "AnalysisService";

    static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, AnalysisService.class, 117, work);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d(TAG, "onHandleWork: ");

        String input = intent.getStringExtra("inputExtra");
        //Fake work, SAD, should be replaced with great work! AMERICAN WORK!
        
        for (int i = 0; i < 10; i++) {

            Log.d(TAG, input + " - " + i);
            Log.d(TAG, "IK WERK");

            if(isStopped()) {
                return;
            }

            SystemClock.sleep(1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public boolean onStopCurrentWork() {
        Log.d(TAG, "onStopCurrentWork: ");
        return super.onStopCurrentWork();
    }
}
