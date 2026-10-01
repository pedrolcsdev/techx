
-- indices para buscas por nome e para colunas usadas em relacionamentos
-- sao os indices mais usados para pesquisas


CREATE INDEX idx_aluno_nome
ON aluno(nome);

CREATE INDEX idx_professor_nome
ON professor(nome);

CREATE INDEX idx_disciplina_nome
ON disciplina(nome);

CREATE INDEX idx_matricula_data
ON matricula(data_matricula);

CREATE INDEX idx_matricula_aluno
ON matricula(aluno_id);

CREATE INDEX idx_matricula_disciplina
ON matricula(disciplina_id);

CREATE INDEX idx_disciplina_professor
ON disciplina(professor_id);

CREATE INDEX idx_turma_disciplina
ON turma(disciplina_id);

CREATE INDEX idx_turma_professor
ON turma(professor_id);

CREATE INDEX idx_avaliacao_turma
ON avaliacao(turma_id);

CREATE INDEX idx_nota_aluno
ON nota(aluno_id);

CREATE INDEX idx_frequencia_aluno
ON frequencia(aluno_id);