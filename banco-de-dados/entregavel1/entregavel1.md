# Sistema de Loja de Jogos

## 1. Cenário

O sistema tem como objetivo gerenciar uma loja digital de jogos, permitindo o cadastro de usuários e jogos, além do registro das compras realizadas.

## 2. Requisitos

- Cadastrar usuários.
- Cadastrar jogos disponíveis na loja.
- Registrar compras realizadas pelos usuários.
- Permitir que um usuário realize várias compras.
- Permitir que diferentes jogos sejam registrados nas compras.

## 3. Entidades e Atributos

### Usuário

- **id_usuario (Chave Primária)**
- nome
- email
- data_nascimento

### Jogo

- **id_jogo (Chave Primária)**
- nome
- genero
- preco

### Compra

- **id_compra (Chave Primária)**
- data_compra
- valor_total
- **id_usuario (Chave Estrangeira)**
- **id_jogo (Chave Estrangeira)**

## 4. Relacionamentos e Cardinalidade

### Usuário e Compra — 1:N

Um **usuário pode realizar várias compras**, enquanto cada **compra pertence a apenas um usuário**.

**Cardinalidade:** Usuário **1:N** Compra.

### Jogo e Compra — 1:N

Um **jogo pode aparecer em várias compras**, enquanto cada **compra está relacionada a apenas um jogo**.

**Cardinalidade:** Jogo **1:N** Compra.

## 5. Resumo dos Relacionamentos

- **Usuário → Compra:** 1:N
- **Compra → Usuário:** N:1
- **Jogo → Compra:** 1:N
- **Compra → Jogo:** N:1