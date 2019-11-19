package com.mygdx.game;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class GalleryScreen extends AppCompatActivity implements Serializable {
    //Button declaration for on-screen buttons.
    Button videoSelectButton;
    Button homeScreenButton;
    Button startAnalysis;
    //VideoView and MediaController declaration for the embedded video view with media control UI.
    VideoView videoView;
    MediaController mediaController;
    //String declaration to copy video-file path with.
    String selectedVideoPath;
    //Int declaration to enter video function with.
    private final int SELECT_VIDEO_REQUEST = 1;
    //Uri for the selectable video from the user.
    Uri videoUri;
    //Toast message to confirm that the user started the analysis process.
    Toast toast;
    //boolean to check if a video has been selected.
    boolean videoIsSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_screen);
        videoIsSelected = false;
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
                startNeuralNetwork(videoUri);
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
        intent.putExtra("return-data", true);
        startActivityForResult(intent, SELECT_VIDEO_REQUEST);
    }
    //Checks if videopath is selected, shows according toast message and starts an activity.
    public void startNeuralNetwork(Uri uri) {
        DebugLog.log(uri.toString());
        if(videoIsSelected) {
            Intent intent = new Intent(this, ProcessingScreenActivity.class);
            toast = Toast.makeText(getApplicationContext(), "Started video analysis, this could take a while", Toast.LENGTH_LONG);

            toast.show();
            enqueueWork();
            startActivity(intent);
        }
        else {
            toast = Toast.makeText(getApplicationContext(), "Failed to load video path", Toast.LENGTH_LONG);
            toast.show();
        }
    }
    //Initializes the VideoView player with the selected video.
    //@param String name
    public void initializePlayer(String name ) {
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
                selectedVideoPath = data.getData().toString();
                initializePlayer(data.getData().toString());
            }
        }
    }
    //Sends works to the AnalysisService class
    public void enqueueWork() {
        String input = selectedVideoPath;
        Intent serviceIntent = new Intent(this, AnalysisService.class);
        serviceIntent.putExtra("inputExtra", input);
        AnalysisService.enqueueWork(this, serviceIntent);
    }

}