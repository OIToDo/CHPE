package com.mygdx.game;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class CurrentResultActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> feedback = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_result);
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, feedback);
        feedback.add("handen X");
        feedback.add("voeten X");
        feedback.add("hoofd X");
        feedback.add("asaaf X");
        feedback.add("hoofd X");
        feedback.add("okasm X");
        feedback.add("hoofd X");
        feedback.add("hoofd X");
        feedback.add("manso X");
        feedback.add("hoofd X");
        feedback.add("hoofd X");
        feedback.add("hoofd X");
        feedback.add("handen X");
        feedback.add("voeten X");
        feedback.add("hoofd X");
        feedback.add("asaaf X");
        feedback.add("hoofd X");
        feedback.add("okasm X");
        feedback.add("hoofd X");
        feedback.add("hoofd X");
        feedback.add("hoofd X");
        feedback.add("manso X");
        feedback.add("hoofd X");
        feedback.add("hoofd X");
        feedback.add("hoofd X");
        listView.setAdapter(adapter);
    }
}
