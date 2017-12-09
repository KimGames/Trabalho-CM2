package com.cm.trabalho.weather;

import android.util.Log;
import android.widget.EditText;

public class FormularioHelper {

    private EditText city;
    private EditText state;

    private CidadeBEAN cidade;


    public FormularioHelper(PesquisaActivity activity){
        //Associacao de campos da tela a atributos de controle
        city = (EditText)activity.findViewById(R.id.edCidade);
        state = (EditText)activity.findViewById(R.id.edEstado);
        cidade = new CidadeBEAN();
        cidade.setRegion(state.getText().toString());

    }

    public CidadeBEAN getCidadeBEAN(){

        cidade.setCity(city.getText().toString());
        cidade.setRegion(state.getText().toString());

        return cidade;
    }

    public void setCidadeBEAN(CidadeBEAN cidade){
        city.setText(cidade.getCity());
        state.setText(cidade.getRegion());

        this.cidade = cidade;
    }




}


