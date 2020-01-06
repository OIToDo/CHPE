package com.mygdx.game.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mygdx.game.R;

import java.util.ArrayList;

public class c_CardAdapter extends RecyclerView.Adapter<c_CardHolder> {

    Context context;
    ArrayList<c_ResultCard> cards;

    public c_CardAdapter(Context context, ArrayList<c_ResultCard> cards) {
        this.context = context;
        this.cards = cards;
    }

    @NonNull
    @Override
    public c_CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_resultcard, parent, false);
        return new c_CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull c_CardHolder holder, int i) {
        holder.title.setText(cards.get(i).getTitle());
        holder.description.setText(cards.get(i).getDescription());
        holder.imageView.setImageResource(cards.get(i).getImage());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
