CREATE ROLE secretaria_academica;
CREATE ROLE professor_academico;

CREATE USER usuario_secretaria;
CREATE USER usuario_professor;

GRANT secretaria_academica TO usuario_secretaria;
GRANT professor_academico TO usuario_professor;


GRANT USAGE ON SCHEMA public TO secretaria_academica, professor_academico;


REVOKE ALL PRIVILEGES ON TABLE aluno, professor, disciplina, curso, turma, matricula
FROM secretaria_academica;

GRANT SELECT ON TABLE aluno, professor, disciplina, curso, turma
TO secretaria_academica;

GRANT SELECT, INSERT, UPDATE ON TABLE matricula
TO secretaria_academica;


GRANT USAGE, SELECT ON SEQUENCE matricula_id_seq
TO secretaria_academica;

REVOKE DELETE ON TABLE aluno, professor, disciplina, curso, turma, matricula
FROM secretaria_academica;

REVOKE ALL PRIVILEGES ON TABLE aluno, disciplina, turma, avaliacao, nota, frequencia
FROM professor_academico;

GRANT SELECT ON TABLE aluno, disciplina, turma, avaliacao
TO professor_academico;

GRANT SELECT, INSERT, UPDATE ON TABLE nota, frequencia
TO professor_academico;

GRANT USAGE, SELECT ON SEQUENCE nota_id_seq, frequencia_id_seq
TO professor_academico;

REVOKE INSERT, UPDATE, DELETE ON TABLE aluno
FROM professor_academico;

REVOKE DELETE ON TABLE nota, frequencia
FROM professor_academico;
