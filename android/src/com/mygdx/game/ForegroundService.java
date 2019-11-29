package com.mygdx.game;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Debug;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.mygdx.game.Exceptions.InvalidFrameAccess;
import com.mygdx.game.Exceptions.InvalidVideoSplicerType;
import com.mygdx.game.PoseEstimation.CHPE;
import com.mygdx.game.PoseEstimation.NN.ModelParser;
import com.mygdx.game.PoseEstimation.Session;
import com.mygdx.game.VideoHandler.VideoSplicer;
import com.mygdx.game.VideoHandler.VideoSplicerFactory;
import com.mygdx.game.VideoHandler.VideoSplicerUriLegacy;

import java.io.FileInputStream;

import static com.mygdx.game.GalleryScreen.channel_ForeGround_ID;

public class ForegroundService extends Service {
    public String CHANNEL_ID = "ForegroundService";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startID) {
        //String videoPath = intent.getStringExtra("videoPath");
        String videoPath = intent.getStringExtra("DING");
        String TAG = "SOEP";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "TEST", importance);
        notificationChannel.setDescription("WERKT HET AL?");
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationManager.createNotificationChannel(notificationChannel);
        Intent currentResultIntent = new Intent(this, CurrentResultActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, currentResultIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, "ForeGroundService")
                .setContentTitle("ForegroundService")
                .setContentText(videoPath)
                .setSmallIcon(R.drawable.testplaatje)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .setChannelId(CHANNEL_ID)
                .build();
        Uri otherUri = intent.getData();
        startForeground(7, notification);



        try {
            VideoSplicer videoSplicer = VideoSplicerFactory.getVideoSplicer(otherUri, getApplication());
            DebugLog.log(Integer.toString(videoSplicer.getFrameCount()));
            DebugLog.log(Integer.toString(videoSplicer.getFramesProcessed()));


            Session session = new Session(otherUri, getApplication(), videoSplicer);


            session.runVideo();

        } catch (InvalidVideoSplicerType invalidVideoSplicerType) {
            Log.e(TAG, invalidVideoSplicerType.getMessage());
        }


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}