package com.diegopereira.cartolafc;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.diegopereira.cartolafc.league.APIInterface;
import com.diegopereira.cartolafc.league.League;
import com.diegopereira.cartolafc.league.RecyclerViewAdapter;
import com.diegopereira.cartolafc.league.ServiceGenerator;
import com.diegopereira.cartolafc.league.Times;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.diegopereira.cartolafc.ligaauth.RecyclerViewAdapter.SHARED_PREF_SLUG;
import static com.diegopereira.cartolafc.ligaauth.RecyclerViewAdapter.SLUG_SHARED_PREF;

import static com.diegopereira.cartolafc.LoginActivity.SHARED_PREF_NAME;
import static com.diegopereira.cartolafc.LoginActivity.SHARED_TOKEN;


public class LeagueActivity extends AppCompatActivity {
    SharedPreferences preferences, token_preferences;
    String slug;
    RecyclerView recyclerView;
    List<Times> list = new ArrayList<>();

    public static RecyclerViewAdapter adapter;


    String token;
    private ProgressBar loadProgress;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.league);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button



        token_preferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        token = token_preferences.getString(SHARED_TOKEN, "");
        preferences = getSharedPreferences(SHARED_PREF_SLUG, MODE_PRIVATE);
        slug = preferences.getString(SLUG_SHARED_PREF, "");

        System.out.println(slug);
        Toast.makeText(getApplicationContext(), slug + " Activity", Toast.LENGTH_SHORT).show();

        loadProgress = (ProgressBar) findViewById(R.id.leagueprogressBar);
        recyclerView = findViewById(R.id.rv_league);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));


        loadLeague();

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefreshLeague);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadLeague(); // your code
                pullToRefresh.setRefreshing(false);
            }
        });



    }

    private void loadLeague() {
        APIInterface league = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<League> call = league.getTime(token, slug);
        call.enqueue(new Callback<League>() {
            @Override
            public void onResponse(Call<League> call, Response<League> response) {
                loadProgress.setVisibility(View.GONE);



                //System.out.println(response.body().getTimes());
                list = response.body().getTimes();

                Collections.sort(list, new Comparator<Times>() {
                    @Override
                    public int compare(Times o1, Times o2) {
                        return (o2.getPontos().getCampeonato()).compareTo(o1.getPontos().getCampeonato());
                    }
                });

                adapter = new RecyclerViewAdapter(LeagueActivity.this, list);

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<League> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
