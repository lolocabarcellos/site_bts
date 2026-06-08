package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class UsuarioDAO {

    @Autowired
    DataSource dataSource;

    private JdbcTemplate jdbc;

    @PostConstruct
    private void postConstruct() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
        Object[] obj = new Object[3];
        obj[0] = usuario.getNome().valor();
        obj[1] = usuario.getEmail().valor();
        obj[2] = usuario.getSenha().valor();
        jdbc.update(sql, obj);
    }

    public boolean login(String email, String senha) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE email = ? AND senha = ?";
        Map<String, Object> resultado = jdbc.queryForMap(sql, email, senha);
        long count = (long) resultado.get("count");
        return count > 0;
    }

    public Usuario obterUsuario(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        Map<String, Object> registros = jdbc.queryForMap(sql, id);
        return Usuario.converterRegistros(registros);
    }

    public List<Usuario> obterTodosUsuarios() {
        String sql = "SELECT * FROM usuario";
        List<Map<String, Object>> listaRegistros = jdbc.queryForList(sql);
        ArrayList<Usuario> aux = new ArrayList<>();
        for (Map<String, Object> registro : listaRegistros) {
            aux.add(Usuario.converterRegistros(registro));
        }
        return aux;
    }
}