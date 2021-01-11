package com.diegopereira.cartolafc.league;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.diegopereira.cartolafc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class RecyclerViewAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Times> list;

    public RecyclerViewAdapter(Context context, List<Times> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.leaguelist_item,parent,false);
        return new RecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        Double diff = 0.00;
        DecimalFormat formatter = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.US));

        for (int i = 0; i < position; i++) {
            holder.dif.setVisibility(View.VISIBLE);

            diff = list.get(position).getPontos().getCampeonato()-list.get(0).getPontos().getCampeonato();

        }


        if( position == 0) {
            holder.dif.setVisibility(View.INVISIBLE);
        }

        holder.dif.setText(String.valueOf(formatter.format(diff)));

        String name = list.get(position).getNome();
        holder.name.setText(name);

        System.out.println(list.get(position).getRanking().getCampeonato());
        Integer posicao = list.get(position).getRanking().getCampeonato();
        String pos = String.valueOf(posicao);
        holder.pos.setText(pos);

        Double pontos = list.get(position).getPontos().getCampeonato();
        String points = String.valueOf(pontos);
        holder.points.setText(points);


        String symbol = list.get(position).getUrl_escudo_png();
        Picasso.with(context)
                .load(symbol)
                .resize(70, 70)
                .into(holder.img_player);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private TextView name, pos, points, dif;
        private AppCompatImageView img_player;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            name=(TextView)itemView.findViewById(R.id.title);
            pos=(TextView)itemView.findViewById(R.id.pos);
            points=(TextView)itemView.findViewById(R.id.points);
            dif=(TextView)itemView.findViewById(R.id.dif);
            img_player=(AppCompatImageView)itemView.findViewById(R.id.img_player);
        }

    }



    }
