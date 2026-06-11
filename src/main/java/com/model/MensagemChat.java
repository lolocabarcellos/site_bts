package com.model;

import java.time.LocalDateTime;

public record MensagemChat(
    Long id,
    String nomeArmy,
    String conteudo,
    Long respostaId,
    String respostaConteudo,
    int curtidas,
    LocalDateTime criadoEm
) {}