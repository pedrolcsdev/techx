
EXPLAIN ANALYZE
SELECT
    id,
    nome,
    email
FROM aluno
WHERE nome = 'Ana Souza';


EXPLAIN ANALYZE
SELECT
    id,
    aluno_id,
    disciplina_id,
    data_matricula
FROM matricula
WHERE aluno_id = 1;


EXPLAIN ANALYZE
SELECT
    d.nome,
    p.nome
FROM disciplina d
INNER JOIN professor p
    ON d.professor_id = p.id
WHERE d.professor_id = 1;