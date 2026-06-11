package com.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MensagemChatDAO {

    @Autowired
    private JdbcTemplate jdbc;

    private final RowMapper<MensagemChat> mapper = (rs, rowNum) -> new MensagemChat(
        rs.getLong("id"),
        rs.getString("nome_army"),
        rs.getString("conteudo"),
        rs.getObject("resposta_id") != null ? rs.getLong("resposta_id") : null,
        rs.getString("resposta_conteudo"),
        rs.getInt("curtidas"),
        rs.getTimestamp("criado_em").toLocalDateTime()
    );

    public List<MensagemChat> obterTodas() {
        String sql = """
            SELECT m.id, m.nome_army, m.conteudo, m.resposta_id, m.criado_em,
                   r.conteudo AS resposta_conteudo,
                   (SELECT COUNT(*) FROM curtida_chat c WHERE c.mensagem_id = m.id) AS curtidas
            FROM mensagem_chat m
            LEFT JOIN mensagem_chat r ON r.id = m.resposta_id
            ORDER BY m.criado_em DESC
            """;
        return jdbc.query(sql, mapper);
    }

    public void inserir(String nomeArmy, String conteudo, Long respostaId) {
        String sql = "INSERT INTO mensagem_chat (nome_army, conteudo, resposta_id) VALUES (?, ?, ?)";
        jdbc.update(sql, nomeArmy, conteudo, respostaId);
    }

    public boolean curtidaExiste(Long mensagemId, String sessionId) {
        String sql = "SELECT COUNT(*) FROM curtida_chat WHERE mensagem_id = ? AND session_id = ?";
        Integer count = jdbc.queryForObject(sql, Integer.class, mensagemId, sessionId);
        return count != null && count > 0;
    }

    public void curtir(Long mensagemId, String sessionId) {
        String sql = "INSERT INTO curtida_chat (mensagem_id, session_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
        jdbc.update(sql, mensagemId, sessionId);
    }

    public void descurtir(Long mensagemId, String sessionId) {
        String sql = "DELETE FROM curtida_chat WHERE mensagem_id = ? AND session_id = ?";
        jdbc.update(sql, mensagemId, sessionId);
    }
}