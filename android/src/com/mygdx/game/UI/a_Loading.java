package com.mygdx.game.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mygdx.game.DebugLog;
import com.mygdx.game.ForegroundService;
import com.mygdx.game.ProcessingScreenActivity;
import com.mygdx.game.R;

public class a_Loading extends AppCompatActivity {
    int progress = 0;
    final int MAX = 25000;
    ConstraintLayout constraintLayout;
    AnimationDrawable animationDrawable;
    TextView progressText;
    ProgressBar progressBar;
    Handler handler = new Handler();
    Button b_Results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        AAL.setTitleBar(getWindow());
        constraintLayout = findViewById(R.id.constraint_loading);
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(1000);
        animationDrawable.start();
        b_Results = findViewById(R.id.resultsButton);
        b_Results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Intent intent = new Intent(context, a_Results.class);
                startActivity(intent);
            }
        });
        b_Results.setVisibility(View.INVISIBLE);

        // get the progress bar and set its precision
        progressText = findViewById(R.id.progressTextView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(MAX);

        ForegroundService.setWork(new Runnable() {
            @Override
            public void run() {
                while (progress < MAX) {
                    // use a handler to post the progress back to the UI thread as text
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String txt = progress / 100 + "%";
                            progressText.setText(txt);
                        }
                    });
                    // update the progress and sleep for 200 ns
                    progressBar.setProgress(progress);
                    try {
                        Thread.sleep(0, 4000);
                    } catch (Exception e) {
                        DebugLog.log(e.getMessage());
                    }
                    progress += 1;
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        notifyUser();
                        b_Results.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        startService();
    }

    public void notifyUser() {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        int importance = NotificationManager.IMPORTANCE_HIGH;
        final String CHANNEL_ID = "notifyUser";
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(getApplicationContext(), a_Results.class), PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "notifyUser", importance);
        notificationChannel.setDescription("Your video has been processed");
        notificationChannel.getLockscreenVisibility();
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationManager.createNotificationChannel(notificationChannel);

        Notification notification = new NotificationCompat.Builder(this, "ForeGroundService")
                .setContentTitle("Prepper")
                .setContentText("Neural Network is done.")
                .setSmallIcon(R.drawable.testplaatje)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setColorized(true)
                .setColor(0xff0000ff)
                .setLights(0xff0000ff, 1111, 1111)
                .setChannelId(CHANNEL_ID)
                .build();
        notificationManager.notify(12, notification);
    }

    public void startService() {
        Toast toast = Toast.makeText(getApplicationContext(), "Started video analysis, this could take a while", Toast.LENGTH_LONG);
        toast.show();
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        ContextCompat.startForegroundService(this, serviceIntent);
    }
}
