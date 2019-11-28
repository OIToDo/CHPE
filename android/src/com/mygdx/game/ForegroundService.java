package com.mygdx.game;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.URLUtil;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.mygdx.game.PoseEstimation.Session;
import com.mygdx.game.VideoHandler.VideoSplicerUri;

import java.util.HashMap;

import static com.mygdx.game.GalleryScreen.channel_ForeGround_ID;

public class ForegroundService extends Service {
    public String CHANNEL_ID = "ForegroundService";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startID) {
        String videoPath = intent.getStringExtra("videoPath");
        String TAG = "IN FOREGROUND BITCHES";

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
        startForeground(10, notification);


        stopSelf();

        return START_NOT_STICKY;
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;

        String[] proj = {MediaStore.Images.Media.DATA};
        cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);


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
