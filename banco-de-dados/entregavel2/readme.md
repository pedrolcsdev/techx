# 🎮 Sistema de Loja de Jogos

## Descrição

Modelo conceitual simplificado de um sistema para compra de jogos por usuários.

## Entidades e Atributos

### Usuário
•⁠  ⁠⁠ id_usuario ⁠ — Chave Primária
•⁠  ⁠⁠ nome ⁠
•⁠  ⁠⁠ email ⁠

### Jogo
•⁠  ⁠⁠ id_jogo ⁠ — Chave Primária
•⁠  ⁠⁠ nome ⁠
•⁠  ⁠⁠ preco ⁠

### Compra
•⁠  ⁠⁠ id_compra ⁠ — Chave Primária
•⁠  ⁠⁠ data_compra ⁠
•⁠  ⁠⁠ id_usuario ⁠
•⁠  ⁠⁠ id_jogo ⁠

## Relacionamentos

•⁠  ⁠*Usuário (1) — (N) Compra*
  - Um usuário pode realizar várias compras.

•⁠  ⁠*Jogo (1) — (N) Compra*
  - Um jogo pode aparecer em várias compras.