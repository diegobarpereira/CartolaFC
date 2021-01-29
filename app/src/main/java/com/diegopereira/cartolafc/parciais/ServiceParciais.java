package com.diegopereira.cartolafc.parciais;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.CONSTANTS;
import com.diegopereira.cartolafc.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Provider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceParciais extends Service {
    private final IBinder mBinder = new MyBinder();

    public static ArrayList<Atletas> list;
    public static ArrayList<Atletas> testlist = new ArrayList<>();


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        loadParciais();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        loadParciais();
        return mBinder;
    }

    public class MyBinder extends Binder {
        public ServiceParciais getService() {
            return ServiceParciais.this;
        }
    }

    public List<Atletas> getList() {
        return testlist;
    }

    private void loadParciais() {
        final Gson gson = new GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(CONSTANTS.BASE_URL)
                .build();
        APIInterface requestInterface = retrofit.create(APIInterface.class);
        Call<Parciais> call = requestInterface.getAtletas();
        call.enqueue(new Callback<Parciais>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Parciais> call, Response<Parciais> response) {


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


                            testlist.addAll(list);
                            System.out.println("TESTLIST:" + testlist);



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



}
