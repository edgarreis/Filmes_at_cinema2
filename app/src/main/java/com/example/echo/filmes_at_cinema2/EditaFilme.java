package com.example.echo.filmes_at_cinema2;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.GregorianCalendar;
// TODO: Usar na Visualização do Filme o Scrionlling Activtiy

public class EditaFilme extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    // ArrayList, estrutura de dados contendo os Filmes
    ArrayList<Filme> alFilmes;
    Filme filme;
    EditText fNome;
    EditText fGenero;
    EditText fLocal;
    EditText fComentario;
    TextView fData;
    GregorianCalendar fDataGC;
    int filmePos;
    int iActId;

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
        //filmePos = Integer.parseInt(extras.getString("filmeId"));
        /*no objeto Bundle recebemos o from e o filmeId
          from = 1 identifica que é inclusão de novo filme
                   ou seja, quem chamou foi a MainActivity
          from = 2 identifica que é edição de filme existente
                   ou seja, quem chamou foi a DadosFilme
          filmeId = posição na ListView, igual a posição no ArrayList alFilmes
          pega então o código do filme e o codigo da activity que chamou
        */
        filmePos = extras.getInt("filmeId");
        iActId = extras.getInt("from");
        // Pega os Filmes
        alFilmes = ListaFilmes.getsListaFilmes(getApplicationContext()).getAlFilmes();

        // Atualiza dados do Filme nas nas TextViews
        fNome = (EditText) findViewById(R.id.etNome);
        fGenero = (EditText) findViewById(R.id.etGenero);
        fLocal = (EditText) findViewById(R.id.etLocal);
        fData = (TextView) findViewById(R.id.tvData);
        fComentario = (EditText) findViewById(R.id.etComentario);

        if (iActId == 2){
            // Editar o Filme

            // Pega o filme em questão que será editado
            filme = alFilmes.get(filmePos);
            // Atualiza o nome do filme na ActionBar
            ab.setTitle(filme.getfNome());
            // Atualiza os dados do filme nas Views

            fNome.setText(filme.getfNome());
            fGenero.setText(filme.getfGenero());
            fLocal.setText(filme.getfLocal());
            fDataGC = filme.getfData();
            //fData.setText(filme.getfDataString());
            //String sComentario = filme.getfComentario();
            fComentario.setText(filme.getfComentario());

        } else {
            // Novo Filme

            ab.setTitle("Novo Filme");
            fDataGC = new GregorianCalendar();
            filme = new Filme();
            fData.setText(filme.getfDataString());

            // Utilizando as Preferencias do Usuario
            String local = PreferenceManager.getDefaultSharedPreferences(this).getString(ListaFilmes.PREF_LOCAL,null);
            if (local != null){
                fLocal.setText(local);
            }
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        //Popula a ActionBar com os Icones configurados em menu_dados_filme.xml
        getMenuInflater().inflate(R.menu.menu_edita_filme, menu);
        return true;
    }

    // Montar a Data como argumento para o Picker
    public void showDataPickerDialog (View view){

        Bundle bfDate = new Bundle();
        // em milisegundos, um long
        bfDate.putLong("data", fDataGC.getTimeInMillis());
        DialogFragment dfDataPicker = new DatePickerFragment();
        dfDataPicker.setArguments(bfDate);
        dfDataPicker.show(getFragmentManager(), "Data do Evento");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        // Atualiza o TextView com a nova data escolhida no Picker
        fData.setText(dayOfMonth + "/" + (month+1) + "/" + year);
        // Cria novo objeto com a data escolhida para posterior atualização do objeto filme
        fDataGC = new GregorianCalendar(year, month, dayOfMonth);
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){

            case android.R.id.home:
                this.finish();
                return true;

            case R.id.miOkEdit:
                if(fNome.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Preencha o Nome do Filme", Toast.LENGTH_SHORT).show();
                    //(findViewById(R.id.lvListaFilmes),"Texto",)
                    return false;
                }
                if(fGenero.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Preencha o Genero do Filme", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if(fLocal.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Preencha o Local do Filme", Toast.LENGTH_SHORT).show();
                    return false;
                }

                // passou verificação obrigatória, salvar os dados alterados ou novos deste filme
                filme.setfNome(fNome.getText().toString());
                filme.setfGenero(fGenero.getText().toString());
                filme.setfLocal(fLocal.getText().toString());
                filme.setfData(fDataGC);
                filme.setfComentario(fComentario.getText().toString());

                // Verifica se o Filme é novo
                if (iActId == 1){
                    //filme tem nome e local, pode adicionar ao ArrayList
                    alFilmes.add(filme);
                }
                ListaFilmes.salvar(); // chama o metodo static para salvar a Lista de Filmes em disco
                // pois houve alteração de algum existente ou inclusão de um novo

                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }




}













