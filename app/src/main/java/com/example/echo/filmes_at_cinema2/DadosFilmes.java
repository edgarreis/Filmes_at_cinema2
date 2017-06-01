package com.example.echo.filmes_at_cinema2;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class DadosFilmes extends AppCompatActivity {

    // ArrayList, estrutura de dados contendo os filmes vistos
    ArrayList<Filme> alFilme;
    int filmePos;
    ActionBar ab;
    Bundle extras;
    Filme filme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_filmes);

        extras = getIntent().getExtras();
        if (extras == null){
            return;
        }

        // Acessa a Action bar
        ab = getSupportActionBar();
        // Seta Retorno na Action Bar
        ab.setDisplayHomeAsUpEnabled(true);

        // Pega código do Filme
        filmePos = Integer.parseInt(extras.getString("FilmeId"));
        this.mostraDadosFilme();


    }

    @Override
    public void onResume(){
        super.onResume();
        this.mostraDadosFilme();
    }

    public void mostraDadosFilme() {

        // Pegar os Filmes
        alFilme = ListaFilmes.getsListaFilmes(getApplicationContext()).getAlFilmes();
        // alFilme pega o ArrayLista via método get (getAlFimes)
        // da lista filme atraves do método static getsListaFilmes

        // Pega o Filme em questão
        filme = alFilme.get(filmePos);
        // Importante! esse é método get é da classe Array List que alFilme herdou

        // Atualiza nome do filme na action bar
        ab.setTitle(filme.getfNome());

        // Atualiza os dados do filme nas TextViews
        TextView fNome = (TextView) findViewById(R.id.tvNome);
        fNome.setText(filme.getfNome());

        TextView fLocal = (TextView) findViewById(R.id.tvLocal);
        fLocal.setText(filme.getfLocal());


        TextView fData = (TextView) findViewById(R.id.tvData);
        fData.setText(filme.getfDataString());

        TextView fGenero = (TextView) findViewById(R.id.tvGenero);
        fGenero.setText(filme.getfGenero());

        // Aparecer o Comentário apenas se Ouver
        ImageView ivComentario = (ImageView) findViewById(R.id.ivComentario);
        TextView fComentario = (TextView) findViewById(R.id.tvComentario);
        String sComentario = filme.getfComentario();
        if (sComentario.isEmpty()) {
            ivComentario.setVisibility(View.INVISIBLE);
            fComentario.setText("");
        }
        else {
            ivComentario.setVisibility(View.VISIBLE);
            fComentario.setText(sComentario);
        }

    }

    public boolean onCreateOptionsMenu(Menu menu){
        // Popula a ActionBar com os Icones configurados em menu_dados_filme
        getMenuInflater().inflate(R.menu.menu_dados_filme, menu);
        return true;
    }

    // TODO: Verificar esse método
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                this.finish();
                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);

                return true;

            case R.id.miShare:
                // Compartilha os dados deste evento
                // Monta a String usando stringBuilder
                StringBuilder strShare = new StringBuilder(200);
                strShare.append("Assisti o Filme ");
                strShare.append(filme.getfNome());
                strShare.append(" em ");
                strShare.append(filme.getfDataString());
                strShare.append(" no ");
                strShare.append(filme.getfLocal());

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, strShare.toString());
                startActivity(Intent.createChooser(i, "Compartilhar o Filme Visto"));
                return true;

            case R.id.miEditar:

                // Chama a Activity para Editar os dados deste filme
                Intent i2 = new Intent(this, EditaFilme.class);
                i2.putExtra("filmeId", filmePos);
                // 2 identifica que é edição de filme existente
                i2.putExtra("from", 2);
                startActivity(i2);
                return true;


//                // Chama a Activity para Editar os dados deste filme
//                Intent i2 = new Intent(this.getBaseContext(), EditaFilme.class);
//                i2.putExtra("filmeId", String.valueOf(filmePos));
//                // 2 identifica que é edição de filme existente
//                i2.putExtra("from", "2");
//                startActivity(i2);
//                return true;

            case R.id.miRemover:
                // Remove o  Filmes do ArrayList
                alFilme.remove(filme);
                Toast.makeText(getApplicationContext(),"O Filme " + filme.getfNome() + " foi removido", Toast.LENGTH_LONG).show();
                ListaFilmes.salvar(); // chama o metodo static para salvar a Lista de Filmes em disco
                // pois houve alteração de algum existente ou inclusão de um novo
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
