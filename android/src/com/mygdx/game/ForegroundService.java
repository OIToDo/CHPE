package com.mygdx.game;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.mygdx.game.PoseEstimation.Session;
import com.mygdx.game.VideoHandler.VideoSplicerUri;

public class ForegroundService extends Service {
    public String CHANNEL_ID = "ForegroundService";
    public static final int NOTIF_ID = 7;
    public Notification notification;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startID) {
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
        this.notification = new NotificationCompat.Builder(this, "ForeGroundService")
                .setContentTitle("ForegroundService")
                .setContentText(videoPath)
                .setSmallIcon(R.drawable.testplaatje)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .setChannelId(CHANNEL_ID)
                .build();

        startForeground(NOTIF_ID, this.notification);
        new Thread(new Runnable() {
            public void run() {
                //String videoPath = intent.getStringExtra("videoPath");

                Uri otherUri = intent.getData();
                VideoSplicerUri videoSplicerUri = new VideoSplicerUri(otherUri, getApplication());
                Session session = new Session(otherUri, getApplication(), videoSplicerUri);
                session.runVideo();
                session.normaliseData();

            }
        }).start();
        stopSelf();

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