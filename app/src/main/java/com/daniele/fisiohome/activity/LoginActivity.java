package com.daniele.fisiohome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daniele.fisiohome.R;
import com.daniele.fisiohome.helper.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button botaoAcessar, btnCadastrarFisio;
    private Button botaoCadastrar;
    private EditText campoEmail, campoSenha;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializaComponentes();
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        botaoAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = campoEmail.getText().toString();
                String senha = campoSenha.getText().toString();

                if (!email.isEmpty()) {
                    if (!senha.isEmpty()) {

                        autenticacao.signInWithEmailAndPassword(
                                email, senha
                        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    Toast.makeText(LoginActivity.this,
                                            "Logado com sucesso",
                                            Toast.LENGTH_SHORT).show();

                                    telaHome();

                                } else {
                                    Toast.makeText(LoginActivity.this,
                                            "Erro ao fazer login : " + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });


                    } else {
                        Toast.makeText(LoginActivity.this,
                                "Preencha a senha!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Preencha o E-mail!",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastroPaciente();

            }
        });

        btnCadastrarFisio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastroFisioterapeuta();
            }
        });
    }

    private void inicializaComponentes() {
        campoEmail = findViewById(R.id.editCadastroEmail);
        campoSenha = findViewById(R.id.editCadastroSenha);
        botaoAcessar = findViewById(R.id.buttonAcesso);
        botaoCadastrar = findViewById(R.id.buttonCadastrar);
        btnCadastrarFisio = findViewById(R.id.buttonCadastrarFisio);
    }

    public void cadastroPaciente() {
        Intent intent = new Intent(this, CadastroPacienteActivity.class);
        startActivity(intent);
    }

    public void cadastroFisioterapeuta() {
        Intent intent = new Intent(this, CadastroFisioterapeutaActivity.class);
        startActivity(intent);
    }

    public void telaHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}