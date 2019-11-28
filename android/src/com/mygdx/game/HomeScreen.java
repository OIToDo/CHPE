package com.mygdx.game;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Analysis.JSONLoader;
import com.mygdx.game.persistance.PersistenceClient;

import com.mygdx.game.Simulation.MyGdxGame;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.Buffer;

public class HomeScreen extends AndroidApplication {
    //Button declaration of on-screen buttons.
    Button previousResultScreenButton;
    Button galleryScreenButton;
    Button JUMP;
    //View Declaration of embedded on-screen libGDX views.
    View libGDXView;
    View embeddedView;
    JSONLoader loader;

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        PersistenceClient.getInstance(getApplicationContext());
        AssetManager am = getApplicationContext().getAssets();
        InputStream is = null;
        try {
            is = am.open("data/wave.json");
        } catch (IOException e) {
            DebugLog.log("Unable to load asset norm json");
        }
        Reader r = new InputStreamReader(is);

        loader = new JSONLoader(r);
        DebugLog.log(loader.toString());
        DebugLog.log(String.valueOf(loader.getFrameCount()));

        MockData mockData = new MockData(PersistenceClient.getInstance(getApplicationContext()).getAppDatabase(), loader.getArray());
//        mockData.executeInserts();

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

    public static Context getAppContext(){
        return HomeScreen.context;
    }

    public void openPreviousResultScreen(){
        Intent intent = new Intent(this, PreviousResultActivity.class);
        startActivity(intent);
    }

    public void openJUMP(){
        Intent intent = new Intent(HomeScreen.this, CurrentResultActivity.class);
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
