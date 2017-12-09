package com.cm.trabalho.weather;

import java.io.Serializable;

public class Cidade implements Serializable{

    private Long id;
    private String lastBuildDate;
    private String city;
    private String country;
    private String region;

    public Previsao getPrevisao() {
        return previsao;
    }

    public void setPrevisao(Previsao previsao) {
        this.previsao = previsao;
    }

    private Previsao previsao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getLastBuildDate(){
        return lastBuildDate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }
}
