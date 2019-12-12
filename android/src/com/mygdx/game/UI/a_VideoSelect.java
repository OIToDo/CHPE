package com.mygdx.game.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import com.mygdx.game.R;

public class a_VideoSelect extends AppCompatActivity {
    final int SELECT_VIDEO_REQUEST = 1;

    Uri videoUri;
    Dialog dialog;
    VideoView videoView;
    ImageButton b_selectVideo;
    MediaController mediaController;
    boolean videoIsSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_video_select);
        AAL.setTitleBar(getWindow());
        dialog = new Dialog(this);

        b_selectVideo = findViewById(R.id.select_button);
        b_selectVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVideoGallery();
            }
        });
    }

    public void showDialog() {
        dialog.setContentView(R.layout.video_popup);
        videoView = dialog.findViewById(R.id.videoView2);
        Button b_OK = dialog.findViewById(R.id.ok_button);
        Button b_Cancel = dialog.findViewById(R.id.cancel_button);
        b_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
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
        videoView.setVideoURI(videoUri);
        videoIsSelected = true;

        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        ((ViewGroup) mediaController.getParent()).removeView(mediaController);

        ((FrameLayout) dialog.findViewById(R.id.videoViewWrapper))
                .addView(mediaController);
        mediaController.setVisibility(View.VISIBLE);
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
                Toast toast = Toast.makeText(getApplicationContext(), "nee", Toast.LENGTH_LONG);
                toast.show();
                b_selectVideo.setVisibility(View.GONE);
                showDialog();
                initializePlayer(data.getData().toString());
            }
        }
    }
}
