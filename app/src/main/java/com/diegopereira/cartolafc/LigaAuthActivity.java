package com.diegopereira.cartolafc;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.diegopereira.cartolafc.liga.Atleta;
import com.diegopereira.cartolafc.liga.LigaRecyclerAdapter;
import com.diegopereira.cartolafc.ligaauth.AuthLiga;
import com.diegopereira.cartolafc.ligaauth.Ligas;
import com.diegopereira.cartolafc.ligaauth.RecyclerViewAdapter;
import com.diegopereira.cartolafc.login.LigaGenerator;
import com.diegopereira.cartolafc.login.RequestInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.diegopereira.cartolafc.LoginActivity.SHARED_PREF_NAME;
import static com.diegopereira.cartolafc.LoginActivity.SHARED_TOKEN;
import static com.diegopereira.cartolafc.teste.RecyclerViewAdapter.ID_SHARED_PREF;
import static com.diegopereira.cartolafc.teste.RecyclerViewAdapter.SHARED_PREF_ID;

public class LigaAuthActivity extends AppCompatActivity {
    SharedPreferences preferences;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    List<Ligas> ligas = new ArrayList<>();
    String token;
    private ProgressBar loadProgress;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ligaauth);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh9);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadLigas(); // your code
                pullToRefresh.setRefreshing(false);
            }
        });

        loadProgress = (ProgressBar) findViewById(R.id.ligaprogressBar);
        recyclerView = findViewById(R.id.rv_ligaauth);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        preferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        token = preferences.getString(SHARED_TOKEN, "");

        System.out.println("token: " + token);

        loadLigas();


    }

    private void loadLigas() {
        RequestInterface requestInterface = LigaGenerator.getRetrofit().create(RequestInterface.class);
        Call<ResponseBody> call2 = requestInterface.getLigas(token);
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse( Call<ResponseBody> call, retrofit2.Response<ResponseBody> response ) {
                try {
                    System.out.println(response.body().string());

                    com.diegopereira.cartolafc.ligaauth.RequestInterface requestInterface1 = ServiceGenerator.getRetrofit().create(com.diegopereira.cartolafc.ligaauth.RequestInterface.class);
                    Call<AuthLiga> call3 = requestInterface1.getLigas(token);
                    call3.enqueue(new Callback<AuthLiga>() {
                        @Override
                        public void onResponse( Call<AuthLiga> call, Response<AuthLiga> response ) {
                            loadProgress.setVisibility(View.GONE);

                            System.out.println(response.body().getLigas());
                            ligas = response.body().getLigas();

                            adapter = new RecyclerViewAdapter(getApplicationContext(), ligas);
                            adapter.notifyDataSetChanged();
                            recyclerView.setAdapter(adapter);

                        }

                        @Override
                        public void onFailure( Call<AuthLiga> call, Throwable t ) {

                        }
                    });



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure( Call<ResponseBody> call, Throwable t ) {

            }
        });
    }
}
