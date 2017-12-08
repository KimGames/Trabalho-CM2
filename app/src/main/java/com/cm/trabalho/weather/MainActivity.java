package com.example.victor.appalunos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {
    private TextView nome;
    private TextView sobrenome;
    private TextView email;
    private TextView endereco;
    private TextView cidade;
    private TextView estado;
    private TextView username;
    private TextView senha;
    private TextView nascimento;
    private TextView telefone;
    private ImageView foto;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetJson download = new GetJson();

        nome = (TextView)findViewById(R.id.Nome);
        sobrenome = (TextView)findViewById(R.id.Sobrenome);
        foto = (ImageView)findViewById(R.id.Imagem);


        email = (TextView)findViewById(R.id.textView2);
        endereco = (TextView)findViewById(R.id.textView7);
        cidade = (TextView)findViewById(R.id.textView4);
        estado = (TextView)findViewById(R.id.textView3);
        username = (TextView)findViewById(R.id.textView2);
        senha = (TextView)findViewById(R.id.textView10);
        nascimento = (TextView)findViewById(R.id.textView9);
        telefone = (TextView)findViewById(R.id.textView12);


        //Chama Async Task
        download.execute();

    }

    private class GetJson extends AsyncTask<Void, Void, PessoaObj> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(MainActivity.this, "Por favor Aguarde ...", "Recuperando Informações do Servidor...");
        }


        @Override
        protected PessoaObj doInBackground(Void... params) {
            Utils util = new Utils();

            return util.getInformacao("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22uberlandia%2C%20mg%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
        }

        @Override
        protected void onPostExecute(PessoaObj pessoa){
            nome.setText(pessoa.getNome().substring(0,1).toUpperCase()+pessoa.getNome().substring(1));

            load.dismiss();
        }
    }
}