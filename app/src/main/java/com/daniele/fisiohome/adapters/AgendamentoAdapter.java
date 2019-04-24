package com.daniele.fisiohome.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.daniele.fisiohome.R;
import com.daniele.fisiohome.activity.AgendamentosActivity;
import com.daniele.fisiohome.activity.AgendarActivity;
import com.daniele.fisiohome.model.Agendamento;

import java.util.List;

public class AgendamentoAdapter extends BaseAdapter {

    private final List<Agendamento> agendamentos;
    private final Activity activity;

    public AgendamentoAdapter(List<Agendamento> agendamentos, Activity activity) {
        this.agendamentos = agendamentos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return agendamentos.size();
    }

    @Override
    public Object getItem(int position) {
        return agendamentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate(R.layout.item_agendamento, parent, false);

        Agendamento agendamento = agendamentos.get(position);

        TextView nome = (TextView) view.findViewById(R.id.lista_agendamento_nome);
        TextView data = (TextView) view.findViewById(R.id.lista_agendamento_data);
        TextView hora = (TextView) view.findViewById(R.id.lista_agendamento_hora);
        Button visualizar = (Button) view.findViewById(R.id.lista_agendamento_visualizar);

        nome.setText(agendamento.getFisioterapeuta().getNome());
        data.setText(agendamento.getDataHora().getDias());
        hora.setText(agendamento.getDataHora().getHoras());

        visualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgendamentosActivity act = (AgendamentosActivity) activity;
                act.visualizarAgedamento();
            }

        });

        return view;
    }

}
