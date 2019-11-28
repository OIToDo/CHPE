package com.mygdx.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.badlogic.gdx.backends.android.AndroidApplication;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class GalleryScreen extends AppCompatActivity implements Serializable {
    //Button declaration for on-screen buttons.
    Button videoSelectButton;
    Button homeScreenButton;
    Button startAnalysis;
    //VideoView and MediaController declaration for the embedded video view with media control UI.
    VideoView videoView;
    MediaController mediaController;
    //Int declaration to enter video function with.
    private final int SELECT_VIDEO_REQUEST = 1;
    //Static string for notification channel
    public static final String channel_ForeGround_ID = "Testerino";
    //Uri for the selectable video from the user.
    Uri videoUri;
    //Toast message to confirm that the user started the analysis process.
    Toast toast;
    //boolean to check if a video has been selected.
    boolean videoIsSelected;
    //Channel_ID name for notification
    public static final String Channel_ID = "NeuralServiceChannel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_screen);
        //Check if the path is selected by the user
        videoIsSelected = false;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // only for gingerbread and newer versions
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(0.902f,0.188f,0.157f));
        }
        else {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0);
        }
        //Initializing buttons
        homeScreenButton = findViewById(R.id.homeScreenButton);
        videoSelectButton = findViewById(R.id.videoGalleryScreenButton);
        startAnalysis = findViewById(R.id.startAnalysis);
        //preparing video environment
        videoView = findViewById(R.id.videoView);
        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        //Instantiates homeScreenButton with the correct function.
        homeScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeScreen();
            }
        });
        //Instantiates videoSelectButton with the correct function.
        videoSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVideoGallery();
            }
        });
        //Instantiates startAnalysisButton with the correct function.
        startAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startNeuralNetwork(videoUri);
                startService();
            }
        });
    }
    //Starts a new Intent to open up the home-screen.
    public void openHomeScreen() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
    //Starts a new Intent to open up the default Android Gallery.
    public void openVideoGallery() {
        Intent intent;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        } else {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.INTERNAL_CONTENT_URI);
        }
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, SELECT_VIDEO_REQUEST);
    }

    public void startService() {
        if(videoIsSelected) {
            //Intent intent = new Intent(this, ProcessingScreenActivity.class);
            toast = Toast.makeText(getApplicationContext(), "Started video analysis, this could take a while", Toast.LENGTH_LONG);
            toast.show();
            Intent serviceIntent = new Intent(this, ForegroundService.class);

            serviceIntent.putExtra("DING", videoUri.toString());
            serviceIntent.setData(videoUri);
            //serviceIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //serviceIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            ContextCompat.startForegroundService(this, serviceIntent);
        }
        else {
            toast = Toast.makeText(getApplicationContext(), "Failed to load video path", Toast.LENGTH_LONG);
            toast.show();
        }
    }
    //Initializes the VideoView player with the selected video.
    //@param String name
    public void initializePlayer(String name) {
        videoUri = Uri.parse(name);
        videoView.setVideoURI(videoUri);
        videoView.start();
        videoIsSelected = true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_VIDEO_REQUEST) {
                initializePlayer(data.getData().toString());
            }
        }
    }

    public void stopService() {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        stopService(serviceIntent);
    }

    public void startNeuralNetwork(Uri uri) {
        //Checks if videopath is selected, shows according toast message and starts an activity.
        if(videoIsSelected) {
            Intent intent = new Intent(this, ProcessingScreenActivity.class);
            toast = Toast.makeText(getApplicationContext(), "Started video analysis, this could take a while", Toast.LENGTH_LONG);
            toast.show();
            startActivity(intent);
        }
        else {
            toast = Toast.makeText(getApplicationContext(), "Failed to load video path", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}