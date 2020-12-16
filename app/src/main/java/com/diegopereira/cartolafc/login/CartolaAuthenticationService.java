package com.diegopereira.cartolafc.login;

import org.springframework.web.client.RestTemplate;

public class CartolaAuthenticationService {
    private Token token;

    public Token getToken(String email,String password) {

        System.out.println(" Executando auth");
        GloboPayload payload = new GloboPayload(email,password,438);
        Autenticacao auth = new Autenticacao(payload);
        RestTemplate client = new RestTemplate();
        this.token = client.postForObject("https://login.globo.com/api/authentication", auth, Token.class);

        System.out.println(" Token auth:"+ token.getGlbId());

        System.out.println(" Fim auth");

        return token;
    }

    public Token getToken() {
        String email = "diegobarpereira@gmail.com";
        String password = "eky0618GolG5";
        GloboPayload payload = new GloboPayload(email,password,438);
        Autenticacao auth = new Autenticacao(payload);
        RestTemplate client = new RestTemplate();
        this.token = client.postForObject("https://login.globo.com/api/authentication", auth, Token.class);
        return token;
    }
}
