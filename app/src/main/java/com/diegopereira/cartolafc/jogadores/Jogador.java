package com.diegopereira.cartolafc.jogadores;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Jogador {
    @SerializedName("atletas")
    @Expose
    private List<Atleta> atletas = null;
   /* @SerializedName("clubes")
    @Expose
    private Clubes clubes;
    @SerializedName("posicoes")
    @Expose
    private Posicoes posicoes;
    @SerializedName("status")
    @Expose
    private Status status; */

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(List<Atleta> atletas) {
        this.atletas = atletas;
    }

   /* public Clubes getClubes() {
        return clubes;
    }

    public void setClubes(Clubes clubes) {
        this.clubes = clubes;
    }

    public Posicoes getPosicoes() {
        return posicoes;
    }

    public void setPosicoes(Posicoes posicoes) {
        this.posicoes = posicoes;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    } */

}