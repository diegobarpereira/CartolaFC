package com.diegopereira.cartolafc.league;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.diegopereira.cartolafc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class MyParcialSection extends StatelessSection {
    String title;
    List<Times> list;
    List<TimePontos> teste;
    Context context;
    Map<String, Double> mapparciais;
    Map<String, Double> mapliga;
    private DatabaseHelper database;




    public MyParcialSection( Context context, String title, List<TimePontos> teste, Map<String, Double> mapparciais) {
        // call constructor with layout resources for this Section header, footer and items
        super(SectionParameters.builder()
                .itemResourceId(R.layout.leaguelist_item)
                .headerResourceId(R.layout.league_header)
                .build());

        this.title = title;
        this.list = list;
        this.teste = teste;
        this.context = context;
        this.mapparciais = mapparciais;

        database = new DatabaseHelper(context);

    }

    @Override
    public int getContentItemsTotal() {
        return teste.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        // return a custom instance of ViewHolder for the items of this section
        return new MyItemViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyItemViewHolder itemViewHolder = (MyItemViewHolder) holder;

        DecimalFormat formatter = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.US));



        Integer get_cap = teste.get(position).getCapitaoId();


        Integer jogador_id = 0;
        mapliga = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            jogador_id = teste.get(position).getAtletas().get(i).getAtletaId();

            mapliga.put(String.valueOf(jogador_id), 0.0);

        }

        HashMap<String, Double> teste_ = new HashMap<>();
        double total = 0.0;
        double totalcap = 0.0;
        String ttotal = "";
        double x = 0.0;
        for (Map.Entry<String, Double> hashmap : mapparciais.entrySet()) {
            if (mapliga.containsKey(hashmap.getKey())) {
                //System.out.println(hashmap.getKey() + " - " + hashmap.getValue());
                mapparciais.put(hashmap.getKey(), hashmap.getValue());

                teste_.put(hashmap.getKey(), hashmap.getValue());

            }

            if (hashmap.getKey().contains(String.valueOf(get_cap))) {

                x = hashmap.getValue();
                //System.out.println("X: " + x);

            }

        }

        for (String k : teste_.keySet()) {
            //System.out.println(k + "\t" + teste_.get(k));

            total += teste_.get(k);
            totalcap = (total + x);
            ttotal = formatter.format(totalcap);

            for (int i = 0; i < teste.size(); i++) {
                teste.get(i).setParciais(totalcap);
                //teste.get(i).setParciais(0.0);
            }

            //System.out.println("SUM: " + ttotal);
            //holder.parciais.setText(ttotal); //
        }

        Integer id = 0;
        String qty = "";
        Double parc = 0.0;
        for (int i = 0; i < teste.size(); i++) {
            Double pontototal = teste.get(i).getPontos() + totalcap;
            //Double pontototal = teste.get(i).getPontos();

            qty = (teste_.size() + "/" + mapliga.size());
            parc = teste.get(i).getParciais();
            teste.get(i).setPontosrod(pontototal);
            teste.get(i).setQty(qty);

                database.update(position+1, teste.get(position).getNome(), teste.get(position).getParciais(), teste.get(position).getPontosrod(),
                        teste.get(position).getUrlEscudoPng(), teste.get(position).getQty(), teste.get(position).getTimeId());

        }


        List<TimePontos> item = database.getTimes();
        System.out.println(item.toString());



        Glide.with(context)
                .load(item.get(position).getUrlEscudoPng())
                .into(itemViewHolder.img_player);


        itemViewHolder.name.setText(item.get(position).getNome());
        itemViewHolder.ultima.setText(formatter.format(item.get(position).getParciais()));
        itemViewHolder.points.setText(formatter.format(item.get(position).getPontosrod()));

        itemViewHolder.cash.setText(item.get(position).getQty());

        itemViewHolder.pos.setText(String.valueOf(position + 1));
        itemViewHolder.var.setVisibility(View.GONE);
        itemViewHolder.dif.setVisibility(View.GONE);

        //String image = item.get(position).getUrlEscudoPng();

        //Picasso.with(context)
        //        .load(item.get(position).getUrlEscudoPng())
        //        .into(itemViewHolder.img_player);




    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        MyHeaderViewHolder headerHolder = (MyHeaderViewHolder) holder;
        headerHolder.league_name.setText(title);
        headerHolder.league_ultima.setText("Parciais");

        headerHolder.league_ultima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, String.valueOf(headerHolder.league_ultima.getText()), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        // return an empty instance of ViewHolder for the headers of this section
        return new MyHeaderViewHolder(view);
    }

}
