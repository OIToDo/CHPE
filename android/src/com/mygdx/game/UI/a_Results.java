package com.mygdx.game.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mygdx.game.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class a_Results extends AppCompatActivity {
    ImageView b_Home;
    RecyclerView cardList;
    c_CardAdapter cardAdapter;
    LinearLayoutManager layoutManager;

    @Override
    public void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_results);
        AAL.setTitleBar(getWindow());

        b_Home = findViewById(R.id.homeButton);
        b_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(a_Results.this, a_Home.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        cardList = findViewById(R.id.resultCardList);
        layoutManager = new LinearLayoutManager(this);
        cardList.setLayoutManager(layoutManager);
        cardAdapter = new c_CardAdapter(this, getCardList());
        cardList.setAdapter(cardAdapter);
        cardAdapter.notifyDataSetChanged();
    }

    private ArrayList<c_ResultCard> getCardList() {
        ArrayList<c_ResultCard> cards = new ArrayList<>();

        c_ResultCard card = new c_ResultCard();
        card.setTitle("Hands Above Head");
        card.setDescription("Did you keep your hands above your head? @4:31");
        card.setImage(R.drawable.ic_launcher);
        for (int i = 0; i < 10; i++) {
            cards.add(card);
        }

        return cards;
    }
}
