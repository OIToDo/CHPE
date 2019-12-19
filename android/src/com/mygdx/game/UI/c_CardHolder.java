package com.mygdx.game.UI;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mygdx.game.R;

public class c_CardHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView title, description;

    public c_CardHolder(@NonNull View itemView) {
        super(itemView);

        this.imageView = itemView.findViewById(R.id.resultCardImage);
        this.title = itemView.findViewById(R.id.resultCardTitle);
        this.description = itemView.findViewById(R.id.resultCardDescription);
    }

}
