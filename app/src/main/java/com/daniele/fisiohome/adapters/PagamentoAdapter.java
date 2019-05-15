package com.daniele.fisiohome.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daniele.fisiohome.R;
import com.daniele.fisiohome.model.Pagamento;

import java.util.List;

public class PagamentoAdapter  extends BaseAdapter {
    private Context contextView;
    private LayoutInflater layoutInflater;
    private List<Pagamento> pagamentoLista;

    public PagamentoAdapter(Context context, List<Pagamento> pagamentoLista) {
        this.contextView = context;
        this.pagamentoLista = pagamentoLista;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return pagamentoLista.size();
    }

    @Override
    public Object getItem(int position) {
        return pagamentoLista.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        @SuppressLint("ViewHolder")
        View listViewItem = layoutInflater.inflate(R.layout.item_lista_forma_pagamento, parent, false);

        TextView textViewNumeroCartao = listViewItem.findViewById(R.id.numero_cartao);

        Pagamento pagamento = pagamentoLista.get(position);

        textViewNumeroCartao.setText(pagamento.getNumeroCartao());

        return listViewItem;
    }
}
