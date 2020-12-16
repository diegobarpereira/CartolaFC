package com.diegopereira.cartolafc.parciais;

import com.diegopereira.cartolafc.liga.Posicoes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Parciais {
    @SerializedName("rodada")
    @Expose
    private Integer rodada;
    @SerializedName("atletas")
    @Expose
    private Map<String, Atletas> atletas;
    @SerializedName("posicoes")
    @Expose
    private Posicoes posicoes;

    public Integer getRodada() {
        return rodada;
    }

    public void setRodada(Integer rodada) {
        this.rodada = rodada;
    }

    public Map<String, Atletas> getAtletas() {
        return atletas;
    }

    public void setAtletas(Map<String, Atletas> atletas) {
        this.atletas = atletas;
    }

    public Posicoes getPosicoes() {
        return posicoes;
    }

    public void setPosicoes(Posicoes posicoes) {
        this.posicoes = posicoes;
    }
}
