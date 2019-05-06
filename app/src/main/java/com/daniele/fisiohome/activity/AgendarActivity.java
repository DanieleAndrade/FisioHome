package com.daniele.fisiohome.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.daniele.fisiohome.FisioHome;
import com.daniele.fisiohome.R;
import com.daniele.fisiohome.model.Endereco;
import com.daniele.fisiohome.model.Fisioterapeuta;

public class AgendarActivity extends AppCompatActivity {

    private TextView campoData, campoLocal, campoNomeFisio, campoMotivo, campoPagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

        campoData = findViewById(R.id.data_agendamento);
        campoLocal = findViewById(R.id.local_agendamento);
        campoMotivo = findViewById(R.id.motivo_agendamento);
        campoNomeFisio = findViewById(R.id.fisioterapeuta_agendamento);
        campoPagamento = findViewById(R.id.pagamento_agendamento);

        Fisioterapeuta fisioterapeuta = FisioHome.getFisioterapeutaAtual();
        campoNomeFisio.setText(fisioterapeuta.getNome());

        Endereco endereco = fisioterapeuta.getEnderecoFisioterapeuta();
        String local = endereco.getLogradouro() + " - " + String.valueOf(endereco.getNumero()) + ", " + endereco.getCidade();
        campoLocal.setText(local);



    }
}
