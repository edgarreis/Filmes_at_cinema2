package com.example.echo.filmes_at_cinema2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {
// caso implements DatePickerDialog.OnDateSetListener ficasse aqui
// teria que implementar a função onDateSet, neste caso optou-se por colocar em EditaFilme

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // pega a data recebida como argumento long (milisegundos) via Bundle
        Bundle bDate = this.getArguments();
        long lDate = bDate.getLong("data");

        // e usa como default no picker
        //final
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(lDate);
        int year = c.get(c.YEAR);
        int month = c.get(c.MONTH);
        int day = c.get(c.DAY_OF_MONTH);

        // caso fosse usar a data corrente
//        final Calendar c = Calendar.getInstance();
//        int year = c.get(Calendar.YEAR);
//        int month = c.get(Calendar.MONTH);
//        int day = c.get(Calendar.DAY_OF_MONTH);

        // cria nova instância DatePickerDialog e retorna
        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
    }

}