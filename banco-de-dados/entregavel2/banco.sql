CREATE TABLE usuario (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE jogo (
    id_jogo SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2) NOT NULL
);

CREATE TABLE compra (
    id_compra SERIAL PRIMARY KEY,
    data_compra DATE NOT NULL,
    id_usuario INT NOT NULL,
    id_jogo INT NOT NULL,

    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_jogo) REFERENCES jogo(id_jogo)
);