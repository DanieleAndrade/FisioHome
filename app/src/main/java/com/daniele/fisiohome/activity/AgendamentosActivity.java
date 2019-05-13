package com.daniele.fisiohome.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.daniele.fisiohome.FisioHome;
import com.daniele.fisiohome.R;
import com.daniele.fisiohome.adapters.AgendamentoAdapter;
import com.daniele.fisiohome.adapters.FisioterapeutasAdapter;
import com.daniele.fisiohome.model.Agendamento;
import com.daniele.fisiohome.model.Fisioterapeuta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AgendamentosActivity extends AppCompatActivity {

    ListView listViewAgendamentos;
    AgendamentoAdapter agendamentoAdapter;
    List<Agendamento> agendamentosLista;
    DatabaseReference agendamentoRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamentos);

        listViewAgendamentos = findViewById(R.id.lista__agendamentos_agendados);

        agendamentoRef = FirebaseDatabase.getInstance().getReference("agendamentos");

        agendamentosLista = new ArrayList<>();

    }

    @Override
    protected void onStart() {

        super.onStart();

        agendamentoRef.addValueEventListener( new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                agendamentosLista.clear();

                for(DataSnapshot agendamentoSnapshot : dataSnapshot.getChildren()) {

                    Agendamento agendamento = agendamentoSnapshot.getValue(Agendamento.class);
                    agendamentosLista.add(agendamento);
                }

                agendamentoAdapter = new AgendamentoAdapter( agendamentosLista, AgendamentosActivity.this);

                listViewAgendamentos.setAdapter(agendamentoAdapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }


    public void visualizarAgedamento() {
        Intent intent = new Intent(this, AgendarActivity.class);
        startActivity(intent);
    }
}
