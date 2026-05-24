package com.model;

// POJO
// aqui vou ter os modelos para as tabelas do banco
public class Usuario {
    private int id;
    private String nome, cpf;

    public Usuario() {
    }

    public Usuario(String nome, String cpf) {
        // o id é incrementável
        this.nome = nome;
        this.cpf = cpf;
    }

    public Usuario(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }


}


