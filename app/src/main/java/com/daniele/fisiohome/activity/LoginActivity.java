package com.daniele.fisiohome.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daniele.fisiohome.R;

public class LoginActivity extends AppCompatActivity {

    Button cadastrarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cadastrarButton = findViewById(R.id.button_cadastrar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        cadastrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastroPaciente();
            }

        });
    }

    public void cadastroPaciente(){
        Intent intent = new Intent(this, CadastroPacienteActivity.class);
        startActivity(intent);
    }
}
