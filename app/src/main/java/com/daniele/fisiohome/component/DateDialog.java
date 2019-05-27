package com.daniele.fisiohome.component;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.daniele.fisiohome.FisioHome;
import com.daniele.fisiohome.model.Disponibilidade;

import java.util.Calendar;

@SuppressLint("ValidFragment")
public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        Button dataConsulta;
        public DateDialog(View view){
        dataConsulta=(Button)view;
        }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, ano, mes, dia);


    }

    public void onDateSet(DatePicker view, int ano, int mes, int dia) {
        String data = dia + "-" + (mes+1) + "-" + ano;
        dataConsulta.setText(data);
        FisioHome.setDataAtual(data);
        Disponibilidade disponibilidade = FisioHome.getDisponibilidadeAtual();
        if(disponibilidade != null){
            disponibilidade.setDias(data);
            FisioHome.setDisponibilidadeAtual(disponibilidade);
        } else {
            Disponibilidade disponibilidade1 = new Disponibilidade();
            disponibilidade1.setDias(data);
            FisioHome.setDisponibilidadeAtual(disponibilidade1);
        }


    }

}
