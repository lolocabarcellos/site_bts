package com.model;

import java.util.Map;

public class Noticia {
    private int id;
    private String titulo, resumo, conteudo, imagem;

    public Noticia() {
    }

    public Noticia(int id, String titulo, String resumo, String conteudo, String imagem) {
        this.id = id;
        this.titulo = titulo;
        this.resumo = resumo;
        this.conteudo = conteudo;
        this.imagem = imagem;
    }

    public static Noticia converterRegistros(Map<String, Object> registros) {
        int id = ((Number) registros.get("id")).intValue();
        String titulo = (String) registros.get("titulo");
        String resumo = (String) registros.get("resumo");
        String conteudo = (String) registros.get("conteudo");
        String imagem = (String) registros.get("imagem");
        return new Noticia(id, titulo, resumo, conteudo, imagem);
    }

    public void setId(int id)             { this.id = id; }
    public void setTitulo(String titulo)  { this.titulo = titulo; }
    public void setResumo(String resumo)  { this.resumo = resumo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
    public void setImagem(String imagem)  { this.imagem = imagem; }

    public int getId()           {
        return id;
    }

    public String getTitulo()    {
        return titulo;
    }

    public String getResumo()    {
        return resumo;
    }

    public String getConteudo()  {
        return conteudo;
    }

    public String getImagem()    {
        return imagem;
    }
}