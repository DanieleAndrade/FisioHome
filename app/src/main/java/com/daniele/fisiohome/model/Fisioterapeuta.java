package com.daniele.fisiohome.model;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

public class Fisioterapeuta extends Usuario {

    private Endereco enderecoFisioterapeuta;
    private Blob foto;
    private Integer numeroRegistro;
    private double precoConsulta;
    private List<Disponibilidade> disponibilidade;
    private String observacao;

    public Fisioterapeuta(){}

    public Endereco getEnderecoFisioterapeuta() {
        return enderecoFisioterapeuta;
    }

    public void setEnderecoFisioterapeuta(Endereco enderecoFisioterapeuta) {
        this.enderecoFisioterapeuta = enderecoFisioterapeuta;
    }

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
}
