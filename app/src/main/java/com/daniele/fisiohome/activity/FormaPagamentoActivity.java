package com.daniele.fisiohome.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.daniele.fisiohome.FisioHome;
import com.daniele.fisiohome.R;
import com.daniele.fisiohome.adapters.FisioterapeutasAdapter;
import com.daniele.fisiohome.adapters.PagamentoAdapter;
import com.daniele.fisiohome.model.Fisioterapeuta;
import com.daniele.fisiohome.model.Pagamento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FormaPagamentoActivity extends AppCompatActivity {

    ListView listViewFormaPagamento;
    List<Pagamento> pagamentoLista;
    DatabaseReference pagamentosRef;

    private Button botaoAdicionarFormaPagamento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forma_pagamento);

        listViewFormaPagamento = findViewById(R.id.lista_formas_de_pagamento);

        pagamentosRef = FirebaseDatabase.getInstance().getReference("pagamentos");

        pagamentoLista = new ArrayList<>();

        botaoAdicionarFormaPagamento = findViewById(R.id.button_adicionar_forma_pagamento);

        botaoAdicionarFormaPagamento.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                cartaoView();
            }
        });
    }

    private void cartaoView() {
        startActivity( new Intent(getApplicationContext(), CadastroCartaoActivity.class));
    }

    @Override
    protected void onStart() {

        super.onStart();

        pagamentosRef.addValueEventListener( new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                pagamentoLista.clear();

                for(DataSnapshot pagamentoSnapshot : dataSnapshot.getChildren()) {

                    Pagamento pagamento = pagamentoSnapshot.getValue(Pagamento.class);
                    pagamentoLista.add(pagamento);
                }

                PagamentoAdapter adapter = new PagamentoAdapter(FormaPagamentoActivity.this, pagamentoLista);

                listViewFormaPagamento.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        listViewFormaPagamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToDetalhesPagamento(pagamentoLista.get(position));
            }
        });
    }

    public void goToDetalhesPagamento(Pagamento pagamento) {
        Intent intent = new Intent(this, DetalheFormaPagamentoActivity.class);
        startActivity(intent);
    }
}
