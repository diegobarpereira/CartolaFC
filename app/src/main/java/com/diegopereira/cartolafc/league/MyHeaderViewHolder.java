package com.diegopereira.cartolafc.league;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.R;

public class MyHeaderViewHolder extends RecyclerView.ViewHolder {
    public final TextView league_name;

    public MyHeaderViewHolder(View itemView) {
        super(itemView);
        league_name = (TextView)itemView.findViewById(R.id.league_name);

    }
}