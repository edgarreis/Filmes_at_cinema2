package com.example.echo.filmes_at_cinema2;

import android.support.design.widget.FloatingActionButton;
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

    // ArrayList estrutura de dados contendo os filmes vistos
    ArrayList<Filme> alFilmes;
    // ArrayAdapter é um adapter para vincular arrays e views, pode ser usado com list views e spinners
    ArrayAdapter<Filme> aaListaFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pega os Filmes
        alFilmes = ListaFilmes.getsListaFilmes(getApplicationContext()).getAlFilmes();

        //Associa o Elemento da UI com a variável
        ListView lvListaFilmes = (ListView) findViewById(R.id.lvListaFilmes);
        // Utiliza o ArrayAdatpar para mostrar os dados do ArrayList de filmes alFilmes
        aaListaFilmes = new ArrayAdapter<Filme>(this, android.R.layout.simple_list_item_1, alFilmes);
        // Mostrá os Filmes listview lvListaFilmes, setada com o método setAdapter
        lvListaFilmes.setAdapter(aaListaFilmes);
        // Usa o retorno do toString para determinar o texto que será exibido em cada item da lista

        // TODO: Verificar toda essa função
        lvListaFilmes.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                        Intent i = new Intent(view.getContext(), DadosFilmes.class);
                        i.putExtra("FilmeId", String.valueOf(posicao));
                        startActivity(i);
                    }
                }
        );

        // TODO: FloatingActionButton
        //FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener());


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
        aaListaFilmes.notifyDataSetChanged();
    }

    // icones selecionados em xhdpi (compatível com o AVD em uso nos testes), ver demais resoluções
    // sugestão para bilbioteca de ícones: https://materialdesignicons.com/
    // e também http://www.veryicon.com/

    // Verificar esse método
    // Menu Superior
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.miAdd:
                // Adiciona Novo Filme

                // Cria um novo Objeto Filme1
                // TODO: Melhorar essa função
                Filme Filme1 = new Filme("", "", "", new GregorianCalendar());
                // Adiciona Filmes para popupar a Base
                alFilmes.add(Filme1);
                // avisa o adaptador que os dados mudaram, logo a view que mostra os dados precisa ser atualizada
                aaListaFilmes.notifyDataSetChanged();
                // Pega a Posição do Filme
                int filmePos = alFilmes.indexOf(Filme1);
                // Atualiza nome do filme na action bar
                //ab.setTitle(Filme1.getfNome()); //TODO : it

                // Chama a Activity para Editar os dados deste filme
                Intent i2 = new Intent(this.getBaseContext(), EditaFilme.class);
                i2.putExtra("FilmeId", String.valueOf(filmePos));
                // TODO: Por que tem esse from 1
                i2.putExtra("from", "1");
                startActivity(i2);
                // TODO: Arrumar a Activity Editar para aparecer oq deve ser colocado em cada campo

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

                aaListaFilmes.notifyDataSetChanged();

                return true;

            case R.id.miCrono:
                // Ordena por Data

                Collections.sort(alFilmes, new Comparator<Filme>() {
                    @Override
                    public int compare(Filme o1, Filme o2) {
                        return o1.getfData().compareTo(o2.getfData());
                    }
                });

                aaListaFilmes.notifyDataSetChanged();

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
