package com.diegopereira.cartolafc;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.diegopereira.cartolafc.parciais.Atletas;
import com.diegopereira.cartolafc.parciais.ParciaisRecyclerAdapter;
import com.diegopereira.cartolafc.parciais.ServiceParciais;
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

public class JogosActivityBkp extends AppCompatActivity implements ServiceConnection {

    private ServiceParciais s;

    private List<Atletas> atletasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parciais);

        atletasList = new ArrayList<>();



        //Intent service = new Intent(getApplicationContext(), ServiceParciais.class);
        //getApplicationContext().startService(service);

        if (s != null) {
            System.out.println(s.getList());
        } else {
            System.out.println("S:" + s.toString());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, ServiceParciais.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }


    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ServiceParciais.MyBinder b = (ServiceParciais.MyBinder) iBinder;
        s = b.getService();
        System.out.println("TESTE: " + atletasList.size());


    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }




}




