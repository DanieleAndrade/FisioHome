package com.daniele.fisiohome.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.daniele.fisiohome.R;
import com.daniele.fisiohome.model.Disponibilidade;

import java.util.List;

public class HorariosFisioAdapter extends BaseAdapter {
    private Context contextView;
    private LayoutInflater layoutInflater;
    private List<Disponibilidade> horariosList;

    public HorariosFisioAdapter(Context context, List<Disponibilidade> horariosList) {
        this.contextView = context;
        this.horariosList = horariosList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return horariosList.size();
    }

    @Override
    public Object getItem(int position) {
        return horariosList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        @SuppressLint("ViewHolder")
        View v = layoutInflater.inflate(R.layout.item_lista_horarios, parent, false);

        TextView textViewHorariosFisio = v.findViewById(R.id.horario);

        Disponibilidade disponibilidade = horariosList.get(position);

        if(disponibilidade != null && disponibilidade.getHoras() != null) {
            textViewHorariosFisio.setText(disponibilidade.getHoras());
        }

        return v;
    }
}
