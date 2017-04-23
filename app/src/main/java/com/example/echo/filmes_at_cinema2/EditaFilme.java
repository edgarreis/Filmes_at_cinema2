package com.example.echo.filmes_at_cinema2;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
// TODO: Arrumar o Icone OkEdit

public class EditaFilme extends AppCompatActivity {

    // ArrayList, estrutura de dados contendo os Filmes
    ArrayList<Filme> alFilme;
    Filme filme;
    int filmePos;
    EditText fNome;
    EditText fGenero;
    EditText fLocal;
    EditText fComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_filme);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        // Acessa a ActionBar
        ActionBar ab = getSupportActionBar();
        // Seta retorno <- na action bar
        ab.setDisplayHomeAsUpEnabled(true);

        // Pega o Código do Filme
        filmePos = Integer.parseInt(extras.getString("FilmeId"));
        // Pega os Filmes
        alFilme = ListaFilmes.getsListaFilmes(getApplicationContext()).getAlFilmes();
        // Pega o Filme em questão
        filme = alFilme.get(filmePos);
        // Atualiza o nome do filme na ActionBar
        ab.setTitle(filme.getfNome());

        // Atualiza dados do Filme nas nas TextViews
        fNome = (EditText) findViewById(R.id.etNome);
        fNome.setText(filme.getfNome());

        fGenero = (EditText) findViewById(R.id.etGenero);
        fGenero.setText(filme.getfGenero());

        fLocal = (EditText) findViewById(R.id.etLocal);
        fLocal.setText(filme.getfLocal());

        TextView fData = (TextView) findViewById(R.id.tvData);
        fData.setText(filme.getDataString());

        fComentario = (EditText) findViewById(R.id.etComentario);
        fComentario.setText(filme.getfComentario());
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        //Popula a ActionBar com os Icones configurados em menu_dados_filme.xml
        getMenuInflater().inflate(R.menu.menu_edita_filme, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.miOkEdit:
                // TODO: Arrumar o Botão de Check SAVE
                filme.setfNome(fNome.getText().toString());
                filme.setfGenero(fGenero.getText().toString());
                filme.setfLocal(fLocal.getText().toString());
                filme.setfComentario(fComentario.getText().toString());

                // TODO: Verificar Por que tem esse Finish
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
