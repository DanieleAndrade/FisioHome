package com.daniele.fisiohome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.daniele.fisiohome.R;

public class AgendamentosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamentos);
    }

    public void visualizarAgedamento() {
        Intent intent = new Intent(this, AgendarActivity.class);
        startActivity(intent);
    }
}
