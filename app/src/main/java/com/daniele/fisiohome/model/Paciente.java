package com.daniele.fisiohome.model;

import java.sql.Blob;

public class Paciente extends Usuario {

    private String nome;
    private Endereco endereco;
    private Blob foto;
    private String idade;
    private String sexo;
    private double peso;
    private double altura;
    private String observacao;

    public Paciente(){}
}
