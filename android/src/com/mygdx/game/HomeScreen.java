package com.mygdx.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.room.Room;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.persistance.AppDatabase;
import com.mygdx.game.persistance.PersistenceClient;

public class HomeScreen extends AndroidApplication {
    //Button declaration of on-screen buttons.
    Button previousResultScreenButton;
    Button galleryScreenButton;
    /////////////////////////////
    //TIJDELIJKE KNOPPEN/////////
    Button JUMP;
    Button processWindow;
    /////////////////////////
    //View Declaration of embedded on-screen libGDX views.
    View libGDXView;
    View embeddedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        PersistenceClient.getInstance(getApplicationContext());

        MockData mockData = new MockData(PersistenceClient.getInstance(getApplicationContext()).getAppDatabase());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        libGDXView = initializeForView(new MyGdxGame(), config);
        embeddedView = findViewById(R.id.gameView);

        previousResultScreenButton = findViewById(R.id.previousResults);
        galleryScreenButton = findViewById(R.id.galleryScreenButton);
        JUMP = findViewById(R.id.JUMP);
        processWindow = findViewById(R.id.WindowProcess);
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
        processWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProcess();
            }
        });
        replaceView(embeddedView, libGDXView);
    }

    public void openPreviousResultScreen(){
        Intent intent = new Intent(this, PreviousResultActivity.class);
        startActivity(intent);
    }

    public void openJUMP(){
        Intent intent = new Intent(HomeScreen.this, CurrentResultActivity.class);
        startActivity(intent);
    }

    public void openProcess() {
        Intent intent = new Intent(this, ProcessingScreenActivity.class);
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
