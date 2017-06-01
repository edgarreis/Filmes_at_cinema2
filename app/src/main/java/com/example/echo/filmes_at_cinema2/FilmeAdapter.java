package com.example.echo.filmes_at_cinema2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FilmeAdapter extends ArrayAdapter<Filme> {

    ArrayList<Filme> alFilmes;
    private Context context;
    public GregorianCalendar fData;

    // TODO: Não entendi este Contrutor
    public FilmeAdapter(Context context, ArrayList<Filme> alFilmes) {
        super(context, R.layout.filme_item, alFilmes);
        this.alFilmes = alFilmes;
        this.context = context;
    }

    // TODO: Não entendi essa Reescrita
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Filme filme = this.alFilmes.get(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.filme_item, null);

        ImageView ivfImagem = (ImageView) convertView.findViewById(R.id.ivfImagem);
        //ivfImagem.setImageResource(filme.getfImagem());
        ivfImagem.setImageResource(R.mipmap.ic_launcher);

        TextView tvfNome = (TextView) convertView.findViewById(R.id.tvfNome);
        tvfNome.setText(filme.getfNome());

        //TextView tvfLocal = (TextView) convertView.findViewById(R.id.tvfLocal);
        //tvfLocal.setText(filme.getfLocalData());

        TextView tvfGenero = (TextView) convertView.findViewById(R.id.tvfGenero);
        tvfGenero.setText(filme.getfLocal());

        TextView tvfData = (TextView) convertView.findViewById(R.id.tvfData);
        tvfData.setText(filme.getfDataString());

        return convertView;
    }
}
