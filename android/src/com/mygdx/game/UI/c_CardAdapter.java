package com.mygdx.game.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mygdx.game.R;

import java.util.ArrayList;

/**
 * Adaoter ckass that extends a recycler view of android cards.
 */
public class c_CardAdapter extends RecyclerView.Adapter<c_CardHolder> {
    /**
     * Context of the application.
     */
    Context context;

    /**
     * Dynamic collection of cards.
     */
    ArrayList<c_ResultCard> cards;

    /**
     * Constructor.
     * @param context Application context.
     * @param cards Cards to add.
     */
    public c_CardAdapter(Context context, ArrayList<c_ResultCard> cards) {
        this.context = context;
        this.cards = cards;
    }

    /**
     * Android function override, deals with inflating the view on creation.
     * @param parent
     * @param viewType
     * @return a new card holder.
     */
    @NonNull
    @Override
    public c_CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_resultcard, parent, false);
        return new c_CardHolder(view);
    }

    /**
     * Android function override, describes a single card at given index.
     * @param holder card collection.
     * @param i Index into the card collection.
     */
    @Override
    public void onBindViewHolder(@NonNull c_CardHolder holder, int i) {
        holder.title.setText(cards.get(i).getTitle());
        holder.description.setText(cards.get(i).getDescription());
        holder.imageView.setImageResource(cards.get(i).getImage());
    }

    /**
     * Gets the number of cards.
     * @return int nr of cards.
     */
    @Override
    public int getItemCount() {
        return cards.size();
    }
}
