package com.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioDAO udao;

    public void inserir(Usuario usuario) {
        udao.inserir(usuario);
    }

    public boolean login(String email, String senha) {
        return udao.login(email, senha);
    }

    public Usuario obterUsuario(int id) {
        return udao.obterUsuario(id);
    }

    public List<Usuario> obterTodosUsuarios() {
        return udao.obterTodosUsuarios();
    }
}