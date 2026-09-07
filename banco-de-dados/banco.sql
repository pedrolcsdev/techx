CREATE TABLE usuario (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE jogo (
    id_jogo SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2) NOT NULL CHECK (preco >= 0)
);

CREATE TABLE compra (
    id_compra SERIAL PRIMARY KEY,
    data_compra DATE NOT NULL DEFAULT CURRENT_DATE,
    id_usuario INT NOT NULL,
    id_jogo INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_jogo) REFERENCES jogo(id_jogo)
);

INSERT INTO usuario (nome, email) VALUES
('Pedro', 'pedro@email.com'),
('Ana', 'ana@email.com'),
('Lucas', 'lucas@email.com');

INSERT INTO jogo (nome, preco) VALUES
('Minecraft', 99.90),
('EA Sports FC 26', 299.90),
('Grand Theft Auto V', 149.90);

INSERT INTO compra (data_compra, id_usuario, id_jogo) VALUES
('2026-09-01', 1, 1),
('2026-09-02', 2, 2),
('2026-09-03', 3, 3);

SELECT
    usuario.nome AS usuario,
    jogo.nome AS jogo,
    compra.data_compra
FROM compra
INNER JOIN usuario
    ON compra.id_usuario = usuario.id_usuario
INNER JOIN jogo
    ON compra.id_jogo = jogo.id_jogo;

SELECT
    usuario.nome AS usuario,
    jogo.nome AS jogo,
    jogo.preco,
    compra.data_compra
FROM compra
INNER JOIN usuario
    ON compra.id_usuario = usuario.id_usuario
INNER JOIN jogo
    ON compra.id_jogo = jogo.id_jogo
ORDER BY compra.data_compra;