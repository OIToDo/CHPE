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
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.IOException;
import java.io.Serializable;

public class GalleryScreen extends AppCompatActivity implements Serializable {
    //Button declaration for on-screen buttons.
    Button videoSelectButton;
    Button homeScreenButton;
    //VideoView and MediaController declaration for the embedded video view with media control UI.
    VideoView videoView;
    MediaController mediaController;
    //String declaration to copy video-file path with.
    String selectedVideoPath;
    String extraVideoPath;
    //Int declaration to enter video function with.
    private final int SELECT_VIDEO_REQUEST = 1;
    Uri extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_screen);

        homeScreenButton = findViewById(R.id.homeScreenButton);
        videoSelectButton = findViewById(R.id.videoGalleryScreenButton);

        videoView = findViewById(R.id.videoView);

        extraVideoPath = "android.resource://" + getPackageName() + "/" + R.raw.example;
        extra = Uri.parse(extraVideoPath);
        videoView.setVideoURI(extra);
        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        homeScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeScreen();
            }
        });

        videoSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVideoGallery();
            }
        });
    }

    public void openHomeScreen() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

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



    public void initializePlayer(String name ) {
        Uri videoUri = Uri.parse(name);
        videoView.setVideoURI(videoUri);
        videoView.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_VIDEO_REQUEST) {
                Uri selectedVideoUri = data.getData();
                selectedVideoPath = "android.resource://" + getPackageName() + "/" + selectedVideoUri.getPath();
                initializePlayer(selectedVideoPath);
            }
        }
    }
}