CREATE TABLE usuario (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    data_cadastro TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE jogo (
    id_jogo SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    preco DECIMAL(10,2) NOT NULL CHECK (preco >= 0)
);

CREATE TABLE compra (
    id_compra SERIAL PRIMARY KEY,
    id_usuario INTEGER NOT NULL,
    id_jogo INTEGER NOT NULL,
    data_compra TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_compra_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES usuario(id_usuario),

    CONSTRAINT fk_compra_jogo
        FOREIGN KEY (id_jogo)
        REFERENCES jogo(id_jogo)
);

CREATE INDEX idx_compra_usuario
ON compra(id_usuario);

CREATE INDEX idx_compra_jogo
ON compra(id_jogo);

BEGIN;

WITH novo_usuario AS (
    INSERT INTO usuario (nome, email)
    VALUES ('Pedro Silva', 'pedro@email.com')
    RETURNING id_usuario
),
novo_jogo AS (
    INSERT INTO jogo (nome, preco)
    VALUES ('Minecraft', 99.90)
    RETURNING id_jogo
)
INSERT INTO compra (id_usuario, id_jogo)
SELECT novo_usuario.id_usuario, novo_jogo.id_jogo
FROM novo_usuario
CROSS JOIN novo_jogo;

SAVEPOINT compra_1;

COMMIT;

BEGIN;

WITH novo_usuario AS (
    INSERT INTO usuario (nome, email)
    VALUES ('Ana Silva', 'ana@email.com')
    RETURNING id_usuario
),
novo_jogo AS (
    INSERT INTO jogo (nome, preco)
    VALUES ('Stardew Valley', 24.99)
    RETURNING id_jogo
)
INSERT INTO compra (id_usuario, id_jogo)
SELECT novo_usuario.id_usuario, novo_jogo.id_jogo
FROM novo_usuario
CROSS JOIN novo_jogo;

SAVEPOINT compra_2;

COMMIT;

BEGIN;

INSERT INTO usuario (nome, email)
VALUES ('Usuario Teste', 'teste@email.com');

ROLLBACK;

BEGIN;

INSERT INTO usuario (nome, email)
VALUES ('Carlos Lima', 'carlos@email.com');

SAVEPOINT cadastro_usuario;

INSERT INTO usuario (nome, email)
VALUES ('Usuario Temporario', 'temporario@email.com');

ROLLBACK TO SAVEPOINT cadastro_usuario;

COMMIT;

EXPLAIN ANALYZE
SELECT id_compra, data_compra
FROM compra
WHERE id_usuario = 1;

EXPLAIN ANALYZE
SELECT id_compra, data_compra
FROM compra
WHERE id_jogo = 1;

CREATE ROLE funcionario_loja;

GRANT SELECT ON usuario, jogo, compra
TO funcionario_loja;

GRANT INSERT, UPDATE ON compra
TO funcionario_loja;

GRANT USAGE, SELECT
ON SEQUENCE compra_id_compra_seq
TO funcionario_loja;

CREATE USER funcionario
WITH PASSWORD 'senha_funcionario';

GRANT funcionario_loja
TO funcionario;

CREATE USER consulta
WITH PASSWORD 'senha_consulta';

GRANT SELECT ON jogo
TO consulta;

REVOKE INSERT, UPDATE, DELETE
ON jogo
FROM consulta;
