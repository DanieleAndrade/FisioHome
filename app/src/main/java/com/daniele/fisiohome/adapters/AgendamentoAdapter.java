package com.daniele.fisiohome.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.daniele.fisiohome.FisioHome;
import com.daniele.fisiohome.R;
import com.daniele.fisiohome.activity.AgendamentosActivity;
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

        @SuppressLint("ViewHolder")
        View view = activity.getLayoutInflater().inflate(R.layout.item_agendamento, parent, false);

        Agendamento agendamento = agendamentos.get(position);

        TextView nome = view.findViewById(R.id.lista_agendamento_nome);
        TextView data = view.findViewById(R.id.lista_agendamento_data);
        TextView hora = view.findViewById(R.id.lista_agendamento_hora);
        Button visualizar = view.findViewById(R.id.lista_agendamento_visualizar);


        nome.setText(FisioHome.getFisioterapeutaAtual().getNome());
        data.setText(FisioHome.getDisponibilidadeAtual().getDias());
        hora.setText(FisioHome.getDisponibilidadeAtual().getHoras());

        visualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AgendamentosActivity act = (AgendamentosActivity) activity;
//                act.visualizarAgedamento();
            }

        });

        return view;
    }

}
