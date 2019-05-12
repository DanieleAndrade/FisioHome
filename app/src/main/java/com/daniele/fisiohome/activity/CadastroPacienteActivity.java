package com.daniele.fisiohome.activity;

import android.annotation.SuppressLint;
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
import com.daniele.fisiohome.model.Paciente;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;

import java.util.Objects;

public class CadastroPacienteActivity extends AppCompatActivity {

    private Button botaoCadastrarPaciente;
    private EditText campoEmail, campoSenha;
    private EditText campoNomePaciente, campoCPF;
    private FirebaseAuth autenticacao;
    private DatabaseReference databasePacientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_paciente);

        inicializaComponentes();
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        databasePacientes = ConfiguracaoFirebase.getFirebase().getDatabase().getReference("Pacientes");

        botaoCadastrarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = campoEmail.getText().toString().trim();
                String senha = campoSenha.getText().toString().trim();
                String nome = campoNomePaciente.getText().toString().trim();
                String cpf = campoCPF.getText().toString().trim();

                if (!nome.isEmpty() && !cpf.isEmpty()) {

                    String id = databasePacientes.push().getKey();
                    Paciente paciente = new Paciente(id, nome, cpf);
                    databasePacientes.child(id).setValue(paciente);

                    Toast.makeText(CadastroPacienteActivity.this,
                            "Cadastro realizado com sucesso!",
                            Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(CadastroPacienteActivity.this, "Você deve digitar um nome e CPF", Toast.LENGTH_LONG).show();
                }
                if (!email.isEmpty()) {
                    if (!senha.isEmpty()) {

                        autenticacao.createUserWithEmailAndPassword(
                                email, senha
                        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @SuppressLint("NewApi")
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    home();

                                } else {

                                    String erroExcecao;

                                    try {
                                        throw Objects.requireNonNull(task.getException());
                                    } catch (FirebaseAuthWeakPasswordException e) {
                                        erroExcecao = "Digite uma senha mais forte!";
                                    } catch (FirebaseAuthInvalidCredentialsException e) {
                                        erroExcecao = "Por favor, digite um e-mail válido";
                                    } catch (FirebaseAuthUserCollisionException e) {
                                        erroExcecao = "Este conta já foi cadastrada";
                                    } catch (Exception e) {
                                        erroExcecao = "ao cadastrar usuário: " + e.getMessage();
                                        e.printStackTrace();
                                    }

                                    Toast.makeText(CadastroPacienteActivity.this,
                                            "Erro: " + erroExcecao,
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }


        });
    }


    private void inicializaComponentes() {
        campoEmail = findViewById(R.id.editCadastroEmail);
        campoSenha = findViewById(R.id.editCadastroSenha);
        campoCPF = findViewById(R.id.editCPF);
        campoNomePaciente = findViewById(R.id.editNomePaciente);
        botaoCadastrarPaciente = findViewById(R.id.botaoCadastrarPaciente);
    }

    public void home() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
