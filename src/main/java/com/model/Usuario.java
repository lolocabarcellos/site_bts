package com.model;

import java.util.Map;

public class Usuario {
    private int id;
    private Nome nome;
    private Email email;
    private Senha senha;

    public Usuario() {
    }

    public Usuario(int id, Nome nome, Email email, Senha senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public static Usuario converterRegistros(Map<String, Object> registros) {
        int id = ((Number) registros.get("id")).intValue();
        Nome nome   = new Nome((String) registros.get("nome"));
        Email email = new Email((String) registros.get("email"));
        Senha senha = new Senha((String) registros.get("senha"));
        return new Usuario(id, nome, email, senha);
    }

    public void setId(int id){
        this.id = id;
    }

    public void setNome(Nome nome){
        this.nome = nome;
    }

    public void setEmail(Email email){
        this.email = email;
    }

    public void setSenha(Senha senha){
        this.senha = senha;
    }

    public int getId(){
        return id;
    }

    public Nome getNome(){
        return nome;
    }

    public Email getEmail(){
        return email;
    }

    public Senha getSenha(){
        return senha;
    }
}