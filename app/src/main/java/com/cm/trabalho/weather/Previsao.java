package com.cm.trabalho.weather;

/**
 * Created by Kim on 08/12/2017.
 */

public class Previsao {

    private int code;
    private String codeTemp;
    private String date;
    private String day;
    private String high;
    private String low;
    private String condicao;

    public int getCode() {
        return code;
    }
    public String getCodeTemp(){
        return this.codeTemp;
    }

    public void setCodeTemp(String code){this.codeTemp = code;}

    public void setCode(int code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

}
