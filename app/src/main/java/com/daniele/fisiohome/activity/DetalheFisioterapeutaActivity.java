package com.daniele.fisiohome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.daniele.fisiohome.FisioHome;
import com.daniele.fisiohome.R;
import com.daniele.fisiohome.adapters.FisioterapeutasAdapter;
import com.daniele.fisiohome.adapters.HorariosFisioAdapter;
import com.daniele.fisiohome.model.Disponibilidade;
import com.daniele.fisiohome.model.Endereco;
import com.daniele.fisiohome.model.Fisioterapeuta;

import java.util.List;

public class DetalheFisioterapeutaActivity extends AppCompatActivity {

    private TextView campoNomeFisioterapeuta;
    private TextView campoNumeroRegistroFisioterapeuta;
    private TextView campoLocalFisioterapeuta;
    private ListView campoHorariosFisioterapeuta;
    private TextView campoContatoFisioterapeuta;
    private TextView campoPrecoFisioterapeuta;

    HorariosFisioAdapter horariosFisioAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_fisioterapeuta);

        campoNomeFisioterapeuta = findViewById(R.id.nome_fisio_detalhe);
        campoNumeroRegistroFisioterapeuta = findViewById(R.id.numero_registro_fisio_detalhe);
        campoLocalFisioterapeuta = findViewById(R.id.local_fisio_detalhe);
        campoContatoFisioterapeuta = findViewById(R.id.contato_fisio_detalhe);
        campoPrecoFisioterapeuta = findViewById(R.id.valor_preco);
        campoHorariosFisioterapeuta = findViewById(R.id.lista_horarios_detalhe);

        campoNomeFisioterapeuta.setText(FisioHome.getFisioterapeutaAtual().getNome());
        campoNumeroRegistroFisioterapeuta.setText(String.valueOf(FisioHome.getFisioterapeutaAtual().getNumeroRegistro()));

        Endereco endereco = FisioHome.getFisioterapeutaAtual().getEnderecoFisioterapeuta();
        String local = endereco.getLogradouro() + " - " + String.valueOf(endereco.getNumero()) + ", " + endereco.getCidade();
        campoLocalFisioterapeuta.setText(local);

        campoContatoFisioterapeuta.setText(FisioHome.getFisioterapeutaAtual().getTelefone());
        campoPrecoFisioterapeuta.setText(String.valueOf(FisioHome.getFisioterapeutaAtual().getPrecoConsulta()));

        List<Disponibilidade> disponibilidades = FisioHome.getFisioterapeutaAtual().getDisponibilidade();

        if(disponibilidades != null && !disponibilidades.isEmpty()) {
            horariosFisioAdapter = new HorariosFisioAdapter(DetalheFisioterapeutaActivity.this, disponibilidades);
            campoHorariosFisioterapeuta.setAdapter(horariosFisioAdapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        campoHorariosFisioterapeuta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fisioterapeuta fisioterapeuta = FisioHome.getFisioterapeutaAtual();
                List<Disponibilidade> disponibilidades = fisioterapeuta.getDisponibilidade();

                telaAgendamento(disponibilidades.get(position));
            }
        });
    }

    private void telaAgendamento(Disponibilidade disponibilidade){
        FisioHome.setDisponibilidadeAtual(disponibilidade);
        Intent intent = new Intent(this, AgendarActivity.class);
        startActivity(intent);

    }
}
