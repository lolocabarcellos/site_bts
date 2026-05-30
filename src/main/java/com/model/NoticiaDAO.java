package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class NoticiaDAO {

    @Autowired
    DataSource dataSource;

    private JdbcTemplate jdbc;

    @PostConstruct
    private void postConstruct() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserir(Noticia noticia) {
        String sql = "INSERT INTO noticia (titulo, resumo, conteudo, imagem) VALUES (?, ?, ?, ?)";
        Object[] obj = new Object[4];
        obj[0] = noticia.getTitulo();
        obj[1] = noticia.getResumo();
        obj[2] = noticia.getConteudo();
        obj[3] = noticia.getImagem();
        jdbc.update(sql, obj);
    }

   public Noticia obterNoticia(int id) {
        String sql = "SELECT * FROM noticia WHERE id = ?";
        Map<String, Object> registros = jdbc.queryForMap(sql, id);
        return Noticia.converterRegistros(registros);
    }

    public List<Noticia> obterTodasNoticias() {
        String sql = "SELECT * FROM noticia";
        List<Map<String, Object>> listaRegistros = jdbc.queryForList(sql);
        ArrayList<Noticia> aux = new ArrayList<>();
        for (Map<String, Object> registro : listaRegistros) {
            aux.add(Noticia.converterRegistros(registro));
        }
        return aux;
    }
}