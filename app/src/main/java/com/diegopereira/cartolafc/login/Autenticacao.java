package com.diegopereira.cartolafc.login;

public class Autenticacao {
    private GloboPayload payload;

    public Autenticacao(GloboPayload payload) {
        this.payload = payload;
    }

    public GloboPayload getPayload() {
        return payload;
    }

    public void setPayload(GloboPayload payload) {
        this.payload = payload;
    }
}
