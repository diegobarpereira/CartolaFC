package com.diegopereira.cartolafc.login;

import com.diegopereira.cartolafc.partidas.Example;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {


    @GET("/?p=login&login=diegobarpereira@gmail.com&password=eky0618GolG5")
    Call<User> getLogin();

}
