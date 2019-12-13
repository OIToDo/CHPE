package com.mygdx.game.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mygdx.game.DebugLog;
import com.mygdx.game.R;

public class a_Loading extends AppCompatActivity {
    int progress = 0;
    final int MAX = 10000;
    TextView progressText;
    ProgressBar progressBar;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        AAL.setTitleBar(getWindow());

        // get the progress bar and set its precision
        progressText = findViewById(R.id.progressTextView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(MAX);

        // launch a thread that increments progress every millisecond
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(progress < MAX) {
                    // use a handler to post the progress back to the UI thread as text
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String txt = progress/100 + "%";
                            progressText.setText(txt);
                        }
                    });
                    // update the progress and sleep for 200 ns
                    progressBar.setProgress(progress);
                    try {
                        Thread.sleep(0, 200);
                    } catch(Exception e) {
                        DebugLog.log(e.getMessage());
                    }
                    progress += 1;
                }

            }
        });
        t.start();
    }
}
