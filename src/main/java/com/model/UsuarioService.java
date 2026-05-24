package com.model;

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
}
