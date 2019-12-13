package com.mygdx.game.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mygdx.game.DebugLog;
import com.mygdx.game.ForegroundService;
import com.mygdx.game.ProcessingScreenActivity;
import com.mygdx.game.R;

public class a_Loading extends AppCompatActivity {
    int progress = 0;
    final int MAX = 10000;
    TextView progressText;
    ProgressBar progressBar;
    Handler handler = new Handler();
    Button b_Results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        AAL.setTitleBar(getWindow());

        b_Results = findViewById(R.id.resultsButton);
        b_Results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Intent intent = new Intent(context, a_Results.class);
                startActivity(intent);
            }
        });
        b_Results.setVisibility(View.INVISIBLE);

        // get the progress bar and set its precision
        progressText = findViewById(R.id.progressTextView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(MAX);

        ForegroundService.setWork(new Runnable() {
            @Override
            public void run() {
                while (progress < MAX) {
                    // use a handler to post the progress back to the UI thread as text
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String txt = progress / 100 + "%";
                            progressText.setText(txt);
                        }
                    });
                    // update the progress and sleep for 200 ns
                    progressBar.setProgress(progress);
                    try {
                        Thread.sleep(0, 4000);
                    } catch (Exception e) {
                        DebugLog.log(e.getMessage());
                    }
                    progress += 1;
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        b_Results.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        startService();
    }

    public void startService() {
        Toast toast = Toast.makeText(getApplicationContext(), "Started video analysis, this could take a while", Toast.LENGTH_LONG);
        toast.show();
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        ContextCompat.startForegroundService(this, serviceIntent);
    }
}
