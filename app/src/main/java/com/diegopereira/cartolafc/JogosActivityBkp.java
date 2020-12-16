package com.diegopereira.cartolafc;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.diegopereira.cartolafc.partidas.ApiClient;
import com.diegopereira.cartolafc.partidas.Example;
import com.diegopereira.cartolafc.partidas.JogosRecyclerAdapter;
import com.diegopereira.cartolafc.partidas.Partida;
import com.diegopereira.cartolafc.partidas.RequestInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JogosActivityBkp extends AppCompatActivity {
/*
    RecyclerView recyclerView;
    List<Partida> list = new ArrayList<>();
    JogosRecyclerAdapter adapter;
    private String rodada = MainActivity.rodada_;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogos);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh2);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJogos(); // your code
                pullToRefresh.setRefreshing(false);
            }
        });

        recyclerView = findViewById(R.id.recyclerview_jogos);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        loadJogos();
        System.out.println(rodada);

    }

    private void loadJogos() {
        RequestInterface service = ApiClient.getInterface();
        Call<Example> call = service.getPartidas();

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                list = response.body().getPartidas();
                Collections.sort(list, new Comparator<Partida>() {
                    @Override
                    public int compare(Partida o1, Partida o2) {
                        return o1.getPartidaData().compareTo(o2.getPartidaData());
                    }
                });

                adapter = new JogosRecyclerAdapter(JogosActivityBkp.this, list);

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
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

 */
}



