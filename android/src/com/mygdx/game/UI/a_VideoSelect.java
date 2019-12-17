package com.mygdx.game.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.mygdx.game.DebugLog;
import com.mygdx.game.R;

import org.w3c.dom.Text;

public class a_VideoSelect extends AppCompatActivity {
    public static final String[] allPermissions = new String[] {
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.WAKE_LOCK,
            Manifest.permission.VIBRATE
    };
    Uri videoUri;
    Dialog dialog;
    String filepath;
    VideoView videoView;
    ImageButton b_selectVideo;
    MediaController mediaController;
    boolean videoIsSelected = false;
    final int SELECT_VIDEO_REQUEST = 1;
    private static final int PERMISSION_CODE = 2;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(AAL.permissionsGranted(getApplicationContext(), allPermissions)) {
            openVideoGallery();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_video_select);
        AAL.setTitleBar(getWindow());

        dialog = new Dialog(this);
        mediaController = new MediaController(a_VideoSelect.this) {
            @Override
            public void show(int timeout) {
                super.show(timeout);
                setVisibility(VISIBLE);
            }

            @Override
            public void hide() {
                super.hide();
                setVisibility(INVISIBLE);
            }
        };

        b_selectVideo = findViewById(R.id.select_button);
        b_selectVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if all permissions were granted already we can just open the video gallery
                // if not, request the missing permissions
                if(AAL.permissionsGranted(getApplicationContext(), allPermissions)) {
                    DebugLog.log("logger: Opening gallery");
                    openVideoGallery();
                } else {
                    DebugLog.log("logger: Not opening gallery");
                    AAL.requestPermissions(getApplicationContext(), a_VideoSelect.this, allPermissions);
                }
            }
        });
    }

    public void showDialog() {
        dialog.setContentView(R.layout.video_popup);
        videoView = dialog.findViewById(R.id.videoView2);
        Button b_OK = dialog.findViewById(R.id.ok_button);
        Button b_Cancel = dialog.findViewById(R.id.cancel_button);
        TextView textView = dialog.findViewById(R.id.textView4);
        textView.setText(filepath);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        b_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), a_Loading.class);
                startActivity(intent);
            }
        });
        b_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void initializePlayer(String name) {
        videoUri = Uri.parse(name);
        //filepath = videoUri.getPath();
        videoView.setVideoURI(videoUri);
        videoIsSelected = true;
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        mediaController.hide();
                        videoView.setMediaController(mediaController);
                        mediaController.setAnchorView(videoView);
                        ((ViewGroup) mediaController.getParent()).removeView(mediaController);
                        ((FrameLayout) dialog.findViewById(R.id.framertje))
                                .addView(mediaController);
                    }
                });
            }
        });
        videoView.setVideoURI(videoUri);
        videoView.start();
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
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, SELECT_VIDEO_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_VIDEO_REQUEST) {
                Toast toast = Toast.makeText(getApplicationContext(), data.getData().getPath(), Toast.LENGTH_LONG);
                toast.show();
                filepath = data.getData().getPath();
                showDialog();
                initializePlayer(data.getData().toString());
            }
        }
    }
}
