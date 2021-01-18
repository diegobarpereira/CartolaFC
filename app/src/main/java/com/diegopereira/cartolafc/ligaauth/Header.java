package com.diegopereira.cartolafc.ligaauth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Header {
    @SerializedName("header")
    @Expose
    private String header;

    public Header(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
