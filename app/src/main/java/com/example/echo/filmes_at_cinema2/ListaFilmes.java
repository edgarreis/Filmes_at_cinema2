package com.example.echo.filmes_at_cinema2;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.NotSerializableException;

// criando a lista de filmes usando o pattern singleton
// ou seja, uma classe que permite a existência de apenas uma instância de sí mesma

// mantendo a lista de filmes em um singleton, ela estará disponível na memoria
// durante o ciclo de vida das atividades e do app

public class ListaFilmes implements Serializable{

    // A Instancia Static
    // TODO: Verificar Por que é static ?
    // TODO: Verificar O que é Context ?
    // TODO: Verificar Por que é final ?
    // a lista de filmes privada da classe
    private ArrayList<Filme> alFilmes;
    private static ListaFilmes sListaFilmes;
    private Context appContext;

    private static String fileName = "FilmeDB.ser";
    public static final String PREF_LOCAL = "local";

    // Construtor Privado
    private ListaFilmes(Context appContext) {

        this.appContext = appContext;
        // Cria o ArrayList de Objetos da Classe Filme
        alFilmes = new ArrayList<Filme>();

        // Cria o Filme
//        Filme Filme1 = new Filme("O Poderoso Chefão", "UCI", "Ação", 30, 12, 2016);
//        Filme Filme2 = new Filme("O Rei Leão", "Cinemark", "Comédia", 22, 05, 2001);
//        Filme Filme3 = new Filme("Constantine", "K7", "Terror", 30, 12, 2017);
//        Filme Filme4 = new Filme("Madrugada Muito Louca", "DVD", "Comédia", 01, 02, 2017);
//        Filme1.setfComentario("Comentario do Poderoso Chefão");
//        Filme4.setfComentario("Comentario do Filme Madrugada Muito Louca");
        // Adiciona Filmes para popupar a Base
//        alFilmes.add(Filme1);
//        alFilmes.add(Filme2);
//        alFilmes.add(Filme3);
//        alFilmes.add(Filme4);

        //this.saveToFile();
        this.readFromFile();


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

    // Salvar tem que ser static para poder ser chamado após ListaFilmes.
    public static void salvar(){
        sListaFilmes.saveToFile();

    }

    // serializa o ArrayList de Filmes gravando no arquivo o conteúdo de alFilmes
    private void saveToFile() {
        try {
            FileOutputStream fileOutputStream = this.appContext.getApplicationContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.alFilmes);
            objectOutputStream.close();
            fileOutputStream.close();
            Toast.makeText(this.appContext.getApplicationContext(), "Salvou arquivo...", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this.appContext.getApplicationContext(), "IOException..." + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // cria um ArrayList de Filmes lendo do arquivo e atribui a alFilmes
    private void readFromFile() {
        try {
            FileInputStream fileInputStream = this.appContext.getApplicationContext().openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            this.alFilmes = (ArrayList<Filme>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            Toast.makeText(this.appContext.getApplicationContext(), "Leu aquivo... ", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(this.appContext.getApplicationContext(), "Arquivo ainda não existe...", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this.appContext.getApplicationContext(), "IOException..." + e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this.appContext.getApplicationContext(), "ClassNotFoundException...", Toast.LENGTH_LONG).show();
        }
    }
}










