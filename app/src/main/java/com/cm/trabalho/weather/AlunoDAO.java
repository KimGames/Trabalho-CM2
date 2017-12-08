package com.example.victor.appalunos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO extends SQLiteOpenHelper{
    //Constantes para auxilio no controle de versoes
    private static final int VERSAO = 1;
    private static final String TABELA = "Aluno";
    private static final String DATABASE = "Alunos";

    //Constante para logcat
    private static final String TAG = "CADASTRO_ALUNO";


        public AlunoDAO(Context context){
            super(context,DATABASE,null,VERSAO);
        }


        @Override
        public void onCreate(SQLiteDatabase database) {
                //Definicao do comando DDL a ser executado
                String ddl = "CREATE TABLE " + TABELA + "( "
                        + "id INTEGER PRIMARY KEY, "
                        + "nome TEXT, telefone TEXT, endereco TEXT, "
                        + "email TEXT,"
                        + "foto TEXT, "
                        + "nota REAL)";
                //EXECUCAO
                database.execSQL(ddl);
        }

        //Metodo responsavel pela atualizacao das estruturas das tabelas
        @Override
        public void onUpgrade(SQLiteDatabase database, int versaoAntiga, int versaoNova) {
                // Definicao do coamando para destruir a tabela aluno
                String sql = "DROP TABLE IF EXISTS " + TABELA;
                Log.i(TAG,"TABELA DROPADA");
                //Execucao
                database.execSQL(sql);

                //Chamada ao metodo de construcao da base de dados
                onCreate(database);
        }


        public void cadastrar(Aluno aluno){
                //objeto para armazenar os valores dos campos

            ContentValues values = new ContentValues();

            //Definicao dos campos da tabela
            values.put("nome", aluno.getNome());
            values.put("telefone", aluno.getTelefone());
            values.put("endereco", aluno.getEndereco());
            values.put("email", aluno.getEmail());
            values.put("foto", aluno.getFoto());


            //inserir dados do aluno no BD
            getWritableDatabase().insert(TABELA, null, values);
            Log.i(TAG, "Aluno cadastrado: " + aluno.getNome());


        }


        public List<Aluno> listar(){
            List<Aluno> lista = new ArrayList<Aluno>();

            String sql = "SELECT * FROM Aluno ORDER BY NOME";

            Cursor cursor = getReadableDatabase().rawQuery(sql,null);

            try{
                while(cursor.moveToNext()){
                    //Criacao de nova referencia para Aluno
                    Aluno aluno = new Aluno();

                    aluno.setId(cursor.getLong(0));
                    aluno.setNome(cursor.getString(1));
                    aluno.setTelefone(cursor.getString(2));
                    aluno.setEndereco(cursor.getString(3));
                    aluno.setEmail(cursor.getString(4));
                    aluno.setFoto(cursor.getString(5));



                    lista.add(aluno);
                }
            }
            catch (SQLException e ){
                Log.e(TAG, e.getMessage());
            }
            finally {
                cursor.close();
            }

            return lista;
        }

        public void deletar(Aluno aluno){
            //definicao do array de parametros
            String[] args = {aluno.getId().toString()};
            //exclusao do aluno
            getWritableDatabase().delete(TABELA,"id=?",args);
            Log.i(TAG,"Aluno Deletado: " + aluno.getNome());
        }


        public void alterar(Aluno aluno){
            ContentValues values = new ContentValues();
            values.put("nome",aluno.getNome());
            values.put("telefone",aluno.getTelefone());
            values.put("endereco",aluno.getEndereco());
            values.put("email", aluno.getEmail());
            values.put("foto",aluno.getFoto());

        //Colecao de Valores de parametro do sql
        String[] args = {aluno.getId().toString()};

        //Altera dados do aluno no BD
        getWritableDatabase().update(TABELA,values,"id=?",args);
        Log.i(TAG,"Aluno Alterado: " + aluno.getNome() );
        }



}
