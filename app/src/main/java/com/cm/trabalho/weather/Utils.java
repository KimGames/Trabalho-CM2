package com.cm.trabalho.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public Cidade getInformacao(String end){
        String json;
        Cidade retorno;
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i(">>>>>>>>>> Resultado", json);
        retorno = parseJson(json);

        return retorno;
    }

    private Cidade parseJson(String json){
        try {
            Cidade cidade = new Cidade();

            JSONObject jsonObj = new JSONObject(json);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data;


            JSONObject obj = jsonObj.getJSONObject("query")
                    .getJSONObject("results")
                    .getJSONObject("channel")
                    .getJSONObject("location");
            cidade.setCity(obj.getString("city"));
            cidade.setRegion(obj.getString("region"));
            cidade.setCountry(obj.getString("country"));



               return cidade;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
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