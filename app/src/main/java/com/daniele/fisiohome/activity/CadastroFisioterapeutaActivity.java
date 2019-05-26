package com.daniele.fisiohome.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daniele.fisiohome.R;
import com.daniele.fisiohome.helper.ConfiguracaoFirebase;
import com.daniele.fisiohome.model.Disponibilidade;
import com.daniele.fisiohome.model.Fisioterapeuta;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CadastroFisioterapeutaActivity extends AppCompatActivity {

    private Button botaoCadastrarFisio;
    private EditText campoEmail, campoSenha, campoNomeFisio;
    private FirebaseAuth autenticacao;
    Fisioterapeuta fisioterapeuta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_fisioterapeuta);

        inicializaComponentes();
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        botaoCadastrarFisio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String email = campoEmail.getText().toString().trim();
//                String senha = campoSenha.getText().toString().trim();
//                String nome = campoNomeFisio.getText().toString().trim();
                Integer numeroRegistro = 74165;
                double precoConsulta = 122.50;
                Disponibilidade disponibilidade = new Disponibilidade();
                disponibilidade.setDias("03/05");
                disponibilidade.setHoras("13:00");
                List<Disponibilidade> disponibilidades = new ArrayList<>();
                disponibilidades.add(disponibilidade);
                String observacao = "";
                String telefone = "1236-4123";
                String logradouro = "Rua 1";
                String bairro = "Bairro 1";
                String cep = "60740";
                Integer numero = 113;
                String estado = "CE";
                String cidade = "Fortaleza";
                String nome = "Teste Fisio 1";
                String email = "q@gmail.com";

                String senha = "123654789";
                String tipo = "tipo";

                if (!nome.isEmpty()) {
                    if (!email.isEmpty()) {
                        if (!senha.isEmpty()) {
                            fisioterapeuta = new Fisioterapeuta();
                            fisioterapeuta.setNome(nome);
                            fisioterapeuta.setEmail(email);
                            fisioterapeuta.setSenha(senha);

                            fisioterapeuta.setNumeroRegistro(74165);
                            fisioterapeuta.setPrecoConsulta(122.50);
                            disponibilidades.add(disponibilidade);
                            fisioterapeuta.setDisponibilidade(disponibilidades);
                            fisioterapeuta.setObservacao("");
                            fisioterapeuta.setTelefone("1236-4123");
                            fisioterapeuta.setLogradouro("Rua 1");
                            fisioterapeuta.setBairro("Bairro 1");
                            fisioterapeuta.setCep("60740-460");
                            fisioterapeuta.setNumero(113);
                            fisioterapeuta.setEstado("CE");
                            fisioterapeuta.setCidade("Fortaleza");
                            fisioterapeuta.setNome("Teste Fisio 1");
                            fisioterapeuta.setEmail("q@gmail.com");
                            fisioterapeuta.setSenha("123654789");
                            fisioterapeuta.setTipo("tipo");

                            cadastrar(fisioterapeuta);
                        } else {
                            Toast.makeText(CadastroFisioterapeutaActivity.this,
                                    "Preencha a senha!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CadastroFisioterapeutaActivity.this,
                                "Preencha o email!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CadastroFisioterapeutaActivity.this,
                            "Preencha o nome!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void inicializaComponentes() {
        campoEmail = findViewById(R.id.editCadastroEmailFisio);
        campoSenha = findViewById(R.id.editCadastroSenhaFisio);
        campoNomeFisio = findViewById(R.id.editNomeFisio);
        botaoCadastrarFisio = findViewById(R.id.buttonCadastrarFisioterapeuta);
    }

    /**
     * Método responsável por cadastrar usuário com e-mail e senha
     * e fazer validações ao fazer o cadastro
     */
    public void cadastrar(final Fisioterapeuta fisioterapeuta) {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                fisioterapeuta.getEmail(),
                fisioterapeuta.getSenha()
        ).addOnCompleteListener(
                this,
                new OnCompleteListener<AuthResult>() {
                    @TargetApi(Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            try {
                                //Salvar dados no firebase
                                String idPaciente = task.getResult().getUser().getUid();
                                fisioterapeuta.setId(idPaciente);
                                fisioterapeuta.salvar();

                                Toast.makeText(CadastroFisioterapeutaActivity.this,
                                        "Cadastro com sucesso",
                                        Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                finish();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
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

                            Toast.makeText(CadastroFisioterapeutaActivity.this,
                                    "Erro: " + erroExcecao,
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );
    }
}