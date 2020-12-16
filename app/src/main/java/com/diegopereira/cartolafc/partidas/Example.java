package com.diegopereira.cartolafc.partidas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {

    @SerializedName("partidas")
    @Expose
    private List<Partida> partidas = null;
    @SerializedName("rodada")
    @Expose
    private Integer rodada;


    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public Example withPartidas(List<Partida> partidas) {
        this.partidas = partidas;
        return this;
    }

    public Integer getRodada() {
        return rodada;
    }

    public void setRodada(Integer rodada) {
        this.rodada = rodada;
    }

    public Example withRodada(Integer rodada) {
        this.rodada = rodada;
        return this;
    }



}