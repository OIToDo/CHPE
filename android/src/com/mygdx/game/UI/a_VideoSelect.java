package com.mygdx.game.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.mygdx.game.R;

public class a_VideoSelect extends AppCompatActivity {
    Button b_selectVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_video_select);
        AAL.setTitleBar(getWindow());
    }
}
