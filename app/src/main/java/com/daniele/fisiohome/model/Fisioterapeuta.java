package com.daniele.fisiohome.model;

import com.daniele.fisiohome.helper.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

import java.sql.Blob;
import java.util.List;

public class Fisioterapeuta extends Usuario {

    private Blob foto;
    private Integer numeroRegistro;
    private double precoConsulta;
    private List<Disponibilidade> disponibilidade;
    private String observacao;
    private String telefone;
    private String logradouro;
    private String bairro;
    private String cep;
    private Integer numero;
    private String estado;
    private String cidade;

    public Fisioterapeuta(){}

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public Integer getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(Integer numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public double getPrecoConsulta() {
        return precoConsulta;
    }

    public void setPrecoConsulta(double precoConsulta) {
        this.precoConsulta = precoConsulta;
    }

    public List<Disponibilidade> getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(List<Disponibilidade> disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }



    //    public static List<Fisioterapeuta> getFisioterapeutas() {
//        List<Fisioterapeuta> fisioterapeutas = null;
//        fisioterapeutas = new ArrayList<Fisioterapeuta>();
////        TODO - Buscar fisioterapeutas do Firebase
//
////        TODO - Criar lista fake de fisioterapeutas
//        Fisioterapeuta fisioterapeuta = new Fisioterapeuta();
//        fisioterapeuta.setNome("Teste 1");
//        fisioterapeuta.setNumeroRegistro(123);
//
//        Endereco endereco = new Endereco();
//        endereco.setLogradouro("Av das Magias Reais");
//        endereco.setNumero(120);
//        endereco.setBairro("Pampola");
//        endereco.setEstado("Ce");
//        endereco.setCidade("Fortaleza");
//        endereco.setCep("62870-555");
//
//        fisioterapeuta.setEnderecoFisioterapeuta(endereco);
//        fisioterapeuta.setTelefone("8599763525");
//        fisioterapeuta.setPrecoConsulta(50.00);
//
//        List<Disponibilidade> disponibilidades = new ArrayList<>();
//        Disponibilidade disponibilidade = new Disponibilidade();
//        disponibilidade.setHoras("12:00");
//
//        Disponibilidade disponibilidade2 = new Disponibilidade();
//        disponibilidade2.setHoras("15:00");
//
//        disponibilidades.add(disponibilidade);
//        disponibilidades.add(disponibilidade2);
//
//        fisioterapeuta.setDisponibilidade(disponibilidades);
//
//        Fisioterapeuta fisioterapeuta2 = new Fisioterapeuta();
//        fisioterapeuta2.setNome("Teste 2");
//        fisioterapeuta2.setNumeroRegistro(456);
//
//        Endereco endereco2 = new Endereco();
//        endereco2.setLogradouro("Av das Magias Reais");
//        endereco2.setNumero(120);
//        endereco2.setBairro("Pampola");
//        endereco2.setEstado("Ce");
//        endereco2.setCidade("Fortaleza");
//        endereco2.setCep("62870-555");
//
//        fisioterapeuta.setEnderecoFisioterapeuta(endereco2);
//        fisioterapeuta.setTelefone("8599763525");
//        fisioterapeuta.setPrecoConsulta(50.00);
//
//        fisioterapeutas.add(fisioterapeuta);
//        fisioterapeutas.add(fisioterapeuta2);
//
//
//        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
//        Query query = firebaseRef.child("fisioterapeutas");
//
//
//
//
//        return fisioterapeutas;
//    }

//   // private static String getUrlEventos() {
//        return "/evento/getEventos";
//    }

    public void salvar() {
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference fisiosRef = firebaseRef.child("fisioterapeutas").child(getId());
        fisiosRef.setValue(this);
    }
}
