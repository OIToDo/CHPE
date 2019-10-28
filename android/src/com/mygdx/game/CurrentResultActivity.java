package com.mygdx.game;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class CurrentResultActivity extends AppCompatActivity {
    private TextSwitcher textSwitcher;
    Button nextAdviceButton;
    private int stringIndex = 0;
    private String[] row = {"Handen te lang hoog", "Te lang geen handbewegingen", "Handen niet gevonden, in je zak?", "Draai niet te vaak of te lang weg van je publiek"};
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_result);


        textSwitcher = findViewById(R.id.textSwitcher);
        nextAdviceButton = findViewById(R.id.nextAdviceButton);
        nextAdviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stringIndex == row.length - 1) {
                    stringIndex = 0;
                    textSwitcher.setText(row[stringIndex]);
                } else {
                    textSwitcher.setText(row[++stringIndex]);
                }
            }
        });
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                textView = new TextView(CurrentResultActivity.this);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(22);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                return textView;
            }
        });
        textSwitcher.setText(row[stringIndex]);
    }
}
