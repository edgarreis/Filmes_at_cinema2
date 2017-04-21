package com.example.echo.filmes_at_cinema2;

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

public class MainActivity extends AppCompatActivity {

    boolean AZ = true;

    // ArrayList, estrutura de dados contendo os filmes vistos
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

        // Verificar toda essa função !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        lvListaFilmes.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                        Intent i = new Intent(view.getContext(), DadosFilmes.class);
                        i.putExtra("filmeId", String.valueOf(posicao));
                        startActivity(i);
                    }
                }
        );
    }

    // mais info sobre ActionBar e Toolbars em
    // https://developer.android.com/training/appbar/index.html
    public boolean onCreateOptionsMenu(Menu menu) {
        // popula a action bar com os icones configurado em menu_main.xml
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

                String nome = "Test" + alFilmes.size();
                Filme Filme1 = new Filme("Teste", "DVD", "Acao", new GregorianCalendar());
                Filme1.setfComentario("Teste Comentario do Filme ...");

                // Adiciona Filmes para popupar a Base
                alFilmes.add(Filme1);
                // avisa o adaptador que os dados mudaram, logo a view que mostra os dados precisa ser atualizada
                aaListaFilmes.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Adicionado " + nome, Toast.LENGTH_SHORT).show();

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
