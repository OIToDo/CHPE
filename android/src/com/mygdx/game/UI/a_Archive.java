package com.mygdx.game.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mygdx.game.R;

public class a_Archive extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_archive);
        AAL.setTitleBar(getWindow());
    }
}
