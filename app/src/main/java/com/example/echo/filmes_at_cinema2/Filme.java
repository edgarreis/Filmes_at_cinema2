package com.example.echo.filmes_at_cinema2;

import android.text.Html;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Filme {

    private String fNome;
    private String fLocal;
    private String fgenero;
    private GregorianCalendar fData;
    private String fComentario;

    public Filme(String fNome, String fLocal, String fgenero, GregorianCalendar fData) {
        this.fNome = fNome;
        this.fLocal = fLocal;
        this.fData = fData;
        this.fgenero = fgenero;
        this.fComentario = "";
        // Comentario não é necessario passar como parametro,
        // pois nao é um campo obrigatório
    }

    // SobreCarga de Construtor
    public Filme(String fNome, String fLocal, String fgenero, int dia, int mes, int ano) {
        this.fNome = fNome;
        this.fLocal = fLocal;
        this.fgenero = fgenero;
        this.fData = new GregorianCalendar(ano, mes-1, dia);
        this.fComentario = "";
    }

    // SobreCarga de Construtor
    public Filme() {
        this.fNome = "";
        this.fLocal = "";
        this.fgenero = "";
        this.fData = new GregorianCalendar();
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

    public String getfGenero() {
        return fgenero;
    }

    public void setfGenero(String fgenero) {
        this.fgenero = fgenero;
    }

    public GregorianCalendar getfData() {

        return fData;
    }

    public void setfData(GregorianCalendar fData) {

        this.fData = fData;
    }

    // Sobrecarga do Método setfData
    public void setfData(int dia, int mes, int ano) {

        setfData(new GregorianCalendar(ano, mes-1, dia));
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
                + " " + fgenero;
    }

    // TODO: Verificar a Necessidade deste Método
    public String getDataString() {

        SimpleDateFormat stf = new SimpleDateFormat("dd.MM.yyyy");
        String sData = stf.format(fData.getTime());
        return sData;
    }
}
