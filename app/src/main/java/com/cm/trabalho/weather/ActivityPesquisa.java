package com.cm.trabalho.weather;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActivityPesquisa extends AppCompatActivity {
    private EditText cidade;
    private EditText estado;
    private Button botao;
    private Button btBuscasAnteriores;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pesquisa_layout);
        getSupportActionBar().hide();

        cidade = findViewById(R.id.edCidade);
        estado = findViewById(R.id.edEstado);
        botao = (Button) findViewById(R.id.bPesquisar);
        btBuscasAnteriores = (Button)findViewById(R.id.btBuscasAnteriores);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cidade c = new Cidade();
                if(cidade.getText().toString() != null && !cidade.getText().toString().equals("")
                        && estado.getText().toString() != null && !estado.getText().toString().equals("")){
                    c.setCity(cidade.getText().toString());
                    c.setState(estado.getText().toString());
                    Intent intent = new Intent(ActivityPesquisa.this,ActivityResultados.class);
                    intent.putExtra("PESQUISA",c);
                    startActivity(intent);
                    Log.i("TAG","BIROSCA");
                }else{
                    int duration = Toast.LENGTH_SHORT;
                    CharSequence text = "Preencha todos os campos";
                    Context context = getApplicationContext();


                    Toast toast = Toast.makeText(context,text,duration);
                    toast.show();
                }


            }
        });

        btBuscasAnteriores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPesquisa.this,BuscasAnteriores.class);
                startActivity(intent);
                Log.i("TAG","BIROSCA");

            }
        });
    }
}
