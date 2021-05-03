package com.diegopereira.cartolafc;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.diegopereira.cartolafc.parciais.APIInterface;
import com.diegopereira.cartolafc.parciais.Atletas;
import com.diegopereira.cartolafc.parciais.Clubes;
import com.diegopereira.cartolafc.parciais.DatabaseHelper;
import com.diegopereira.cartolafc.parciais.Parciais;
import com.diegopereira.cartolafc.parciais.ParciaisRecyclerAdapter;
import com.diegopereira.cartolafc.parciais.Posicoes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParciaisActivity extends AppCompatActivity {

    private ProgressBar loadProgress;

    RecyclerView rv_parciais;

    ParciaisRecyclerAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    private DatabaseHelper database;

    public static ArrayList<Atletas> list;
    Map<Integer, Posicoes> posicoes = new HashMap();
    Map<Integer, Clubes> clubes = new HashMap();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parciais);

        database = new DatabaseHelper(getApplicationContext());

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button


        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh3);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //database.delete();

                loadParciais(); // your code
                pullToRefresh.setRefreshing(false);
            }
        });

        loadProgress = (ProgressBar) findViewById(R.id.progressBar);
        rv_parciais = findViewById(R.id.rv_parciais);
        rv_parciais.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_parciais.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(rv_parciais.getContext(), DividerItemDecoration.VERTICAL);
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{0xfff7f7f7, 0xfff7f7f7});
        drawable.setSize(1,1);
        itemDecoration.setDrawable(drawable);
        rv_parciais.addItemDecoration(itemDecoration);


        loadParciais();

    }

    public void loadParciais() {

        final Gson gson = new GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://jsonkeeper.com")
                //.baseUrl(CONSTANTS.BASE_URL)
                .build();
        APIInterface requestInterface = retrofit.create(APIInterface.class);
        Call<Parciais> call = requestInterface.getAtletas();
        call.enqueue(new Callback<Parciais>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Parciais> call, Response<Parciais> response) {

                    loadProgress.setVisibility(View.GONE);

                    posicoes = response.body().getPosicoes();
                    clubes = response.body().getClubes();


                try {
                        // edited here ,add toJson
                        String jsonResponse = new Gson().toJson(response.body());
                        System.out.println(response.code());
                        if(response.code() == 200) {
                        // if (response.isSuccessful() && response.body() != null) {
                            JSONObject jsonObject = new JSONObject(jsonResponse);

                            String rodada = jsonObject.optString("rodada");
                            JSONObject key2 = jsonObject.optJSONObject("atletas");

                            Iterator<String> sIterator = key2.keys();

                            //final ArrayList<Atletas> list = new ArrayList<>();
                            list = new ArrayList<>();


                            while (sIterator.hasNext()) {
                                String atleta_ids = sIterator.next();

                                JSONObject atletas = key2.optJSONObject(atleta_ids);

                                String apelido = atletas.getString("apelido");

                                final String pontos = atletas.getString("pontuacao");

                                //
                                Map<String, String> colours = new HashMap<>();
                                colours.put(apelido, pontos);


                                //System.out.println("MAP:" + colours);
                                //

                                String posicao = atletas.getString("posicao_id");

                                String clube = atletas.getString("clube_id");

                                JSONObject scouts = atletas.optJSONObject("scout");

                                //System.out.println("APELIDO: " + apelido + " PONTOS: " + pontos);
                                //System.out.println("SCOUTS: " + scouts);


                                Map<String, Integer> map = new HashMap<>();
                                ;
                                String chave = "";
                                int valor = 0;
                                if (scouts != null) {
                                    JSONArray scoutsTags = atletas.getJSONObject("scout").names();

                                    if (scoutsTags != null) {
                                        //System.out.println("scoutsTags: " + scoutsTags);
                                        for (int i = 0; i < scoutsTags.length(); i++) {
                                            chave = scoutsTags.getString(i);

                                            valor = scouts.getInt(chave);

                                            //System.out.println("CHAVE: " + chave + " VALOR: " + valor);


                                            map.put(chave, valor);
                                            //System.out.println("MAP: " + map);


                                        }


                                    }
                                }


                                Atletas newatleta = new Atletas();
                                newatleta.setRodada(Integer.parseInt(rodada));
                                newatleta.setId(Integer.parseInt(atleta_ids));
                                newatleta.setClubeId(Integer.parseInt(clube));
                                newatleta.setApelido(apelido);
                                newatleta.setPosicaoId(Integer.parseInt(posicao));
                                newatleta.setPontuacao(Double.parseDouble(pontos));
                                newatleta.setScout(map);

                                list.add(newatleta);


                                Collections.sort(list, new Comparator<Atletas>() {
                                    @Override
                                    public int compare(Atletas o1, Atletas o2) {
                                        return o2.getPontuacao().compareTo(o1.getPontuacao());
                                    }
                                });


                                adapter = new ParciaisRecyclerAdapter(getApplicationContext(), list, posicoes, clubes);

                                rv_parciais.setAdapter(adapter);


                            }

                        }

                        if(response.code() == 204 || response.code() == 404) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Parciais não disponíveis!!! \n Aguarde a rodada \n Iniciar!", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                            LinearLayout toastLL = (LinearLayout) toast.getView();
                            toastLL.setBackgroundColor(Color.WHITE);
                            TextView toastTV = (TextView) toastLL.getChildAt(0);
                            toastTV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            toastTV.setTextColor(Color.BLACK);
                            toastTV.setTextSize(22);
                            toast.show();
                        }
                    }
                        catch(JSONException ex){
                        ex.printStackTrace();
                    }


                    }



            @Override
            public void onFailure(Call<Parciais> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_mais) {
            Intent intent = new Intent(getApplicationContext(), MaisEscalados.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.partidas) {
            Intent intent = new Intent(getApplicationContext(), JogosActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.jogadores) {
            Intent intent = new Intent(getApplicationContext(), JogadoresActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.parciais) {
            Intent intent = new Intent(getApplicationContext(), ParciaisActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.liga) {
            Intent intent = new Intent(getApplicationContext(), Teste2Activity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}