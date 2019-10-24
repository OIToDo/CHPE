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
    Button videoSelectButton;
    Button homeScreenButton;
    VideoView videoView;
    MediaController mediaController;
    String selectedVideoPath;
    private final int SELECT_VIDEO_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_screen);

        homeScreenButton = findViewById(R.id.homeScreenButton);
        videoSelectButton = findViewById(R.id.videoGalleryScreenButton);
        videoView = findViewById(R.id.videoView);
        mediaController = new MediaController(this);

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

    public Uri getMedia(String mediaName) {
        return Uri.parse("android/resource://" + getPackageName() + "raw/" + mediaName);
    }

    public void initializePlayer(String name ) {
        Uri videoUri = getMedia(name);
        videoView.setVideoURI(videoUri);
        videoView.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_VIDEO_REQUEST) {
                Uri selectedVideoUri = data.getData();
                selectedVideoPath = selectedVideoUri.getPath();
                initializePlayer(selectedVideoPath);
            }
/*            if (requestCode == SELECT_IMAGE_REQUEST) {
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        }
    }
}
    /*
        imageSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, SELECT_IMAGE_REQUEST);
            }
        });*/
/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/


