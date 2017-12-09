package com.cm.trabalho.weather;

import java.io.Serializable;

public class Cidade implements Serializable{

    private String city;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
