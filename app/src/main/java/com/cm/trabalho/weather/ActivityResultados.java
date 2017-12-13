package com.cm.trabalho.weather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;


public class ActivityResultados extends Activity {
    private TextView city;
    private TextView state;
    private TextView country;
    private TextView data;
    private TextView condicao;
    private TextView tempmax;
    private TextView tempmin;
    private ImageView imagemIlustrativa;


    private Button button_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_layout);

        GetJson download = new GetJson();

        city = (TextView)findViewById(R.id.Cidade);
        state = (TextView)findViewById(R.id.Estado);
        country = (TextView)findViewById(R.id.Pais);
        data = (TextView)findViewById(R.id.Data);
        condicao = (TextView)findViewById(R.id.Condicao);
        tempmax = (TextView)findViewById(R.id.TempMax);
        tempmin = (TextView)findViewById(R.id.TempMin);
        imagemIlustrativa = (ImageView)findViewById(R.id.Imagem);

        button_voltar = (Button)findViewById(R.id.button_voltar);
        button_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //Chama Async Task
        download.execute();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private class GetJson extends AsyncTask<Void, Void, CidadeBEAN> {

        @Override
        protected void onPreExecute(){

        }


        @Override
        protected CidadeBEAN doInBackground(Void... params) {
            Utils util = new Utils();
            Cidade c = (Cidade) getIntent().getSerializableExtra("PESQUISA");
            String nome_cidade = c.getCity();
            String nome_estado = c.getState();
            nome_cidade = c.inserirEspacos(nome_cidade);
            nome_estado = c.inserirEspacos(nome_estado);

            return util.getInformacao("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+

                    nome_cidade +"%2C%20"+ nome_estado +"%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
        }



        @Override
        protected void onPostExecute(CidadeBEAN cidade){
try {


            city.setText(cidade.getCity().substring(0,1).toUpperCase()+cidade.getCity().substring(1));
            state.setText(cidade.getRegion().substring(0,1).toUpperCase()+cidade.getRegion().substring(1) );
            country.setText(cidade.getCountry().substring(0,1).toUpperCase()+cidade.getCountry().substring(1) );
            Previsao p = cidade.getPrevisao();

            CidadeDAO daoCity = new CidadeDAO(ActivityResultados.this);
            daoCity.cadastrarCidadeBEAN(cidade);
            daoCity.close();

            PrevisaoDAO dao = new PrevisaoDAO(ActivityResultados.this);
            dao.cadastrarPrevisao(p,cidade);
            dao.close();

            data.setText(p.getDate());
            condicao.setText(p.getCondicao());
            tempmax.setText(p.getHigh());
            tempmin.setText(p.getLow());


            String code = p.getCode();
            if(code.equals("0") || code.equals("1") || code.equals("2")
                    || code.equals("3") || code.equals("4") || code.equals("45")){
                imagemIlustrativa.setImageResource(R.mipmap.ic_t);
            }

            else if(code.equals("5") || code.equals("7") || code.equals("24")){
                imagemIlustrativa.setImageResource(R.mipmap.ic_lr);
            }
            else if(code.equals("6") || code.equals("10") || code.equals("11")
                    || code.equals("40") || code.equals("35") || code.equals("37")
                    || code.equals("38") || code.equals("39")|| code.equals("47")){
                imagemIlustrativa.setImageResource(R.mipmap.ic_hr);
            }
            else if(code.equals("12") ){
                imagemIlustrativa.setImageResource(R.mipmap.ic_s);
            }
            else if(code.equals("8") || code.equals("9")){
                imagemIlustrativa.setImageResource(R.mipmap.ic_h);
            }
            else if(code.equals("13") || code.equals("14") || code.equals("15")|| code.equals("16")
                    || code.equals("41")|| code.equals("42")|| code.equals("43")|| code.equals("46")){
                imagemIlustrativa.setImageResource(R.mipmap.ic_sn);
            }
            else if(code.equals("17") || code.equals("18")){
                imagemIlustrativa.setImageResource(R.mipmap.ic_sl);
            }
            else if(code.equals("19") || code.equals("20") || code.equals("21") || code.equals("22") || code.equals("23")){
                imagemIlustrativa.setImageResource(R.mipmap.ic_hc);
            }
            else if(code.equals("25") || code.equals("31") || code.equals("26") || code.equals("27") || code.equals("28") || code.equals("29") || code.equals("30") || code.equals("44")){
                imagemIlustrativa.setImageResource(R.mipmap.ic_lc);
            }
            else if(code.equals("32") || code.equals("36") || code.equals("33") || code.equals("34")){
                imagemIlustrativa.setImageResource(R.mipmap.ic_c);
            }
            else{
                imagemIlustrativa.setImageResource(R.mipmap.ic_launcher);
            }
}catch (Exception e){
    setContentView(R.layout.resultado_nao_encontrado);
}
        }
    }
}