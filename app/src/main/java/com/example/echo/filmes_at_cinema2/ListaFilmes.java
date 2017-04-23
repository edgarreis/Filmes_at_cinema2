package com.example.echo.filmes_at_cinema2;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

// criando a lista de filmes usando o pattern singleton
// ou seja, uma classe que permite a existência de apenas uma instância de sí mesma

// mantendo a lista de filmes em um singleton, ela estará disponível na memoria
// durante o ciclo de vida das atividades e do app

public class ListaFilmes {

    // A Instancia Static
    // TODO: Verificar Por que é static ?
    private static ListaFilmes sListaFilmes;
    // TODO: Verificar O que é Context ?
    private Context appContext;

    // a lista de filmes privada da classe
    private ArrayList<Filme> alFilmes;

    // Construtor Privado
    private ListaFilmes(Context appContext) {

        this.appContext = appContext;
        // Cria o ArrayList de Objetos da Classe Filme
        alFilmes = new ArrayList<Filme>();

        // Cria o Filme
        Filme Filme1 = new Filme("O Poderoso Chefão", "UCI", "Ação", 30, 12, 2016);
        Filme Filme2 = new Filme("O Rei Leão", "Cinemark", "Comédia", 22, 05, 2001);
        Filme Filme3 = new Filme("Constantine", "K7", "Terror", 30, 12, 2017);
        Filme Filme4 = new Filme("Madrugada Muito Louca", "DVD", "Comédia", 01, 02, 2017);

        Filme1.setfComentario("Comentario do Poderoso Chefão");
        Filme4.setfComentario("Comentario do Filme Madrugada Muito Louca");


        // Adiciona Filmes para popupar a Base
        alFilmes.add(Filme1);
        alFilmes.add(Filme2);
        alFilmes.add(Filme3);
        alFilmes.add(Filme4);
    }

    // Método Singlenton
    // Getter irá verificar se a instância já existe ou não
    //public static ListaFilmes get
    public static ListaFilmes getsListaFilmes(Context c) {
        if (sListaFilmes == null){
            sListaFilmes = new ListaFilmes(c.getApplicationContext());
        }
        return sListaFilmes;
    }

    // Retorna o Conjunto de Filmes
    public ArrayList<Filme> getAlFilmes() {
        return alFilmes;
    }
}






