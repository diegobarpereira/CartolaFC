package com.diegopereira.cartolafc.league;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.LeagueActivity;
import com.diegopereira.cartolafc.LigaActivity;
import com.diegopereira.cartolafc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

import static android.content.Context.MODE_PRIVATE;

public class MySection extends StatelessSection {
    String title;
    List<Times> list;
    Context context;

    public MySection( Context context, String title, List<Times> list) {
        // call constructor with layout resources for this Section header, footer and items
        super(SectionParameters.builder()
                .itemResourceId(R.layout.leaguelist_item)
                .headerResourceId(R.layout.league_header)
                .build());

        this.title = title;
        this.list = list;
        this.context = context;
    }

    @Override
    public int getContentItemsTotal() {
        return list.size(); // number of items of this section
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        // return a custom instance of ViewHolder for the items of this section
        return new MyItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyItemViewHolder itemViewHolder = (MyItemViewHolder) holder;

        // bind your view here

        Double diff = 0.00;
        DecimalFormat formatter = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.US));

        for (int i = 0; i < position; i++) {
            itemViewHolder.dif.setVisibility(View.VISIBLE);

            diff = list.get(position).getPontos().getCampeonato()-list.get(0).getPontos().getCampeonato();

        }


        if( position == 0) {
            itemViewHolder.dif.setVisibility(View.INVISIBLE);
        }

        itemViewHolder.dif.setText(String.valueOf(formatter.format(diff)));

        String name = list.get(position).getNome();
        itemViewHolder.name.setText(name);

        System.out.println(list.get(position).getRanking().getCampeonato());
        Integer posicao = list.get(position).getRanking().getCampeonato();
        String pos = String.valueOf(posicao);
        itemViewHolder.pos.setText(pos);

        Double pontos = list.get(position).getPontos().getCampeonato();
        String points = String.valueOf(formatter.format(pontos));
        itemViewHolder.points.setText(points);

        Integer var = list.get(position).getVariacao().getCampeonato();
        if (var > 0) {
            String variacao = String.valueOf(var);
            itemViewHolder.var.setText("+" + variacao);
            itemViewHolder.var.setTextColor(Color.GREEN);
        }
        if (var < 0 ) {
            String variacao = String.valueOf(var);
            itemViewHolder.var.setText(variacao);
            itemViewHolder.var.setTextColor(Color.RED);
        }
        if (var == 0 ) {
            String variacao = String.valueOf(var);
            itemViewHolder.var.setText("=");
            itemViewHolder.var.setTextColor(Color.GRAY);
        }

        Double ultima = list.get(position).getPontos().getRodada();
        String rodada = String.valueOf(formatter.format(ultima));
        itemViewHolder.ultima.setText(rodada);

        Double cash = list.get(position).getPatrimonio();
        String dinheiro = String.valueOf(formatter.format(cash));
        itemViewHolder.cash.setText("$" + dinheiro);

        String symbol = list.get(position).getUrl_escudo_png();
        Picasso.with(context)
                .load(symbol)
                .resize(70, 70)
                .into(itemViewHolder.img_player);

        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   Toast.makeText(context, list.get(position).getTime_id().toString(), Toast.LENGTH_SHORT).show();
                                                   SharedPreferences preferences = context.getSharedPreferences("SHARED_PREF_ID", MODE_PRIVATE);
                                                   SharedPreferences.Editor editor = preferences.edit();

                                                   editor.putString("ID_SHARED_PREF", list.get(position).getTime_id().toString());
                                                   Intent intent = new Intent(context, LigaActivity.class);
                                                   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                   editor.apply();
                                                   context.startActivity(intent);
                                               }
                                           }
        );

    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        MyHeaderViewHolder headerHolder = (MyHeaderViewHolder) holder;

        // bind your header view here

        headerHolder.league_name.setText(title);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        // return an empty instance of ViewHolder for the headers of this section
        return new MyHeaderViewHolder(view);
    }



}
