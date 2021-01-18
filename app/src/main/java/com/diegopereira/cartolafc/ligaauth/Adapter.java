package com.diegopereira.cartolafc.ligaauth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.LeagueActivity;
import com.diegopereira.cartolafc.LigaActivity;
import com.diegopereira.cartolafc.R;
import com.diegopereira.cartolafc.teste.TimePontos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private List<Ligas> ligas;
    private List<String> ligas1 = new ArrayList<>();
    private List<String> ligas2 = new ArrayList<>();
    private List<Section> sectionList = new ArrayList<>();

    public Adapter( Context context, List<Ligas> ligas) {
        this.context=context;
        this.ligas=ligas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.ligaauth_header, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( @NonNull ViewHolder holder, int position ) {
        String ligaPrivada = "Ligas Privadas";
        List<String> sectionOneItems = new ArrayList<>();
        sectionOneItems.add(ligas.get(position).getNome());


        String ligaPublica = "Ligas Públicas";
        List<String> sectionTwoItems = new ArrayList<>();
        sectionTwoItems.add(ligas.get(position).getNome());

        sectionList.add(new Section(ligaPrivada, sectionOneItems));
        sectionList.add(new Section(ligaPublica, sectionTwoItems));

        System.out.println("SECTIONLIST: " + sectionList);

        Section section = sectionList.get(position);
        String sectionName = section.getSectionName();
        List<String> items = section.getSectionItems();

        holder.nameliga.setText(sectionName);

        ChildRecyclerAdapter childRecyclerAdapter = new ChildRecyclerAdapter(items);
        holder.childRecyclerView.setAdapter(childRecyclerAdapter);

    }





    @Override
    public int getItemCount() {
        return ligas.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameliga;
        RecyclerView childRecyclerView;


        public ViewHolder(View itemView) {
            super(itemView);
            nameliga = (TextView)itemView.findViewById(R.id.nameliga);
            childRecyclerView = itemView.findViewById(R.id.childRecyclerView);


        }
    }

}


