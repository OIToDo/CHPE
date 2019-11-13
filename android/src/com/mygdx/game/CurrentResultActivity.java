package com.mygdx.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mygdx.game.Analysis.Action;
import com.mygdx.game.Analysis.Analysis;
import com.mygdx.game.Analysis.Data;
import com.mygdx.game.Analysis.DatabaseData;
import com.mygdx.game.persistance.PersistenceClient;

import java.util.ArrayList;
import java.util.HashMap;

public class CurrentResultActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> feedback = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_result);

        Context context = getApplicationContext();
        Data data = new DatabaseData(PersistenceClient.getInstance(context).getAppDatabase());
        Analysis program = new Analysis(data);
        HashMap<Action, Boolean> results = program.detect();

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, feedback);

        for(HashMap.Entry<Action, Boolean> pair : results.entrySet()) {
            if(pair.getValue()) {
                feedback.add(pair.getKey().getName() + " happened.");
            } else {
                feedback.add(pair.getKey().getName() + " didn't happen.");
            }
        }

        listView.setAdapter(adapter);
    }
}
