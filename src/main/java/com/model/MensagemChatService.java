package com.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MensagemChatService {

    @Autowired
    private MensagemChatDAO dao;

    public List<MensagemChat> obterTodas() {
        return dao.obterTodas();
    }

    public void enviar(String conteudo, Long respostaId) {
        if (conteudo == null || conteudo.isBlank()) return;
        dao.inserir("Army", conteudo.trim(), respostaId);
    }

    public void toggleCurtida(Long mensagemId, String sessionId) {
        if (dao.curtidaExiste(mensagemId, sessionId)) {
            dao.descurtir(mensagemId, sessionId);
        } else {
            dao.curtir(mensagemId, sessionId);
        }
    }

    public boolean curtidaExiste(Long mensagemId, String sessionId) {
        return dao.curtidaExiste(mensagemId, sessionId);
    }
}