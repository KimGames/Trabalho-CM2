package com.cm.trabalho.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public CidadeBEAN getInformacao(String end){
        String json;
        CidadeBEAN retorno;
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i(">>>>>>>>>> Resultado", json);
        retorno = parseJson(json);

        return retorno;
    }

    private CidadeBEAN parseJson(String json){
        try {
            CidadeBEAN cidade = new CidadeBEAN();

            JSONObject jsonObj = new JSONObject(json);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data;


            JSONObject channel = jsonObj.getJSONObject("query")
                    .getJSONObject("results")
                    .getJSONObject("channel");

            JSONObject obj1 = channel
                    .getJSONObject("location");

            cidade.setCity(obj1.getString("city"));
            cidade.setRegion(obj1.getString("region"));
            cidade.setCountry(obj1.getString("country"));




            JSONObject obj2 = channel
                    .getJSONObject("item");
            JSONArray forecast = obj2.getJSONArray("forecast");

            JSONObject previsoes = forecast.getJSONObject(0);
            Previsao p = cidade.getPrevisao();
            p.setDate(previsoes.getString("date"));
            p.setDay(previsoes.getString("day"));
            p.setCondicao(previsoes.getString("text"));


            String high = previsoes.getString("high");
            String low = previsoes.getString("low");
            p.setHigh(converterFC(high));
            p.setLow(converterFC(low));
            p.setCodeTemp(previsoes.getString("code"));



            return cidade;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public String converterFC(String tmp){
        double t = Double.parseDouble(tmp);
        DecimalFormat df = new DecimalFormat("#.##");
        t = (t-32)/1.8;
        return df.format(t);

    }

    private Bitmap baixarImagem(String url) {
        try{
            URL endereco;
            InputStream inputStream;
            Bitmap imagem; endereco = new URL(url);
            inputStream = endereco.openStream();
            imagem = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return imagem;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}