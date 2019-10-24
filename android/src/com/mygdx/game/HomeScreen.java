package com.mygdx.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class HomeScreen extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        View libGDXView = initializeForView(new MyGdxGame(), config);
        View embeddedView = findViewById(R.id.gameView);

        Button previousResultScreenButton = findViewById(R.id.previousResults);
        Button galleryScreenButton = findViewById(R.id.galleryScreenButton);

        previousResultScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPreviousResultScreen();
            }
        });

        galleryScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoadVideoScreen();
            }
        });
        replaceView(embeddedView, libGDXView);
    }

    public void openPreviousResultScreen(){
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }

    public void openLoadVideoScreen(){
        Intent intent = new Intent(this, GalleryScreen.class);
        startActivity(intent);
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
