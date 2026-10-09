# Sistema Acadêmico — Sprint 6

## Descrição

Este projeto é um banco de dados PostgreSQL para gerenciamento acadêmico. Ele reúne informações de alunos, professores, cursos, disciplinas, turmas, matrículas, avaliações, notas e frequência.

## Estrutura do Banco

O banco possui 10 tabelas, com pelo menos 15 registros em cada uma:

- `aluno`
- `professor`
- `disciplina`
- `matricula`
- `curso`
- `turma`
- `avaliacao`
- `nota`
- `frequencia`
- `curso_disciplina`

## Modelo Lógico

- Um professor pode ministrar várias disciplinas (`professor` → `disciplina`).
- Alunos e disciplinas se relacionam por meio de `matricula` (`aluno` → `matricula` ← `disciplina`).
- Uma disciplina pode aparecer em várias turmas, e cada turma possui um professor (`disciplina` → `turma` ← `professor`).
- Uma turma pode ter várias avaliações (`turma` → `avaliacao`).
- As notas relacionam avaliações e alunos (`avaliacao` → `nota` ← `aluno`).
- A frequência relaciona alunos e turmas (`aluno` → `frequencia` ← `turma`).
- Cursos e disciplinas se relacionam por `curso_disciplina` (`curso` → `curso_disciplina` ← `disciplina`).

## Restrições

- `PRIMARY KEY`: identifica cada registro de forma única.
- `FOREIGN KEY`: liga tabelas e impede referências a registros inexistentes.
- `NOT NULL`: exige o preenchimento dos campos obrigatórios.
- `UNIQUE`: impede valores ou combinações duplicadas onde isso é necessário.
- `CHECK`: limita valores, como carga horária positiva e notas de 0 a 10.
- `DEFAULT`: define um valor automático quando nenhum é informado, como a data de matrícula e a presença.

## Normalização

As informações foram separadas em tabelas específicas para reduzir repetição. As chaves estrangeiras mantêm os relacionamentos entre elas.

## Índices

O arquivo `03_indices.sql` cria os seguintes índices:

- `idx_aluno_nome`, `idx_professor_nome` e `idx_disciplina_nome`: buscas por nome.
- `idx_matricula_data`: buscas por data de matrícula.
- `idx_matricula_aluno` e `idx_matricula_disciplina`: relacionamentos e consultas de matrículas.
- `idx_disciplina_professor`: relacionamento entre disciplinas e professores.
- `idx_turma_disciplina` e `idx_turma_professor`: relacionamentos usados nas consultas de turmas.
- `idx_avaliacao_turma`: relacionamento entre avaliações e turmas.
- `idx_nota_aluno` e `idx_frequencia_aluno`: consultas de notas e frequência por aluno.

Os índices foram aplicados principalmente em colunas usadas em buscas, relacionamentos e `JOIN`s. Não é necessário indexar todas as colunas, pois índices também têm custo nas operações de `INSERT`, `UPDATE` e `DELETE`. Chaves primárias e restrições `UNIQUE` já criam seus próprios índices no PostgreSQL.

## Consultas

O arquivo `04_consultas.sql` contém 12 consultas com `SELECT`, `WHERE`, `INNER JOIN`, `LEFT JOIN`, `GROUP BY`, `HAVING`, `ORDER BY`, `COUNT`, `AVG` e subconsultas:

1. Listar alunos.
2. Listar professores.
3. Buscar a disciplina Banco de Dados pelo nome.
4. Mostrar alunos e disciplinas em que estão matriculados.
5. Mostrar disciplinas e seus professores.
6. Contar alunos por disciplina.
7. Calcular a média de notas por aluno.
8. Mostrar alunos com média igual ou superior a 7.
9. Encontrar alunos com nota acima da média geral.
10. Mostrar alunos e notas em ordem decrescente de nota.
11. Contar faltas por aluno.
12. Mostrar cursos e suas disciplinas.

## Transações

O arquivo `05_transacoes.sql` demonstra `BEGIN`, `COMMIT`, `ROLLBACK`, `SAVEPOINT`, `ROLLBACK TO SAVEPOINT` e `RETURNING`. Uma transação cadastra aluno e matrícula; outra cadastra professor e disciplina. As CTEs usam o ID retornado pelo próprio `INSERT`. Também há exemplos de operações canceladas com `ROLLBACK` e `ROLLBACK TO SAVEPOINT`.

## Controle de Acesso

O arquivo `06_permissoes.sql` cria as roles `secretaria_academica` e `professor_academico`, além dos usuários `usuario_secretaria` e `usuario_professor`.

A secretaria pode consultar as tabelas acadêmicas previstas e inserir ou atualizar matrículas. O professor pode consultar alunos, disciplinas, turmas e avaliações, além de inserir ou atualizar notas e frequências. As permissões seguem o princípio do menor privilégio: cada role recebe apenas o acesso necessário para suas atividades. O script usa `GRANT` e `REVOKE`. Para executar o arquivo, use um superusuário ou uma conta com `CREATEROLE` que também seja proprietária das tabelas, sequences e do schema (ou tenha opção de concessão sobre eles). `CREATEROLE` sozinho permite criar roles e usuários, mas não concede automaticamente permissão para conceder acesso aos objetos.

## EXPLAIN ANALYZE

`EXPLAIN ANALYZE` executa a consulta e exibe o plano real junto com informações de execução. Os resultados reais devem ser obtidos executando `07_explain.sql` no PostgreSQL; nenhum resultado foi presumido neste README. Como as tabelas têm poucos registros, o PostgreSQL pode optar por `Seq Scan` mesmo quando existe um índice.

## Estrutura dos Arquivos

```text
entregavel6/
├── README.md
├── 01_ddl.sql
├── 02_inserts.sql
├── 03_indices.sql
├── 04_consultas.sql
├── 05_transacoes.sql
├── 06_permissoes.sql
├── 07_explain.sql
├── 08_backup.sh
└── 09_mongodb.js
```

## Backup e retenção

O script `08_backup.sh` gera um backup lógico no formato customizado do PostgreSQL (`pg_dump -Fc`), com data e hora no nome. Backups concluídos com mais de 30 dias são removidos. Se o `pg_dump` falhar, o arquivo temporário é apagado e o script termina com erro, sem publicar um backup incompleto.

O script usa as variáveis padrão `PGHOST`, `PGPORT`, `PGUSER` e `PGPASSWORD` do cliente PostgreSQL quando necessárias. Para não expor senha no cron, configure o arquivo `.pgpass` do usuário que executará o agendamento, com permissão `0600`.
`DB_NAME` deve conter apenas letras, números, `_` ou `-`.

### Regra 3-2-1

A regra recomenda manter **3 cópias** dos dados, em **2 tipos de mídia** diferentes, com **1 cópia fora do local principal**. O script cria um arquivo local; ele sozinho não completa a regra. Para completá-la, copie os arquivos de backup para outra mídia (por exemplo, outro servidor/disco) e mantenha uma cópia em outro local físico ou em armazenamento remoto. Restrinja o acesso a esses destinos e teste a restauração periodicamente.

```bash
chmod +x entregavel6/08_backup.sh
DB_NAME=sistema_academico BACKUP_DIR=/var/backups/postgresql ./entregavel6/08_backup.sh
```

O usuário do sistema precisa ter permissão de escrita em `BACKUP_DIR`. Sem essa variável, os arquivos são gravados em `entregavel6/backups/`.

### Agendamento diário

Edite o crontab do usuário que tem acesso ao banco (`crontab -e`) e inclua a linha abaixo, substituindo o caminho pelo caminho absoluto deste repositório:

```cron
0 2 * * * DB_NAME=sistema_academico BACKUP_DIR=/var/backups/postgresql /caminho/absoluto/banco-de-dados/entregavel6/08_backup.sh
```

A expressão `0 2 * * *` executa o script diariamente às 02:00, no fuso horário configurado para o serviço cron.

### Teste de restauração e integridade

Faça o teste em um banco separado para preservar o banco original. No host de PostgreSQL, crie o banco de teste, restaure o arquivo customizado mais recente e compare as contagens antes do backup e depois da restauração:

```bash
createdb sistema_academico_teste
pg_restore --no-owner --no-privileges --dbname=sistema_academico_teste /var/backups/postgresql/postgres_sistema_academico_AAAAMMDD_HHMMSS.dump
```

Execute a consulta abaixo no banco de origem antes do backup e no banco restaurado. A restauração está íntegra quando cada contagem coincide com a anotada na origem.

```sql
SELECT 'aluno' AS tabela, COUNT(*) FROM aluno
UNION ALL SELECT 'professor', COUNT(*) FROM professor
UNION ALL SELECT 'disciplina', COUNT(*) FROM disciplina
UNION ALL SELECT 'matricula', COUNT(*) FROM matricula
UNION ALL SELECT 'curso', COUNT(*) FROM curso
UNION ALL SELECT 'turma', COUNT(*) FROM turma
UNION ALL SELECT 'avaliacao', COUNT(*) FROM avaliacao
UNION ALL SELECT 'nota', COUNT(*) FROM nota
UNION ALL SELECT 'frequencia', COUNT(*) FROM frequencia
UNION ALL SELECT 'curso_disciplina', COUNT(*) FROM curso_disciplina
ORDER BY tabela;
```

Conecte-se a cada banco com `psql -d NOME_DO_BANCO`, execute a consulta e compare os resultados. Após comparar, remova o banco de teste com `dropdb sistema_academico_teste`, se não precisar mantê-lo.

## Introdução à modelagem NoSQL com MongoDB

### Domínio e modelo

O exemplo representa o mesmo Sistema Acadêmico das tabelas relacionais. O script `09_mongodb.js` cria o banco `sistema_academico_sprint6` e duas collections: `alunos` e `disciplinas`. Ele insere 10 alunos e 5 disciplinas (15 documentos ao todo). Alguns alunos têm campos opcionais como `telefone`, `acessibilidade` ou `observacao`, demonstrando que documentos da mesma collection podem ter campos diferentes.

Execute com MongoDB disponível localmente:

```bash
mongosh entregavel6/09_mongodb.js
```

### Embedding e Referencing

Cada aluno contém um array `matriculas`, e cada matrícula contém suas notas. Esses dados são embutidos (**Embedding**) porque a aplicação costuma consultar as matrículas e notas junto com o aluno.

Cada matrícula guarda `disciplinaId`, que referencia o `_id` do documento na collection `disciplinas` (**Referencing**). A disciplina é compartilhada por vários alunos e pode ser consultada ou alterada independentemente; mantê-la em um único documento evita duplicar seu nome e carga horária em cada aluno. Em MongoDB, a referência não cria uma chave estrangeira automática: a aplicação consulta os dois documentos quando precisa dos dados relacionados.

### Consultas, índices e explain

O script demonstra `find()` com filtro de nota mínima, ordenação por nome e projeção apenas de nome e e-mail. Também consulta alunos por `disciplinaId`. Ele cria três índices: e-mail dos alunos (único), referência da disciplina nas matrículas e código da disciplina (único). Os dois últimos exemplos do script usam `explain("executionStats")` para exibir o plano e as estatísticas da consulta; procure `IXSCAN` para ver que um índice foi usado.

Para verificar os dados inseridos no `mongosh`:

```javascript
const academia = db.getSiblingDB("sistema_academico_sprint6");
academia.alunos.countDocuments({ sprint6: true });       // esperado: 10
academia.disciplinas.countDocuments({ sprint6: true });  // esperado: 5
```

### Backup e restauração do MongoDB

Para gerar um backup lógico das duas collections:

```bash
mongodump --db sistema_academico_sprint6 --out ./backups/mongodb
```

Inclua esses arquivos na mesma rotina 3-2-1: mantenha três cópias, em dois tipos de mídia, com uma cópia fora do local principal. O comando acima cria apenas uma cópia local; copie o diretório para outra mídia e para um destino remoto ou fora do local. Aplique retenção aos backups antigos e teste a restauração periodicamente.

Restaure em um banco separado para testar, sem substituir o original:

```bash
mongorestore --nsFrom='sistema_academico_sprint6.*' --nsTo='sistema_academico_sprint6_teste.*' --dir=./backups/mongodb
```

No `mongosh`, confira as contagens no banco restaurado. Devem ser 10 alunos e 5 disciplinas:

```javascript
const teste = db.getSiblingDB("sistema_academico_sprint6_teste");
teste.alunos.countDocuments({ sprint6: true });
teste.disciplinas.countDocuments({ sprint6: true });
```

### Quando usar cada banco

Para este domínio, PostgreSQL é mais adequado quando a prioridade é manter relacionamentos consistentes entre muitas tabelas, aplicar chaves estrangeiras e restrições, executar transações envolvendo várias entidades e produzir relatórios relacionais. MongoDB se destaca quando os dados são acessados como documentos completos, seus campos variam entre registros e a aplicação lê frequentemente o aluno junto com matrículas e notas. A referência à disciplina evita duplicação, mas a aplicação precisa garantir que ela exista e lidar com alterações relacionadas.

## Tecnologias

- PostgreSQL
- MongoDB
- SQL
- Bash
- Git
- GitHub
