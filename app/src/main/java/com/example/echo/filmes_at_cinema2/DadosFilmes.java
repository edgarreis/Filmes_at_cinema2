package com.example.echo.filmes_at_cinema2;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DadosFilmes extends AppCompatActivity {

    // ArrayList, estrutura de dados contendo os filmes vistos
    ArrayList<Filme> alFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_filmes);

        Bundle extras = getIntent().getExtras();
        if (extras == null){
            return;
        }

        // Acessa a Action bar
        ActionBar ab = getSupportActionBar();
        // Seta Retorno na Action Bar
        ab.setDisplayHomeAsUpEnabled(true);

        // Pega código do Filme
        int filmePos = Integer.parseInt(extras.getString("filmeId"));
        // Pegar os Filmes
        alFilme = ListaFilmes.getsListaFilmes(getApplicationContext()).getAlFilmes();
        // alFilme pega o ArrayLista via método get (getAlFimes)
        // da lista filme atraves do método static getsListaFilmes

        // Pega o Filme em questão
        Filme filme = alFilme.get(filmePos);
        // Importante! esse é método get é da classe Array List que alFilme herdou

        // Atualiza nome do filme na action bar
        ab.setTitle(filme.getfNome());

        // Atualiza os dados do filme nas TextViews
        TextView fNome = (TextView) findViewById(R.id.tvNome);
        fNome.setText(filme.getfNome());

        TextView fLocal = (TextView) findViewById(R.id.tvLocal);
        fLocal.setText(filme.getfLocal());


        TextView fData = (TextView) findViewById(R.id.tvData);
        fData.setText(filme.getDataString());

        TextView fGenero = (TextView) findViewById(R.id.tvGenero);
        fGenero.setText(filme.getGenero());

        // Aparecer o Comentário apenas se Ouver
        ImageView ivComentario = (ImageView) findViewById(R.id.ivComentario);
        TextView fComentario = (TextView) findViewById(R.id.tvComentario);
        String sComentario = filme.getfComentario();

        if (sComentario.isEmpty()){
            ivComentario.setVisibility(View.INVISIBLE);
        } else
            fComentario.setText(sComentario);
    }

    // Verificar esse método !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
