package com.diegopereira.cartolafc.login;

public class GloboPayload {
    private String email;
    private String password;
    private int serviceId;

    public GloboPayload(String email, String password, int serviceId) {
        this.email = email;
        this.password = password;
        this.serviceId = serviceId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
