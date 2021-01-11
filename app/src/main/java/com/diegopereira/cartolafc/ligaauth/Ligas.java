package com.diegopereira.cartolafc.ligaauth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ligas {
    @SerializedName("liga_id")
    @Expose
    private Integer liga_id;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("url_flamula_png")
    @Expose
    private String url_flamula_png;
    @SerializedName("total_times_liga")
    @Expose
    private Integer total_times_liga;

    public Integer getLiga_id() {
        return liga_id;
    }

    public void setLiga_id( Integer liga_id ) {
        this.liga_id = liga_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug( String slug ) {
        this.slug = slug;
    }

    public String getUrl_flamula_png() {
        return url_flamula_png;
    }

    public void setUrl_flamula_png( String url_flamula_png ) {
        this.url_flamula_png = url_flamula_png;
    }

    public Integer getTotal_times_liga() {
        return total_times_liga;
    }

    public void setTotal_times_liga( Integer total_times_liga ) {
        this.total_times_liga = total_times_liga;
    }
}
