package com.diegopereira.cartolafc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.favoritos.APIInterface;
import com.diegopereira.cartolafc.favoritos.ApiClient;
import com.diegopereira.cartolafc.favoritos.FavRecyclerAdapter;
import com.diegopereira.cartolafc.favoritos.Players;
import com.diegopereira.cartolafc.favoritos.TimePontos;
import com.diegopereira.cartolafc.groups.GroupRecyclerAdapter;
import com.diegopereira.cartolafc.groups.Input;
import com.diegopereira.cartolafc.teste.RecyclerViewAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritosActivity extends AppCompatActivity {

    String time_id;
    ArrayList<TimePontos> teste = new ArrayList<>();
    //public static ArrayList<Integer> ids = new ArrayList<>();
    List<Input> test;
    RecyclerView rv_fav;
    public static FavRecyclerAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favoritos);

        SharedPreferences sharedPref = getSharedPreferences("GROUP", MODE_PRIVATE);
        //time_id = sharedPref.getString("GROUP_ID", "");

        //System.out.println("TIME_ID: " + time_id);

        Gson gson = new Gson();
        String json = sharedPref.getString("GROUP_ID", "");
        //ArrayList<Input> obj = gson.fromJson(json, (Type) Input.class);

        test = new Gson().fromJson(sharedPref.getString("GROUP_ID", null), new TypeToken<List<Input>>(){}.getType());
        //String test = gson.fromJson(json, String.class);


        System.out.println("OBJ: " + test);




        rv_fav = findViewById(R.id.rv_fav);
        rv_fav.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_fav.setLayoutManager(linearLayoutManager);
        rv_fav.addItemDecoration(new DividerItemDecoration(rv_fav.getContext(), DividerItemDecoration.VERTICAL));
        adapter = new FavRecyclerAdapter(getApplicationContext(), teste);
        rv_fav.setAdapter(adapter);

        addItem();

        //ids.add(Integer.valueOf(time_id));

        //System.out.println("IDS: " + ids);


    }

    @Override
    public void onResume() {
        super.onResume();

        adapter.notifyDataSetChanged();
    }

    public void addItem() {
        if (!String.valueOf(test).equals("")) {
            for (int i = 0; i < test.size(); i++) {
                APIInterface apiInterface = ApiClient.getRetrofit().create(APIInterface.class);
                //Call<Players> call = apiInterface.getTime(time_id);
                System.out.println("dsvgfdvgf: " + test.get(i).getTimeId());
                Call<Players> call = apiInterface.getTime(test.get(i).getTimeId().toString());
                int finalI = i;
                call.enqueue(new Callback<Players>() {
                    @Override
                    public void onResponse(Call<Players> call, Response<Players> response) {
                        TimePontos timePontos = new TimePontos();
                        timePontos.setNome(response.body().getTime().getNome());
                        timePontos.setPontos(response.body().getPontosCampeonato());
                        timePontos.setUltima(response.body().getPontos());
                        timePontos.setUrlEscudoPng(response.body().getTime().getUrlEscudoPng());
                        timePontos.setPatrimonio(response.body().getPatrimonio());
                        timePontos.setTimeId(test.get(finalI).getTimeId());
                        teste.add(timePontos);

                        Collections.sort(teste, new Comparator<TimePontos>() {
                            @Override
                            public int compare(TimePontos o1, TimePontos o2) {
                                return o2.getPontos().compareTo(o1.getPontos());
                            }
                        });


                        //adapter = new FavRecyclerAdapter(getApplicationContext(), teste);
                        //rv_fav.setAdapter(adapter);
                        adapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onFailure(Call<Players> call, Throwable t) {

                    }
                });
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fav_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(), GroupActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);


    }

}