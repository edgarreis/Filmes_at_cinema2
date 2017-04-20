package com.example.echo.filmes_at_cinema2;

import android.text.Html;
import android.widget.TextView;

import java.util.GregorianCalendar;

/**
 * Created by Echo on 19/04/2017.
 */

public class Filme {

    private String fNome;
    private String fLocal;
    private String Genero;
    private GregorianCalendar fData;
    private String fComentario;

    public Filme(String fNome, String fLocal, String genero, GregorianCalendar fData) {
        this.fNome = fNome;
        this.fLocal = fLocal;
        this.fData = fData;
        this.Genero = genero;
        this.fComentario = "";
        // Comentario não é necessario passar como parametro,
        // pois nao é um campo obrigatório
    }

    // SobreCarga de Construtor
    public Filme(String fNome, String fLocal, String genero, int dia, int mes, int ano) {
        this.fNome = fNome;
        this.fLocal = fLocal;
        this.Genero = genero;
        this.fData = new GregorianCalendar(ano, mes-1, dia);
        this.fComentario = "";
    }

    public String getfNome() {
        return fNome;
    }

    public void setfNome(String fNome) {
        this.fNome = fNome;
    }

    public String getfLocal() {
        return fLocal;
    }

    public void setfLocal(String fLocal) {
        this.fLocal = fLocal;
    }

    public GregorianCalendar getfData() {
        return fData;
    }

    public void setfData(GregorianCalendar fData) {
        this.fData = fData;
    }

    public String getfComentario() {
        return fComentario;
    }

    public void setfComentario(String fComentario) {
        this.fComentario = fComentario;
    }

    @Override
    public String toString() {
        return fNome + '\n'
                + " " + Genero;
    }

}
