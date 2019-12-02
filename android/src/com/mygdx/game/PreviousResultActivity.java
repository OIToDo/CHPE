package com.mygdx.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PreviousResultActivity extends AppCompatActivity {
//  Button to return the user to the home screen.
    Button homeScreenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_result);

        homeScreenButton = findViewById(R.id.homeScreenButton);
        homeScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreviousResultActivity.this, HomeScreen.class);
                startActivity(intent);
            }
        });
    }
}
