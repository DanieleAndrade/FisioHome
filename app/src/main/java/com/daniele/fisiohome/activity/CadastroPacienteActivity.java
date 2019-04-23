package com.daniele.fisiohome.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daniele.fisiohome.R;

public class CadastroPacienteActivity extends AppCompatActivity {

    Button finalizarCadastroButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_paciente);
        finalizarCadastroButton = findViewById(R.id.button_finalizar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        finalizarCadastroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }

        });
    }

    public void login(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
