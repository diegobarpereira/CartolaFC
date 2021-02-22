package com.diegopereira.cartolafc.groups;

import android.bluetooth.BluetoothGattServer;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.diegopereira.cartolafc.FavoritosActivity;
import com.diegopereira.cartolafc.LigaActivity;
import com.diegopereira.cartolafc.R;
import com.diegopereira.cartolafc.destaques.DestaquesRecyclerAdapter;
import com.diegopereira.cartolafc.league.TimePontos;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class GroupRecyclerAdapter extends RecyclerView.Adapter<GroupRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Input> input;

    public static ArrayList<Integer> newList = new ArrayList<>();

    private DatabaseHelper database;


    public GroupRecyclerAdapter(Context context, List<Input> input) {
        this.context=context;
        this.input=input;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.grouplist_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Glide.with(context)
                .load(input.get(position).getUrlEscudoPng())
                .into(viewHolder.img_team);

        viewHolder.tv_nome.setText(input.get(position).getNome());
        viewHolder.tv_nome_cartola.setText(input.get(position).getNomeCartola());

        viewHolder.button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = context.getSharedPreferences("GROUP", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                //editor.putString("GROUP_ID", input.get(position).getTimeId().toString());



                Intent intent = new Intent(context, FavoritosActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                editor.apply();
                context.startActivity(intent);

                database = new DatabaseHelper(context);
                database.insert(input.get(position).getNome(), input.get(position).getNomeCartola(), input.get(position).getUrlEscudoPng(), input.get(position).getTimeId());


            }
        });

    }

    @Override
    public int getItemCount() {
        return input.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        AppCompatImageView img_team, button_add;
        AppCompatTextView tv_nome, tv_nome_cartola;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_team=(AppCompatImageView) itemView.findViewById(R.id.img_team);
            button_add=(AppCompatImageView) itemView.findViewById(R.id.button_add);
            tv_nome=(AppCompatTextView)itemView.findViewById(R.id.tv_nome);
            tv_nome_cartola=(AppCompatTextView)itemView.findViewById(R.id.tv_nome_cartola);
        }
    }

}
