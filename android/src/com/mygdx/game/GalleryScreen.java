package com.mygdx.game;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;

import java.io.IOException;

public class GalleryScreen extends AppCompatActivity {
    Button imageSelectButton;
    ImageView imageView;
    private int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_screen);
        Button homeScreenButton = findViewById(R.id.homeScreenButton);
        imageSelectButton = findViewById(R.id.galleryScreenButton);
        imageView =  findViewById(R.id.galleryView);
        homeScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeScreen();
            }
        });
        imageSelectButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

                startActivityForResult(intent, 1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
    }

    public void openHomeScreen(){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}
