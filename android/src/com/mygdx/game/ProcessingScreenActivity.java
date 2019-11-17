package com.mygdx.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import com.mygdx.game.Simulation.MyGdxGame;

public class ProcessingScreenActivity extends AndroidApplication {
    //Declaration of Views to use in processing screen.
    View libGDXView;
    View embeddedView;
    EditText editText;
    Button startButton;
    Button stopButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing_screen);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        editText = findViewById(R.id.testText);
        libGDXView = initializeForView(new MyGdxGame(), config);
        embeddedView = findViewById(R.id.processingView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        replaceView(embeddedView, libGDXView);
    }



    private void replaceView(View oldView, View newView) {
        ViewGroup parent = (ViewGroup)oldView.getParent();
        ViewGroup.LayoutParams oldParameters = oldView.getLayoutParams();
        newView.setLayoutParams(oldParameters);
        if(parent == null) {
            return;
        }
        int index = parent.indexOfChild(oldView);
        parent.removeViewAt(index);
        parent.addView(newView, index);
    }
}
