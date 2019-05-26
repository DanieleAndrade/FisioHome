package com.daniele.fisiohome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daniele.fisiohome.FisioHome;
import com.daniele.fisiohome.R;
import com.daniele.fisiohome.model.Pagamento;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroCartaoActivity extends AppCompatActivity {

    private EditText numeroCartao, dataVencimento, cvv;
    private TextView pais;
    private Button botaoCadastrarCartao;
    DatabaseReference databaseFormaPagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cartao);

        numeroCartao = findViewById(R.id.edit_numero_cartao);
        dataVencimento = findViewById(R.id.edit_data_venc);
        pais = findViewById(R.id.edit_pais_cartao);
        cvv = findViewById(R.id.edit_cvv);
        botaoCadastrarCartao = findViewById(R.id.button_cadastrar_cartao);


        if (FisioHome.getPaciente() != null && FisioHome.getPaciente().getId() != null) {
            databaseFormaPagamento = FirebaseDatabase.getInstance().getReference("pagamentos").child(FisioHome.getPaciente().getId());
        }

        botaoCadastrarCartao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                salvarCartao();
            }
        });
    }

    private void salvarCartao() {

        String id = databaseFormaPagamento.push().getKey();

        Pagamento pagamento = new Pagamento();
        pagamento.setNumeroCartao(numeroCartao.toString());
        pagamento.setDataValidade(dataVencimento.toString());
        pagamento.setCvv(cvv.toString());
        pagamento.setPais(pais.toString());
        pagamento.setPaciente(FisioHome.getPaciente());

        databaseFormaPagamento.child(id).setValue(pagamento);

        Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();

        startActivity(new Intent(getApplicationContext(), FormaPagamentoActivity.class));
    }
}
