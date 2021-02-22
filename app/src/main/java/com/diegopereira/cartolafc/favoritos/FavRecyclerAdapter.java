package com.diegopereira.cartolafc.favoritos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.diegopereira.cartolafc.FavoritosActivity;
import com.diegopereira.cartolafc.R;
import com.diegopereira.cartolafc.groups.DatabaseHelper;
import com.diegopereira.cartolafc.groups.Input;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class FavRecyclerAdapter extends RecyclerView.Adapter<FavRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Input> teste;

    private DatabaseHelper database;

    public FavRecyclerAdapter(Context context, List<Input> teste) {
        this.context=context;
        this.teste=teste;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.favlist_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Input item = teste.get(position);
        DecimalFormat formatter = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.US));


        Glide.with(context)
                .load(item.getUrlEscudoPng())
                .into(viewHolder.favimg_player);
        viewHolder.fav_title.setText(item.getNome());
        //viewHolder.fav_points.setText(formatter.format(item.get(position).getPontos()));
        //viewHolder.fav_ultima.setText(formatter.format(item.get(position).getUltima()));




    }

    @Override
    public int getItemCount() {
        return teste.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        AppCompatImageView favimg_player, button_add;
        TextView fav_title, fav_points, fav_ultima;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            favimg_player=(AppCompatImageView) itemView.findViewById(R.id.favimg_player);
            button_add=(AppCompatImageView) itemView.findViewById(R.id.button_add);
            fav_title=(TextView)itemView.findViewById(R.id.fav_title);
            fav_points=(TextView)itemView.findViewById(R.id.fav_points);
            fav_ultima=(TextView)itemView.findViewById(R.id.fav_ultima);
        }
    }


}
