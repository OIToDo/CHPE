package com.mygdx.game;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.Simulation.MyGdxGame;
/**
 * Class where the user is brought the moment the neural network service starts
 * @author Gianluca Piccardo
 */
public class ProcessingScreenActivity extends AndroidApplication {
    /**
     * Declaration of Views and Buttons
     */
    View libGDXView;
    View embeddedView;
    Button startButton;
    Button stopButton;
    /**
     * Constructor
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing_screen);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        /**
         * Initialization of Views and Buttons
         */
        libGDXView = initializeForView(new MyGdxGame(), config);
        embeddedView = findViewById(R.id.processingView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        /**
         * Android Version control for colored status bar
         */
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(0.902f,0.188f,0.157f));
        }
        else {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0);
        }
    }
}
