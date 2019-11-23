package com.mygdx.game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.Simulation.MyGdxGame;

public class HomeScreen extends AndroidApplication {
    //Button declaration of on-screen buttons.
    Button previousResultScreenButton;
    Button galleryScreenButton;
    //TIJDELIJKE KNOPPEN/////////
    Button JUMP;
    //View Declaration of embedded on-screen libGDX views.
    View libGDXView;
    View embeddedView;

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        libGDXView = initializeForView(new MyGdxGame(), config);
        embeddedView = findViewById(R.id.gameView);

        previousResultScreenButton = findViewById(R.id.previousResults);
        galleryScreenButton = findViewById(R.id.galleryScreenButton);

        JUMP = findViewById(R.id.JUMP);

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

        JUMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJUMP();
            }
        });

        replaceView(embeddedView, libGDXView);
        HomeScreen.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return HomeScreen.context;
    }

    public void openPreviousResultScreen() {
        Intent intent = new Intent(this, PreviousResultActivity.class);
        startActivity(intent);
    }

    public void openJUMP() {
        Intent intent = new Intent(this, CurrentResultActivity.class);
        startActivity(intent);
    }

    public void openLoadVideoScreen() {
        Intent intent = new Intent(this, GalleryScreen.class);
        startActivity(intent);
    }

    private void replaceView(View oldView, View newView) {
        ViewGroup parent = (ViewGroup) oldView.getParent();
        ViewGroup.LayoutParams oldParameters = oldView.getLayoutParams();
        newView.setLayoutParams(oldParameters);
        if (parent == null) {
            return;
        }
        int index = parent.indexOfChild(oldView);
        parent.removeViewAt(index);
        parent.addView(newView, index);
    }
}
