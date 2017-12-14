package com.cm.trabalho.weather;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BuscasAnteriores extends AppCompatActivity {
    private ListView listBuscas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscas_anteriores);
        listBuscas = (ListView) findViewById(R.id.ltvBuscas);
        listBuscas.setAdapter(new CustomAdapter(this));
    }

    private class CustomAdapter extends BaseAdapter {

        private List<Previsao> previsoes;
        private LayoutInflater inflater;
        Context context;

        public CustomAdapter(Context context){
            PrevisaoDAO dao = new PrevisaoDAO(context);
            previsoes = dao.listarPrevisoes();
            dao.close();
            inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            this.context = context;
        }

        @Override
        public int getCount() {
            return previsoes.size();
        }

        @Override
        public Object getItem(int i) {
            return previsoes.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Holder holder = new Holder();
            View rowview;
            rowview = inflater.inflate(R.layout.buscas_list, null);
            holder.text = (TextView) rowview.findViewById(R.id.listBuscasText);
            holder.image = (ImageView) rowview.findViewById(R.id.listBuscasImage);
            holder.text.setText(previsoes.get(i).getDate() + " " + previsoes.get(i).getCondicao());
            rowview.setTag(i);
            String code = previsoes.get(i).getCode();
            if(code.equals("0") || code.equals("1") || code.equals("2")
                    || code.equals("3") || code.equals("4") || code.equals("45")){
                holder.image.setImageResource(R.mipmap.ic_t);
            }

            else if(code.equals("5") || code.equals("7") || code.equals("24")){
                holder.image.setImageResource(R.mipmap.ic_lr);
            }
            else if(code.equals("6") || code.equals("10") || code.equals("11")
                    || code.equals("40") || code.equals("35") || code.equals("37")
                    || code.equals("38") || code.equals("39")|| code.equals("47")){
                holder.image.setImageResource(R.mipmap.ic_hr);
            }
            else if(code.equals("12") ){
                holder.image.setImageResource(R.mipmap.ic_s);
            }
            else if(code.equals("8") || code.equals("9")){
                holder.image.setImageResource(R.mipmap.ic_h);
            }
            else if(code.equals("13") || code.equals("14") || code.equals("15")|| code.equals("16")
                    || code.equals("41")|| code.equals("42")|| code.equals("43")|| code.equals("46")){
                holder.image.setImageResource(R.mipmap.ic_sn);
            }
            else if(code.equals("17") || code.equals("18")){
                holder.image.setImageResource(R.mipmap.ic_sl);
            }
            else if(code.equals("19") || code.equals("20") || code.equals("21") || code.equals("22") || code.equals("23")){
                holder.image.setImageResource(R.mipmap.ic_hc);
            }
            else if(code.equals("25") || code.equals("31") || code.equals("26") || code.equals("27") || code.equals("28") || code.equals("29") || code.equals("30") || code.equals("44")){
                holder.image.setImageResource(R.mipmap.ic_lc);
            }
            else if(code.equals("32") || code.equals("36") || code.equals("33") || code.equals("34")){
                holder.image.setImageResource(R.mipmap.ic_c);
            }
            else{
                holder.image.setImageResource(R.mipmap.ic_launcher);
            }

            rowview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int)v.getTag();
                    Previsao previsao = (Previsao)getItem(position);
                    Intent intent = new Intent(BuscasAnteriores.this, ActivityResultados.class);
                    intent.putExtra("PREVISAO",previsao.getCode());
                    startActivity(intent);
                }
            });

            return rowview;
        }

        public class Holder
        {
            TextView text;
            ImageView image;
        }
    }
}
