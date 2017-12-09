package com.cm.trabalho.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PesquisaActivity extends AppCompatActivity {
    private EditText cidade;
    private EditText estado;
    private Button botao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.formulario);
        cidade = findViewById(R.id.edCidade);
        estado = findViewById(R.id.edEstado);
        botao = (Button) findViewById(R.id.bPesquisar);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cidade c = new Cidade();
                c.setCity(cidade.getText().toString());
                c.setState(estado.getText().toString());

                Intent intent = new Intent(PesquisaActivity.this,PrevisaoActivity.class);
                intent.putExtra("PESQUISA",c);
                startActivity(intent);

            }
        });

    }


}
