package com.cm.trabalho.weather;

import android.util.Log;

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


    public String inserirEspacos(String str){
        str = str.replaceAll(" ","%20");
    return str;
    }


}
