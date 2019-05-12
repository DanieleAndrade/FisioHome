package com.daniele.fisiohome.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daniele.fisiohome.R;
import com.daniele.fisiohome.model.Fisioterapeuta;

import java.util.List;

public class FisioterapeutasAdapter extends BaseAdapter {
    private Context contextView;
    private Activity context;
    private LayoutInflater layoutInflater;
    private List<Fisioterapeuta> fisioterapeutaList;

    public FisioterapeutasAdapter(Context context, List<Fisioterapeuta> fisioterapeutaList) {
        this.contextView = context;
        this.fisioterapeutaList = fisioterapeutaList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return fisioterapeutaList.size();
    }

    @Override
    public Object getItem(int position) {
        return fisioterapeutaList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

//    @Override
//    public View getView(int position, View view, ViewGroup parent) {
//        @SuppressLint("ViewHolder")
//        View v = layoutInflater.inflate(R.layout.item_lista_fisioterapeutas, parent, false);
//
//        TextView textViewNomeFisio = v.findViewById(R.id.nome_fisioterapeuta);
//        TextView textViewCRM = v.findViewById(R.id.crm_fisioterapeuta);
//
//        textViewNomeFisio.setText(fisioterapeutaList.get(position).getNome());
//        textViewCRM.setText(String.valueOf(fisioterapeutaList.get(position).getNumeroRegistro()));
//
//        return v;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.item_lista_fisioterapeutas, null, true);

        TextView textViewNome = (TextView) listViewItem.findViewById(R.id.nome_fisioterapeuta);
        TextView textViewNumero = (TextView) listViewItem.findViewById(R.id.crm_fisioterapeuta);

        Fisioterapeuta fisioterapeuta = fisioterapeutaList.get(position);

        textViewNome.setText(fisioterapeuta.getNome());
        textViewNumero.setText(fisioterapeuta.getNumeroRegistro());

        return listViewItem;
    }
}
