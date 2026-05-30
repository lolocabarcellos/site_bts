package com.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaService {

    @Autowired
    NoticiaDAO ndao;

    public void inserir(Noticia noticia) {
        ndao.inserir(noticia);
    }

    public Noticia obterNoticia(int id) {
        return ndao.obterNoticia(id);
    }

    public List<Noticia> obterTodasNoticias() {
        return ndao.obterTodasNoticias();
    }
}