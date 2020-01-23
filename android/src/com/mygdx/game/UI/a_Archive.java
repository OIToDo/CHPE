package com.mygdx.game.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mygdx.game.R;

/**
 * Archive class that handles the presentation archive.
 * It contains the previous sessions and allows the user to revisit or delete previous presentations.
 */
public class a_Archive extends AppCompatActivity {

    /**
     * Android Activity constructor.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_archive);
        AAL.setTitleBar(getWindow());
    }
}
