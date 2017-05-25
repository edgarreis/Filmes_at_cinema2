package com.example.echo.filmes_at_cinema2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Config extends AppCompatActivity {

    EditText etConfLocal;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Configurações");

        etConfLocal = (EditText) findViewById(R.id.etConfLocal);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        String local = sharedPreferences.getString(ListaFilmes.PREF_LOCAL, null);

        if (local != null) {
            etConfLocal.setText(local);
        }

    }

    public void onClickSalvarConfig (View view) {
        editor.putString(ListaFilmes.PREF_LOCAL, etConfLocal.getText().toString());
        editor.commit();
        this.finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
