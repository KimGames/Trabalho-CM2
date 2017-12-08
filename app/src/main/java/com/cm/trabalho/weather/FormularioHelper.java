package com.example.victor.appalunos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

public class FormularioHelper {

    private EditText nome;
    private EditText telefone;
    private EditText endereco;
    private EditText email;
    private SeekBar nota;
    private ImageView foto;
    private Aluno aluno;

        public FormularioHelper(FormularioActivity activity){
            //Associacao de campos da tela a atributos de controle
            nome = (EditText)activity.findViewById(R.id.edNome);
            telefone = (EditText)activity.findViewById(R.id.edTelefone);
            email = (EditText)activity.findViewById(R.id.edEmail);
            endereco = (EditText)activity.findViewById(R.id.edEndereco);
            foto = (ImageView) activity.findViewById(R.id.foto);

            aluno = new Aluno();
        }

        public Aluno getAluno(){
            aluno.setNome(nome.getText().toString());
            aluno.setTelefone(telefone.getText().toString());
            aluno.setEmail(email.getText().toString());
            aluno.setEndereco(endereco.getText().toString());
            return aluno;
        }

        public void setAluno(Aluno aluno){
            nome.setText(aluno.getNome());
            telefone.setText(aluno.getTelefone());
            endereco.setText(aluno.getEndereco());
            email.setText(aluno.getEmail());

            this.aluno = aluno;

            //Carregar a foto do aluno

            if(aluno.getFoto() != null){
                Log.i("LOG","IMAGEM1: " );
                carregarFoto(aluno.getFoto());
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


            //Guarda o caminho da foto do aluno
            aluno.setFoto(localFoto);

            //Atualiza a imagem exibida na tela de formulario
            foto.setImageBitmap(imagemFotoReduzida);
            Log.i("LOG","IMAGEM: " );



        }

}
