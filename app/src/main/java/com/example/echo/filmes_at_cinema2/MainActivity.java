package com.example.echo.filmes_at_cinema2;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;

// icone da aplicação alterado em mipmap com arquivos correspondentes a cada resolução
// sugestão de utilitário para icones dos apps: http://romannurik.github.io/AndroidAssetStudio/
// cria os diversos formatos a partir de imagem e outras funções

// TODO: Utilizar o Spiner para gerar a lista de Cinemas


public class MainActivity extends AppCompatActivity {

    // Para ordenação alfabética
    boolean AZ = true;
    boolean crono = true;

    // ArrayList estrutura de dados contendo os filmes vistos
    ArrayList<Filme> alFilmes;
    // ArrayAdapter é um adapter para vincular arrays e views, pode ser usado com list views e spinners
    // ArrayAdapter<Filme> aaListaFilmes;
    FilmeAdapter faListaFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pega os Filmes
        alFilmes = ListaFilmes.getsListaFilmes(getApplicationContext()).getAlFilmes();

        //Associa o Elemento da UI com a variável
        ListView lvListaFilmes = (ListView) findViewById(R.id.lvListaFilmes);
        // Utiliza o ArrayAdatpar para mostrar os dados do ArrayList de filmes alFilmes
        //aaListaFilmes = new ArrayAdapter<Filme>(this, android.R.layout.simple_list_item_1, alFilmes);
        faListaFilmes = new FilmeAdapter(this, alFilmes);
        // Mostrá os Filmes listview lvListaFilmes, setada com o método setAdapter
        //lvListaFilmes.setAdapter(aaListaFilmes);
        lvListaFilmes.setAdapter(faListaFilmes);
        // Usa o retorno do toString para determinar o texto que será exibido em cada item da lista

        // criar um listener para os eventos de onClick nos items da listview
        lvListaFilmes.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                        Intent i = new Intent(view.getContext(), DadosFilmes.class);
                        i.putExtra("FilmeId", String.valueOf(posicao));
                        startActivity(i);
                    }
                }
        );

        // adiciona o Floating Action Button para adicionar filmes
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),EditaFilme.class);
                i.putExtra("filmeId", 0);
                // 1 identifica que é inclusão de novo filme
                i.putExtra("from", 1);
                startActivity(i);
            }
        });

        ActionBar ab = getSupportActionBar(); // acessa a action bar
        ab.setDisplayHomeAsUpEnabled(true);   // seta retorno <- na action bar
        ab.setTitle("Filmes App");

    }

    // mais info sobre ActionBar e Toolbars em
    // https://developer.android.com/training/appbar/index.html
    public boolean onCreateOptionsMenu(Menu menu) {
        // popula a action bar com os icones configurado em menu_main.xml
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onResume(){
        super.onResume();
        //aaListaFilmes.notifyDataSetChanged();
        faListaFilmes.notifyDataSetChanged();
    }

    // icones selecionados em xhdpi (compatível com o AVD em uso nos testes), ver demais resoluções
    // sugestão para bilbioteca de ícones: https://materialdesignicons.com/
    // e também http://www.veryicon.com/

    // Verificar esse método
    // Menu Superior
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {


            case android.R.id.home:
                this.finish();

                return true;

            case R.id.miAdd:
                // Adiciona Novo Filme

                // Cria um novo Objeto Filme1
                Intent i = new Intent(getApplicationContext(),EditaFilme.class);
                i.putExtra("filmeId", 0);
                // 1 identifica que é inclusão de novo filme
                i.putExtra("from", 1);
                startActivity(i);

                return true;

            case R.id.miAlpha:
                //Ordena AZ

                if (AZ){
                    // Ordem Crescente
                    AZ = false;

                    Collections.sort(alFilmes, new Comparator<Filme>() {
                        @Override
                        public int compare(Filme o1, Filme o2) {
                            return o1.getfNome().compareToIgnoreCase(o2.getfNome());
                        }
                    });

                }else {
                    // Ordem decrescente
                    AZ = true;

                    Collections.sort(alFilmes, new Comparator<Filme>() {
                        @Override
                        public int compare(Filme o1, Filme o2) {
                            return o2.getfNome().compareToIgnoreCase(o1.getfNome());
                        }
                    });

                }

                //aaListaFilmes.notifyDataSetChanged();
                faListaFilmes.notifyDataSetChanged();

                return true;

            case R.id.miCrono:
                // Ordena por Data
                // TODO: Fazer crescente e descrecente

                if (crono){
                    // Ordem Crescente

                    crono = false;
                    Collections.sort(alFilmes, new Comparator<Filme>() {
                        @Override
                        public int compare(Filme o1, Filme o2) {
                            return o1.getfData().compareTo(o2.getfData());
                        }
                    });


                }else {
                    // Ordem decrescente
                    crono = true;

                    Collections.sort(alFilmes, new Comparator<Filme>() {
                        @Override
                        public int compare(Filme o1, Filme o2) {
                            return o2.getfData().compareTo(o1.getfData());
                        }
                    });
                }

                //aaListaFilmes.notifyDataSetChanged();
                faListaFilmes.notifyDataSetChanged();

                return true;

            case R.id.miSinc:

                // a fazer/alterar, é um exemplo de item no menu
                // como exemplo, vamor mostrar uma snakcbar
                Snackbar.make(findViewById(R.id.lvListaFilmes), "A Sincronização não está habilitada", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
                return true;

            case R.id.miConfigurar:

                Intent conf = new Intent(getApplicationContext(), Config.class);
                startActivity(conf);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
