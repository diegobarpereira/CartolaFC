package com.diegopereira.cartolafc.parciais;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.diegopereira.cartolafc.R;
import com.diegopereira.cartolafc.jogadores.Atleta;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class ParciaisRecyclerAdapter extends RecyclerView.Adapter<ParciaisRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Atletas> list;
    private Map<Integer, Posicoes> posicoes;
    private Map<Integer, Clubes> clubes;

    private DatabaseHelper database;
    private String posicao;
    private String escudo;

    public ParciaisRecyclerAdapter(Context context, List<Atletas> list, Map<Integer, Posicoes> posicoes, Map<Integer, Clubes> clubes) {
        this.context = context;
        this.list = list;
        this.posicoes = posicoes;
        this.clubes = clubes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.parciaislist_item, parent, false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        database = new DatabaseHelper(context);

        holder.tv_playerparciais.setText(list.get(position).getApelido());

        database.insert(list.get(position).getRodada(), list.get(position).getApelido(), list.get(position).getPontuacao(), list.get(position).getId(), list.get(position).getScout());

        DecimalFormat formatter = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.US));
        String get_pontos = formatter.format(list.get(position).getPontuacao());

        holder.tv_pontosparciais.setText(String.valueOf(get_pontos));

        holder.tv_scouts.setText(String.valueOf(list.get(position).getScout()).replace("{", "").replace(",", "").replace("}", "").replace("=", ": "));

        for(Map.Entry<Integer, Posicoes> entry:posicoes.entrySet()) {
            //System.out.println(entry.getKey() + " / " + entry.getValue().getNome());
            if ((String.valueOf(list.get(position).getPosicaoId())).equals(entry.getValue().getId())) {
                posicao = entry.getValue().getNome();
            }
        }
        holder.tv_posicaoparciais.setText(posicao);

        for(Map.Entry<Integer, com.diegopereira.cartolafc.parciais.Clubes> entry:clubes.entrySet()) {
            if ((list.get(position).getClubeId()).equals((entry.getValue().getId()))) {
                escudo = entry.getValue().getEscudos().get_60x60();
            }
        }

        Glide
                .with(context)
                .load(escudo)
                .into(holder.img_clube);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView img_clube, img_bola, img_chut;
        private AppCompatTextView tv_playerparciais, tv_pontosparciais, tv_posicaoparciais, tv_scouts, tv_status, tv_media, tv_ultima;
        private RelativeLayout rl;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_playerparciais = (AppCompatTextView) itemView.findViewById(R.id.tv_playerparciais);
            tv_pontosparciais = (AppCompatTextView) itemView.findViewById(R.id.tv_pontosparciais);
            tv_posicaoparciais = (AppCompatTextView) itemView.findViewById(R.id.tv_posicaoparciais);
            img_clube = (AppCompatImageView) itemView.findViewById(R.id.img_clube);
            tv_scouts = (AppCompatTextView) itemView.findViewById(R.id.tv_scouts);
            rl = itemView.findViewById(R.id.rl);

        }
    }
}