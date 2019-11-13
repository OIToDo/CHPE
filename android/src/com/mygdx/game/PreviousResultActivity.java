package com.mygdx.game;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mygdx.game.Analysis.Data;
import com.mygdx.game.Analysis.Action;
import com.mygdx.game.Analysis.Analysis;
import com.mygdx.game.Analysis.DatabaseData;
import com.mygdx.game.persistance.PersistenceClient;

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
