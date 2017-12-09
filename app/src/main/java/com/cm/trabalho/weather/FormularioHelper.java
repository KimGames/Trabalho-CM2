package com.cm.trabalho.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

public class FormularioHelper {

    private EditText city;
    private EditText state;
    private EditText country;
    private Cidade cidade;

    public FormularioHelper(FormularioActivity activity){
        //Associacao de campos da tela a atributos de controle
        city = (EditText)activity.findViewById(R.id.Cidade);
        state = (EditText)activity.findViewById(R.id.Estado);
        country = (EditText)activity.findViewById(R.id.Pais);
        cidade = new Cidade();
    }

    public Cidade getCidade(){
        cidade.setCity(city.getText().toString());
        cidade.setRegion(state.getText().toString());
        cidade.setCountry(country.getText().toString());
        return cidade;
    }

    public void setCidade(Cidade cidade){
        city.setText(cidade.getCity());
        state.setText(cidade.getRegion());
        country.setText(cidade.getCountry());

        this.cidade = cidade;

        //Carregar a foto do cidade
        /*
        if(cidade.getFoto() != null){
            Log.i("LOG","IMAGEM1: " );
            carregarFoto(cidade.getFoto());
        }


    }

    public ImageView getFoto(){
        return foto;
    }


    public void carregarFoto(String localFoto){

        //Carregar o arquivo de imagem
        Bitmap imagemFoto = BitmapFactory.decodeFile(localFoto);

        Bitmap imagemFotoReduzida =
                Bitmap.createScaledBitmap(imagemFoto,100,100,true);


        //Guarda o caminho da foto do cidade
        cidade.setFoto(localFoto);

        //Atualiza a imagem exibida na tela de formulario
        foto.setImageBitmap(imagemFotoReduzida);
        Log.i("LOG","IMAGEM: " );
    */



    }

}
