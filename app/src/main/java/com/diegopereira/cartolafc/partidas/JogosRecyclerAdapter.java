package com.diegopereira.cartolafc.partidas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.diegopereira.cartolafc.JogosActivity;
import com.diegopereira.cartolafc.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class JogosRecyclerAdapter extends RecyclerView.Adapter<JogosRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Partida> list;

    public JogosRecyclerAdapter(Context context, List<Partida> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.jogoslist_item,parent,false);
        return new JogosRecyclerAdapter.ViewHolder(v);
    }

    @SuppressLint({"ResourceAsColor", "ResourceType"})
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int rodadaatual = JogosActivity.rodada;
        int rod = JogosActivity.rod;
        System.out.println("RODADAATUAL: " + rodadaatual + " ROD: " + rod);

        Integer poscasa = list.get(position).getClubeCasaPosicao();
        Integer posvis = list.get(position).getClubeVisitantePosicao();

        String casa = "";
        if (rodadaatual == rod) {
            casa = String.valueOf(list.get(position).getClubeCasaId())
                    .replace("262", poscasa + "º " + "Flamengo")
                    .replace("263", poscasa + "º " + "Botafogo")
                    .replace("264", poscasa + "º " + "Corinthians")
                    .replace("265", poscasa + "º " + "Bahia")
                    .replace("266", poscasa + "º " + "Fluminense")
                    .replace("267", poscasa + "º " + "Vasco")
                    .replace("275", poscasa + "º " + "Palmeiras")
                    .replace("276", poscasa + "º " + "São Paulo")
                    .replace("277", poscasa + "º " + "Santos")
                    .replace("280", poscasa + "º " + "Bragantino")
                    .replace("282", poscasa + "º " + "Atlético-MG")
                    .replace("284", poscasa + "º " + "Grêmio")
                    .replace("285", poscasa + "º " + "Internacional")
                    .replace("290", poscasa + "º " + "Goiás")
                    .replace("292", poscasa + "º " + "Sport")
                    .replace("293", poscasa + "º " + "Athlético-PR")
                    .replace("294", poscasa + "º " + "Coritiba")
                    .replace("354", poscasa + "º " + "Ceará")
                    .replace("356", poscasa + "º " + "Fortaleza")
                    .replace("373", poscasa + "º " + "Atlético-GO");
        } else {
            casa = String.valueOf(list.get(position).getClubeCasaId())
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
        }
        //Log.d("TAG", casa);

        String visitante = "";
        if (rodadaatual == rod) {
            visitante = String.valueOf(list.get(position).getClubeVisitanteId())
                    .replace("262", "Flamengo" + " " + posvis + "º")
                    .replace("263", "Botafogo" + " " + posvis + "º")
                    .replace("264", "Corinthians" + " " + posvis + "º")
                    .replace("265", "Bahia" + " " + posvis + "º")
                    .replace("266", "Fluminense" + " " + posvis + "º")
                    .replace("267", "Vasco" + " " + posvis + "º")
                    .replace("275", "Palmeiras" + " " + posvis + "º")
                    .replace("276", "São Paulo" + " " + posvis + "º")
                    .replace("277", "Santos" + " " + posvis + "º")
                    .replace("280", "Bragantino" + " " + posvis + "º")
                    .replace("282", "Atlético-MG" + " " + posvis + "º")
                    .replace("284", "Grêmio" + " " + posvis + "º")
                    .replace("285", "Internacional" + " " + posvis + "º")
                    .replace("290", "Goiás" + " " + posvis + "º")
                    .replace("292", "Sport" + " " + posvis + "º")
                    .replace("293", "Athlético-PR" + " " + posvis + "º")
                    .replace("294", "Coritiba" + " " + posvis + "º")
                    .replace("354", "Ceará" + " " + posvis + "º")
                    .replace("356", "Fortaleza" + " " + posvis + "º")
                    .replace("373", "Atlético-GO" + " " + posvis + "º");
        } else {
            visitante = String.valueOf(list.get(position).getClubeVisitanteId())
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
        }


        if (casa.contains("Flamengo")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2018/04/09/Flamengo-65.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Flamengo")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2018/04/09/Flamengo-65.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("Botafogo")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/02/04/botafogo-65.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Botafogo")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/02/04/botafogo-65.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("Corinthians")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/09/30/Corinthians_65.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Corinthians")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/09/30/Corinthians_65.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("Bahia")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/bahia_60x60.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Bahia")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/bahia_60x60.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("Fluminense")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/fluminense_60x60.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Fluminense")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/fluminense_60x60.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("Vasco")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2016/07/29/Vasco-65.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Vasco")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2016/07/29/Vasco-65.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("Palmeiras")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/palmeiras_60x60.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Palmeiras")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/palmeiras_60x60.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("São Paulo")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/sao_paulo_60x60.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("São Paulo")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/sao_paulo_60x60.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("Santos")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/santos_60x60.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Santos")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/santos_60x60.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("Bragantino")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2020/01/01/65.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Bragantino")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2020/01/01/65.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("Atlético-MG")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2017/11/23/Atletico-Mineiro-escudo65px.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Atlético-MG")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2017/11/23/Atletico-Mineiro-escudo65px.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("Grêmio")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/gremio_60x60.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Grêmio")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/gremio_60x60.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("Internacional")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2016/05/03/inter65.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Internacional")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2016/05/03/inter65.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("Goiás")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/05/01/Goias_65px.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Goiás")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/05/01/Goias_65px.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("Sport")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2015/07/21/sport65.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Sport")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2015/07/21/sport65.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("Athlético-PR")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/09/09/Athletico-PR-65x65.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Athlético-PR")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/09/09/Athletico-PR-65x65.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("Coritiba")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2017/03/29/coritiba65.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Coritiba")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2017/03/29/coritiba65.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("Ceará")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/10/10/ceara-65x65.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Ceará")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/10/10/ceara-65x65.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("Fortaleza")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2018/06/10/fortaleza-ec-65px.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Fortaleza")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2018/06/10/fortaleza-ec-65px.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }

        if (casa.contains("Atlético-GO")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2020/07/02/atletico-go-2020-65.png")
                    .resize(70, 70)
                    .into(holder.img_clube1);
        }
        if (visitante.contains("Atlético-GO")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2020/07/02/atletico-go-2020-65.png")
                    .resize(70, 70)
                    .into(holder.img_clube2);
        }


        holder.tv_clube1.setText(casa);
        holder.tv_clube2.setText(visitante);

        if(list.get(position).getValida() == true) {
            Date currentTime = Calendar.getInstance().getTime();
            Date partidadata = null;
            Date futuredata = null;
            String datapartida = null;
            Date datapartida_ = null;
            final long millisToAdd = 7_200_000;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                futuredata = sdf.parse("2020-10-03 19:01:00");
                partidadata = sdf.parse(list.get(position).getPartidaData());

                datapartida = sdf.format(partidadata);
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.HOUR, 2);
                cal.setTime(partidadata);
                datapartida_ = addHour(datapartida,2);

            } catch (Exception e) {
                e.printStackTrace();
            }


            //System.out.println("Current : " + currentTime);
            System.out.println("PartidaData: " + partidadata);
            //System.out.println("FutureData: " + futuredata);


            if (currentTime.before(datapartida_)) {
                holder.tv_data.setText(list.get(position).getPartidaData() + " - " + list.get(position).getLocal());
            }
            if (currentTime.after(datapartida_)) {
                holder.tv_data.setText("Partida Encerrada!");
                holder.tv_data.setTextColor(Color.RED);
            }
        } else {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
            holder.tv_data.setText("ESTA PARTIDA NÃO É VÁLIDA PARA A RODADA");
        }

        holder.tv_placar1.setText(String.valueOf(list.get(position).getPlacarOficialMandante()).replace("null", "0"));
        holder.tv_placar2.setText(String.valueOf(list.get(position).getPlacarOficialVisitante()).replace("null", "0"));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private AppCompatImageView img_clube1, img_clube2;
        private AppCompatTextView tv_clube1, tv_clube2, tv_local, tv_placar1, tv_placar2, tv_valida;
        private TextView tv_data;
        private ImageView tv_apro, tv_apro2, tv_apro3, tv_apro4, tv_apro5;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_clube1=(AppCompatTextView)itemView.findViewById(R.id.tv_clube1);
            tv_clube2=(AppCompatTextView)itemView.findViewById(R.id.tv_clube2);
            tv_data=(TextView)itemView.findViewById(R.id.tv_data);
            tv_placar1 = (AppCompatTextView)itemView.findViewById(R.id.tv_placar1);
            tv_placar2 = (AppCompatTextView)itemView.findViewById(R.id.tv_placar2);
            img_clube1=(AppCompatImageView) itemView.findViewById(R.id.img_clube1);
            img_clube2=(AppCompatImageView) itemView.findViewById(R.id.img_clube2);
        }
    }

    public static Date addHour(String partidadata,int number)
    {
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = df.parse(partidadata);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.HOUR, number);
            String newTime = df.format(cal.getTime());
            Date data = df.parse(newTime);
            return data;
        }
        catch(ParseException e)
        {
            System.out.println(" Parsing Exception");
        }
        return null;

    }
}
