package com.diegopereira.cartolafc.parciais;

import android.renderscript.Sampler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface APIInterface {
   // @Headers("Content-Type: application/json")

    //@GET("/b/PSVE")
    @GET("/atletas/pontuados/32")
    Call<Parciais> getAtletas();

}