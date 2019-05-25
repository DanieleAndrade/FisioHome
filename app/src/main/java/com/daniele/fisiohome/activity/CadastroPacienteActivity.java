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

import com.daniele.fisiohome.FisioHome;
import com.daniele.fisiohome.R;
import com.daniele.fisiohome.helper.ConfiguracaoFirebase;
import com.daniele.fisiohome.model.Endereco;
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
    private EditText campoEmail, campoSenha, campoRua, campoCidade, campoEstado, campoNumero, campoBairro;
    private EditText campoNomePaciente, campoCPF;
    private FirebaseAuth autenticacao;
    private Paciente paciente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_paciente);

        inicializaComponentes();
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        botaoCadastrarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = campoEmail.getText().toString().trim();
                String senha = campoSenha.getText().toString().trim();
                String nome = campoNomePaciente.getText().toString().trim();
                String cpf = campoCPF.getText().toString().trim();
                String rua = campoRua.getText().toString().trim();
                String cidade = campoCidade.getText().toString().trim();
                String estado = campoEstado.getText().toString().trim();
                String bairro = campoBairro.getText().toString().trim();
                String numero = campoNumero.getText().toString().trim();

                if( !nome.isEmpty() ){
                    if( !email.isEmpty() ){
                        if( !senha.isEmpty() ){
                            if(!cpf.isEmpty() ) {
                                paciente = new Paciente();
                                paciente.setNome(nome);
                                paciente.setEmail(email);
                                paciente.setSenha(senha);
                                paciente.setCpf(cpf);

                                if(!rua.isEmpty() && !bairro.isEmpty() && !cidade.isEmpty() && !numero.isEmpty() && !estado.isEmpty()){
                                    Endereco endereco = new Endereco();
                                    endereco.setBairro(bairro);
                                    endereco.setCidade(cidade);
                                    endereco.setLogradouro(rua);
                                    endereco.setEstado(estado);
                                    endereco.setNumero(numero);
                                    paciente.setEndereco(endereco);
                                    cadastrar( paciente );

                                }else{
                                    Toast.makeText(CadastroPacienteActivity.this,
                                            "Preencha o endereço completo!",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                Toast.makeText(CadastroPacienteActivity.this,
                                        "Preencha o CPF!",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(CadastroPacienteActivity.this,
                                    "Preencha a senha!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(CadastroPacienteActivity.this,
                                "Preencha o email!",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CadastroPacienteActivity.this,
                            "Preencha o nome!",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    /**
     * Método responsável por cadastrar usuário com e-mail e senha
     * e fazer validações ao fazer o cadastro
     */
    public void cadastrar(final Paciente paciente){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                paciente.getEmail(),
                paciente.getSenha()
        ).addOnCompleteListener(
                this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if( task.isSuccessful() ){

                            try {


                                //Salvar dados no firebase
                                String idPaciente = task.getResult().getUser().getUid();
                                paciente.setId( idPaciente );

                                FisioHome.setPaciente(paciente);
                                paciente.salvar();

                                Toast.makeText(CadastroPacienteActivity.this,
                                        "Cadastro com sucesso",
                                        Toast.LENGTH_SHORT).show();

                                startActivity( new Intent(getApplicationContext(), HomeActivity.class));
                                finish();

                            }catch (Exception e){
                                e.printStackTrace();
                            }



                        }else {


                            String erroExcecao = "";
                            try{
                                throw task.getException();
                            }catch (FirebaseAuthWeakPasswordException e){
                                erroExcecao = "Digite uma senha mais forte!";
                            }catch (FirebaseAuthInvalidCredentialsException e){
                                erroExcecao = "Por favor, digite um e-mail válido";
                            }catch (FirebaseAuthUserCollisionException e){
                                erroExcecao = "Este conta já foi cadastrada";
                            } catch (Exception e) {
                                erroExcecao = "ao cadastrar usuário: "  + e.getMessage();
                                e.printStackTrace();
                            }

                            Toast.makeText(CadastroPacienteActivity.this,
                                    "Erro: " + erroExcecao ,
                                    Toast.LENGTH_SHORT).show();


                        }

                    }
                }
        );

    }
    private void inicializaComponentes() {
        campoEmail = findViewById(R.id.editCadastroEmail);
        campoSenha = findViewById(R.id.editCadastroSenha);
        campoCPF = findViewById(R.id.editCPF);
        campoNomePaciente = findViewById(R.id.editNomePaciente);
        botaoCadastrarPaciente = findViewById(R.id.botaoCadastrarPaciente);
        campoRua = findViewById(R.id.campoRua);
        campoCidade = findViewById(R.id.campoCidade);
        campoEstado = findViewById(R.id.campoEstado);
        campoBairro = findViewById(R.id.campoBairro);
        campoNumero = findViewById(R.id.campoNumero);
    }

    public void home() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
