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

    private DatabaseHelper database;

    public ParciaisRecyclerAdapter(Context context, List<Atletas> list) {
        this.context = context;
        this.list = list;
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


        //list.get(position).g


        database.insert(list.get(position).getRodada(), list.get(position).getApelido(), list.get(position).getPontuacao(), list.get(position).getId(), list.get(position).getScout());

        DecimalFormat formatter = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.US));
        String get_pontos = formatter.format(list.get(position).getPontuacao());

        holder.tv_pontosparciais.setText(String.valueOf(get_pontos));


        holder.tv_scouts.setText(String.valueOf(list.get(position).getScout()).replace("{", "").replace(",", "").replace("}", "").replace("=", ": "));
        //System.out.println(list.get(position).getScout());
        holder.tv_posicaoparciais.setText(String.valueOf(list.get(position).getPosicaoId())
                .replace("1", "Goleiro")
                .replace("2", "Lateral")
                .replace("3", "Zagueiro")
                .replace("4", "Meia")
                .replace("5", "Atacante")
                .replace("6", "Técnico")
        );

        String clube = String.valueOf(list.get(position).getClubeId()) 
                .replace("262", "Flamengo")
                .replace("263", "Botafogo")
                .replace("264", "Corinthians")
                .replace("265", "Bahia")
                .replace("266", "Fluminense")
                .replace("267", "Vasco")
                .replace("275", "Palmeiras")
                .replace("276", "São Paulo")
                .replace("277", "Santos")
                .replace("280", "Bragantino")
                .replace("282", "Atlético-MG")
                .replace("284", "Grêmio")
                .replace("285", "Internacional")
                .replace("290", "Goiás")
                .replace("292", "Sport")
                .replace("293", "Athlético-PR")
                .replace("294", "Coritiba")
                .replace("354", "Ceará")
                .replace("356", "Fortaleza")
                .replace("373", "Atlético-GO");

        if (clube.equals("Flamengo")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2018/04/09/Flamengo-65.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("Botafogo")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/02/04/botafogo-65.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("Corinthians")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/09/30/Corinthians_65.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("Bahia")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/bahia_60x60.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("Fluminense")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/fluminense_60x60.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("Vasco")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2016/07/29/Vasco-65.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("Palmeiras")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/palmeiras_60x60.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("São Paulo")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/sao_paulo_60x60.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("Santos")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/santos_60x60.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("Bragantino")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2020/01/01/65.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("Atlético-MG")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2017/11/23/Atletico-Mineiro-escudo65px.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("Grêmio")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/gremio_60x60.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("Internacional")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2016/05/03/inter65.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("Goiás")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/05/01/Goias_65px.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("Sport")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2015/07/21/sport65.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("Athlético-PR")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/09/09/Athletico-PR-65x65.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("Coritiba")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2017/03/29/coritiba65.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("Ceará")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/10/10/ceara-65x65.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("Fortaleza")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2018/06/10/fortaleza-ec-65px.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("Atlético-GO")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2020/07/02/atletico-go-2020-65.png")
                    .resize(70, 70)
                    .into(holder.img_clube);


        }

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