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

/**
 * Created by Kim on 08/12/2017.
 */

public class PrevisaoDAO extends SQLiteOpenHelper {

    //Constantes para auxilio no controle de versoes
    private static final int VERSAO = 1;
    private static final String TABELA = "Previsoes";
    private static final String DATABASE = "Cidades";
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
                + "condicao TEXT)";
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

    public void cadastrarPrevisao(Cidade cidade){

        //objeto para armazenar os valores dos campos
        ContentValues values = new ContentValues();
        //Definicao dos campos da tabela
        values.put("lastBuildDate", cidade.getLastBuildDate());
        values.put("city", cidade.getCity());
        values.put("country", cidade.getCountry());
        values.put("region", cidade.getRegion());
        //inserir dados da cidade no BD
        getWritableDatabase().insert(TABELA, null, values);
        Log.i(TAG, "Cidade cadastrada: " + cidade.getCity());
    }

    public List<Cidade> listarCidades(){

        List<Cidade> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + TABELA + " ORDER BY CITY";
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        try{

            while(cursor.moveToNext()){

                //Criacao de nova referencia para Cidade
                Cidade cidade = new Cidade();
                cidade.setId(cursor.getLong(0));
                cidade.setLastBuildDate(cursor.getString(1));
                cidade.setCity(cursor.getString(2));
                cidade.setCountry(cursor.getString(3));
                cidade.setRegion(cursor.getString(4));
                lista.add(cidade);
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

    public void deletarCidade(Cidade cidade){

        //definicao do array de parametros
        String[] args = {cidade.getId().toString()};
        //exclusao do aluno
        getWritableDatabase().delete(TABELA,"id=?", args);
        Log.i(TAG, "Cidade Deletada: " + cidade.getCity());
    }

    public void alterarCidade(Cidade cidade){

        ContentValues values = new ContentValues();
        values.put("lastBuildDate", cidade.getLastBuildDate());
        values.put("city", cidade.getCity());
        values.put("country", cidade.getCountry());
        values.put("region", cidade.getRegion());
        //Colecao de Valores de parametro do sql
        String[] args = {cidade.getId().toString()};
        //Altera dados da cidade no BD
        getWritableDatabase().update(TABELA, values,"id=?", args);
        Log.i(TAG, "Cidade Alterada: " + cidade.getCity() );
    }
}
