# 🎮 Sistema de Loja de Jogos — Entregável 4

## Descrição

Implementação física de um banco de dados para uma loja de jogos usando PostgreSQL. O banco registra usuários, jogos e compras, com recursos de integridade, transações, índices e controle de acesso.

## Estrutura do Banco

### Usuario

- `id_usuario` — chave primária
- `nome`
- `email`
- `data_cadastro`

### Jogo

- `id_jogo` — chave primária
- `nome`
- `preco`

### Compra

- `id_compra` — chave primária
- `id_usuario` — chave estrangeira
- `id_jogo` — chave estrangeira
- `data_compra`

## Relacionamentos

Um usuário pode realizar várias compras. Um jogo pode aparecer em várias compras.

`Usuario (1) --- (N) Compra (N) --- (1) Jogo`

## Tipos de Dados e Restrições

- `SERIAL` gera os IDs das chaves primárias.
- `INTEGER` armazena as chaves estrangeiras.
- `VARCHAR` armazena nomes e emails.
- `DECIMAL(10,2)` armazena o preço do jogo.
- `TIMESTAMPTZ` armazena datas e horários com fuso horário.
- `PRIMARY KEY` identifica cada registro; `FOREIGN KEY` mantém os relacionamentos.
- `NOT NULL` exige valores nos campos obrigatórios.
- `UNIQUE` impede emails duplicados.
- `CHECK` impede preços negativos.
- `DEFAULT` preenche automaticamente a data de cadastro e a data da compra.

## Estratégia de Indexação

Foram criados índices nas colunas `id_usuario` e `id_jogo` da tabela `compra`, usadas nos relacionamentos e consultas. O campo `email` possui restrição `UNIQUE`, que já cria um índice único no PostgreSQL.

## Transações

O arquivo `banco.sql` inclui transações de cadastro de usuários, jogos e compras, além de exemplos de `COMMIT`, `ROLLBACK`, `SAVEPOINT`, `ROLLBACK TO SAVEPOINT` e `RETURNING`. O `RETURNING` recupera os IDs gerados para associar cada compra ao usuário e ao jogo.

## EXPLAIN ANALYZE

Há consultas por `id_usuario` e `id_jogo` para analisar o acesso à tabela `compra`. O plano e os tempos dependem do volume de dados e do estado do banco; com poucos registros, o PostgreSQL pode escolher `Seq Scan` em vez dos índices.

## Controle de Acesso

A role `funcionario_loja` permite consultar usuários, jogos e compras, além de inserir e atualizar compras. O usuário `funcionario` recebe essa role. O usuário `consulta` recebe permissão de leitura apenas na tabela `jogo`.

As permissões são concedidas com `GRANT`, seguindo o princípio do menor privilégio.

## Tecnologias

- PostgreSQL
- SQL
- Git
- GitHub
