CREATE TABLE IF NOT EXISTS usuario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS noticia (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    resumo TEXT,
    conteudo TEXT NOT NULL,
    imagem VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS mensagem_chat (
    id BIGSERIAL PRIMARY KEY,
    nome_army VARCHAR(100) NOT NULL,
    conteudo TEXT NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS curtida_chat (
    id BIGSERIAL PRIMARY KEY,
    mensagem_id BIGINT NOT NULL REFERENCES mensagem_chat(id) ON DELETE CASCADE,
    session_id VARCHAR(100) NOT NULL,
    UNIQUE(mensagem_id, session_id)
);

ALTER TABLE mensagem_chat ADD COLUMN IF NOT EXISTS resposta_id BIGINT REFERENCES mensagem_chat(id) ON DELETE SET NULL;

CREATE TABLE IF NOT EXISTS curtida_chat (
    id BIGSERIAL PRIMARY KEY,
    mensagem_id BIGINT NOT NULL REFERENCES mensagem_chat(id) ON DELETE CASCADE,
    session_id VARCHAR(100) NOT NULL,
    UNIQUE(mensagem_id, session_id)
);