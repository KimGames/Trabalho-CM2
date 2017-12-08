package com.example.victor.appalunos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;

public class FormularioActivity extends AppCompatActivity {
    private Button botao;
    private FormularioHelper helper;
    private Aluno alunoParaSerAlterado = null;

    //Variaveis para o controle da camera
    private String localArquivo;
    private static final int FAZER_FOTO = 123;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.formulario);

                EditText edNome = (EditText)findViewById(R.id.edNome);
                EditText edTelefone = (EditText)findViewById(R.id.edTelefone);
                EditText edEmail = (EditText)findViewById(R.id.edEmail);
                EditText edEndereco = (EditText)findViewById(R.id.edEndereco);

                Aluno aluno = new Aluno();
                aluno.setNome(edNome.getText().toString());
                aluno.setTelefone(edTelefone.getText().toString());
                aluno.setEmail(edEmail.getText().toString());
                aluno.setEndereco(edEndereco.getText().toString());

                helper = new FormularioHelper(this);
                botao = (Button) findViewById(R.id.sbSalvar);


                //Listener da foto
                helper.getFoto().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        localArquivo = Environment.getExternalStorageDirectory()
                                + "/" + System.currentTimeMillis() + ".jpg";
                        File arquivo = new File(localArquivo);
                        //URI que informa onde o arquivo resultado deve
                        Uri localFoto = Uri.fromFile(arquivo);
                        Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT,localFoto);

                        Log.i("LOG_FOTO","ALGUMAS CONSTATES AQUI <<<<<<<<<< : " + localArquivo + " " + localFoto.toString());

                        startActivityForResult(irParaCamera,FAZER_FOTO);
                    }
                });


                //Busca o aluno a ser alterado
                alunoParaSerAlterado = (Aluno) getIntent().getSerializableExtra("ALUNO_SELECIONADO");

                if (alunoParaSerAlterado !=  null){
                    //Atualiza a tela com os dados do aluno
                    Log.i("ALUNO_SELECIONADO","ALUNO SELECIONADO: " + alunoParaSerAlterado.getNome() );
                    helper.setAluno(alunoParaSerAlterado);
                }


                botao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //utilizando helper
                        Aluno aluno1 = helper.getAluno();
                        Toast.makeText(FormularioActivity.this,aluno1.getNome(),Toast.LENGTH_LONG).show();

                        AlunoDAO dao = new AlunoDAO(FormularioActivity.this);

                        //verificacao para salvar ou cadastrar o aluno
                        if(aluno1.getId()==null){
                            dao.cadastrar(aluno1);
                        }else{
                            dao.alterar(aluno1);
                        }

                        dao.close();
                        // encerra a Activity
                        finish();
                        Log.i("CADASTRO_ALUNO","Formulário Encerrado");
                    }
                });

        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Verificacao do resultado da nossa requisicao
        if(requestCode == FAZER_FOTO){
            if(resultCode == Activity.RESULT_OK){
                Log.i("LOCAL_ARQUIVO_FOTO","LOCAL ARQUIVO >>> " + localArquivo);
                helper.carregarFoto(this.localArquivo);
            }
            else{
                localArquivo = null;
            }
        }


    }
}
