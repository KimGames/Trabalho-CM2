package com.cm.trabalho.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PesquisaActivity extends AppCompatActivity {
    private Button botao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.formulario);
        botao = (Button) findViewById(R.id.bPesquisar);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cidade p = new Cidade();
                p.setCity("oi");
                p.setState("k");

                Intent intent = new Intent(PesquisaActivity.this,PrevisaoActivity.class);
                intent.putExtra("PESQUISA",p);
                startActivity(intent);

            }
        });

    }


}
