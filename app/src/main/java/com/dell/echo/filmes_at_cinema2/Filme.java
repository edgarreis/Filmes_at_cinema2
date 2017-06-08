package com.dell.echo.filmes_at_cinema2;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Filme implements Serializable{

    private String fNome;
    private String fLocal;
    private String fgenero;
    private GregorianCalendar fData;
    private String fComentario;
    private int fImagem;

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

    // getfDataString para retorno de uma string para um setText
    public String getfDataString() {

        SimpleDateFormat stf = new SimpleDateFormat("EEE, d MMM yyyy");
        String sData = stf.format(fData.getTime());
        return sData;
    }


    public String getfComentario() {

        return fComentario;
    }

    public void setfComentario(String fComentario) {

        this.fComentario = fComentario;
    }

    public int getfImagem() {
        return fImagem;
    }

    public void setfImagem(int fImagem) {
        this.fImagem = fImagem;
    }

    public String getfLocalData(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM YY");
        // Gambiarra Para Caber A Data e o Local no Item da Lista do Filme
        // ToDo: Arrumar getfLocalData
        return String.format("%1$-" + (60-fLocal.length()) + "s", fLocal) + sdf.format(fData.getTime());
    }

    @Override
    public String toString() {

        return fgenero;
    }


}