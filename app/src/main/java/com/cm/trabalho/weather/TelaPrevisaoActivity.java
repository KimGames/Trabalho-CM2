package com.cm.trabalho.weather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;


public class TelaPrevisaoActivity extends Activity {
    private TextView city;
    private TextView state;
    private TextView country;
    private TextView data;
    private TextView condicao;
    private TextView tempmax;
    private TextView tempmin;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_previsao);

        GetJson download = new GetJson();

        city = (TextView)findViewById(R.id.Cidade);
        state = (TextView)findViewById(R.id.Estado);
        country = (TextView)findViewById(R.id.Pais);
        condicao = (TextView)findViewById(R.id.Condicao);
        tempmax = (TextView)findViewById(R.id.TempMax);
        tempmin = (TextView)findViewById(R.id.TempMin);

        //Chama Async Task
        download.execute();

    }

    private class GetJson extends AsyncTask<Void, Void, Cidade> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(TelaPrevisaoActivity.this, "Por favor Aguarde ...", "Recuperando Informações...");
        }


        @Override
        protected Cidade doInBackground(Void... params) {
            Utils util = new Utils();

            return util.getInformacao("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22uberlandia%2C%20mg%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
        }

        @Override
        protected void onPostExecute(Cidade cidade){
            city.setText(cidade.getCity().substring(0,1).toUpperCase()+cidade.getCity().substring(1));
            state.setText(cidade.getRegion().substring(0,1).toUpperCase()+cidade.getRegion().substring(1) );
            country.setText(cidade.getCountry().substring(0,1).toUpperCase()+cidade.getCountry().substring(1) );
            Previsao p = cidade.getPrevisao();

            data.setText(p.getDate());
            condicao.setText(p.getCondicao());
            tempmax.setText(p.getHigh());
            tempmin.setText(p.getLow());

            load.dismiss();
        }
    }
}