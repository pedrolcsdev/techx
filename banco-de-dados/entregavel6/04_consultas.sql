SELECT
    id,
    nome,
    email,
    data_nascimento
FROM aluno;


SELECT
    id,
    nome,
    email
FROM professor;


SELECT
    id,
    nome,
    carga_horaria
FROM disciplina
WHERE nome = 'Banco de Dados';


SELECT
    a.nome AS aluno,
    d.nome AS disciplina,
    m.data_matricula
FROM matricula m
INNER JOIN aluno a
    ON m.aluno_id = a.id
INNER JOIN disciplina d
    ON m.disciplina_id = d.id;


SELECT
    d.nome AS disciplina,
    p.nome AS professor
FROM disciplina d
INNER JOIN professor p
    ON d.professor_id = p.id;


SELECT
    d.nome AS disciplina,
    COUNT(m.aluno_id) AS quantidade_alunos
FROM disciplina d
LEFT JOIN matricula m
    ON d.id = m.disciplina_id
GROUP BY d.id, d.nome;


SELECT
    a.nome AS aluno,
    AVG(n.valor) AS media
FROM aluno a
INNER JOIN nota n
    ON a.id = n.aluno_id
GROUP BY a.id, a.nome;


SELECT
    a.nome AS aluno,
    AVG(n.valor) AS media
FROM aluno a
INNER JOIN nota n
    ON a.id = n.aluno_id
GROUP BY a.id, a.nome
HAVING AVG(n.valor) >= 7;


SELECT nome
FROM aluno
WHERE id IN (
    SELECT aluno_id
    FROM nota
    WHERE valor > (
        SELECT AVG(valor)
        FROM nota
    )
);


SELECT
    a.nome AS aluno,
    n.valor AS nota
FROM nota n
INNER JOIN aluno a
    ON n.aluno_id = a.id
ORDER BY n.valor DESC;


SELECT
    a.nome AS aluno,
    COUNT(*) AS faltas
FROM frequencia f
INNER JOIN aluno a
    ON f.aluno_id = a.id
WHERE f.presente = FALSE
GROUP BY a.id, a.nome;


SELECT
    c.nome AS curso,
    d.nome AS disciplina
FROM curso_disciplina cd
INNER JOIN curso c
    ON cd.curso_id = c.id
INNER JOIN disciplina d
    ON cd.disciplina_id = d.id
ORDER BY c.nome;