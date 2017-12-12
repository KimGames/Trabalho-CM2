package com.cm.trabalho.weather;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class PrevisaoDAO extends SQLiteOpenHelper {

    //Constantes para auxilio no controle de versoes
    private static final int VERSAO = 1;
    private static final String TABELA = "Previsoes";
    private static final String DATABASE = "CidadeBEANs";
    //Constante para logcat
    private static final String TAG = "CADASTRO_PREVISAO";

    public PrevisaoDAO(Context context){
        super(context, DATABASE,null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        //Definicao do comando DDL a ser executado
        String ddl = "CREATE TABLE " + TABELA + "( "
                + "code TEXT PRIMARY KEY, "
                + "date TEXT, "
                + "day TEXT, "
                + "high TEXT, "
                + "low TEXT, "
                + "condicao TEXT, ";
        //EXECUCAO
        database.execSQL(ddl);
    }

    //Metodo responsavel pela atualizacao das estruturas das tabelas
    @Override
    public void onUpgrade(SQLiteDatabase database, int versaoAntiga, int versaoNova) {

        // Definicao do coamando para destruir a tabela previsao
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        Log.i(TAG,"TABELA DROPADA");
        //Execucao
        database.execSQL(sql);
        //Chamada ao metodo de construcao da base de dados
        onCreate(database);
    }

    public void cadastrarPrevisao(Previsao previsao){

        //objeto para armazenar os valores dos campos
        ContentValues values = new ContentValues();
        //Definicao dos campos da tabela
        values.put("code", previsao.getCode());
        values.put("date", previsao.getDate());
        values.put("day", previsao.getDay());
        values.put("high", previsao.getHigh());
        values.put("low", previsao.getLow());
        values.put("condicao", previsao.getCondicao());
        //inserir dados da previsao no BD
        getWritableDatabase().insert(TABELA, null, values);
        Log.i(TAG, "Previsao cadastrada: " + previsao.getCondicao());
    }

    public List<Previsao> listarPrevisoes(){

        List<Previsao> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + TABELA + " ORDER BY CIDADE_ID";
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        try{

            while(cursor.moveToNext()){

                //Criacao de nova referencia para Previsao
                Previsao previsao = new Previsao();
                previsao.setCode(cursor.getString(0));
                previsao.setDate(cursor.getString(1));
                previsao.setDay(cursor.getString(2));
                previsao.setHigh(cursor.getString(3));
                previsao.setLow(cursor.getString(4));
                previsao.setCondicao(cursor.getString(5));
                lista.add(previsao);
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

    public void deletarPrevisao(Previsao previsao){

        //definicao do array de parametros
        String[] args = {previsao.getCode().toString()};
        //exclusao do aluno
        getWritableDatabase().delete(TABELA,"id=?", args);
        Log.i(TAG, "Previsao Deletada: " + previsao.getCondicao());
    }

    public void alterarPrevisao(Previsao previsao){

        ContentValues values = new ContentValues();
        values.put("code", previsao.getCode());
        values.put("date", previsao.getDate());
        values.put("day", previsao.getDay());
        values.put("high", previsao.getHigh());
        values.put("low", previsao.getLow());
        values.put("condicao", previsao.getCondicao());
        //Colecao de Valores de parametro do sql
        String[] args = {previsao.getCode().toString()};
        //Altera dados da cidade no BD
        getWritableDatabase().update(TABELA, values,"id=?", args);
        Log.i(TAG, "CidadeBEAN Alterada: " + previsao.getCondicao() );
    }
}
