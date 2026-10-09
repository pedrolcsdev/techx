// Execute com: mongosh entregavel6/09_mongodb.js
const academia = db.getSiblingDB("sistema_academico_sprint6");

for (const nome of ["alunos", "disciplinas"]) {
  if (!academia.getCollectionNames().includes(nome)) academia.createCollection(nome);
}

// Limpa somente os documentos de demonstração desta atividade para permitir reexecução.
academia.alunos.deleteMany({ sprint6: true });
academia.disciplinas.deleteMany({ sprint6: true });

academia.disciplinas.insertMany([
  { codigo: "BD101", nome: "Banco de Dados", area: "Computação", cargaHoraria: 60, sprint6: true },
  { codigo: "PR101", nome: "Programação Java", area: "Computação", cargaHoraria: 80, sprint6: true },
  { codigo: "ES101", nome: "Engenharia de Software", area: "Computação", cargaHoraria: 60, sprint6: true },
  { codigo: "RE101", nome: "Redes de Computadores", area: "Redes", cargaHoraria: 40, sprint6: true },
  { codigo: "IA101", nome: "Inteligência Artificial", area: "Computação", cargaHoraria: 60, sprint6: true }
]);

const disciplina = Object.fromEntries(
  academia.disciplinas.find({ sprint6: true }).toArray().map((item) => [item.codigo, item._id])
);
const dataMatricula = ISODate("2026-09-01T00:00:00Z");
const alunos = [
  { nome: "Ana Souza", email: "ana@email.com", telefone: "85990000001", matriculas: [{ disciplinaId: disciplina.BD101, dataMatricula, notas: [{ avaliacao: "Prova 1", valor: 9.5 }] }] },
  { nome: "Carlos Lima", email: "carlos@email.com", matriculas: [{ disciplinaId: disciplina.PR101, dataMatricula, notas: [{ avaliacao: "Prova 1", valor: 8.0 }] }] },
  { nome: "Mariana Alves", email: "mariana@email.com", matriculas: [{ disciplinaId: disciplina.ES101, dataMatricula, notas: [{ avaliacao: "Prova 1", valor: 7.5 }] }] },
  { nome: "Joao Silva", email: "joao@email.com", acessibilidade: { recurso: "leitor de tela" }, matriculas: [{ disciplinaId: disciplina.RE101, dataMatricula, notas: [{ avaliacao: "Prova 1", valor: 10.0 }] }] },
  { nome: "Lucas Ferreira", email: "lucas@email.com", matriculas: [{ disciplinaId: disciplina.IA101, dataMatricula, notas: [{ avaliacao: "Prova 1", valor: 6.5 }] }] },
  { nome: "Beatriz Santos", email: "beatriz@email.com", telefone: "85990000006", matriculas: [{ disciplinaId: disciplina.BD101, dataMatricula, notas: [{ avaliacao: "Prova 1", valor: 8.5 }] }] },
  { nome: "Gabriel Costa", email: "gabriel@email.com", observacao: "Monitor", matriculas: [{ disciplinaId: disciplina.PR101, dataMatricula, notas: [{ avaliacao: "Prova 1", valor: 9.0 }] }] },
  { nome: "Laura Mendes", email: "laura@email.com", matriculas: [{ disciplinaId: disciplina.ES101, dataMatricula, notas: [{ avaliacao: "Prova 1", valor: 7.0 }] }] },
  { nome: "Rafael Rocha", email: "rafael@email.com", matriculas: [{ disciplinaId: disciplina.RE101, dataMatricula, notas: [{ avaliacao: "Prova 1", valor: 5.5 }] }] },
  { nome: "Camila Martins", email: "camila@email.com", matriculas: [{ disciplinaId: disciplina.IA101, dataMatricula, notas: [{ avaliacao: "Prova 1", valor: 8.0 }] }] }
].map((aluno) => ({ ...aluno, sprint6: true }));
academia.alunos.insertMany(alunos);

// Consultas: nota >= 8, ordenação alfabética e projeção de nome/email.
academia.alunos.find(
  { "matriculas.notas.valor": { $gte: 8 }, sprint6: true },
  { _id: 0, nome: 1, email: 1 }
).sort({ nome: 1 });

// Referência: localizar os alunos matriculados na disciplina pelo ObjectId.
const bancoDeDadosId = academia.disciplinas.findOne({ codigo: "BD101" })._id;
academia.alunos.find(
  { "matriculas.disciplinaId": bancoDeDadosId, sprint6: true },
  { _id: 0, nome: 1, "matriculas.disciplinaId": 1 }
).sort({ nome: 1 });

academia.alunos.createIndex({ email: 1 }, { unique: true, name: "uq_alunos_email" });
academia.alunos.createIndex({ "matriculas.disciplinaId": 1 }, { name: "idx_alunos_disciplina" });
academia.disciplinas.createIndex({ codigo: 1 }, { unique: true, name: "uq_disciplinas_codigo" });

// explain("executionStats") mostra o plano usado e as estatísticas reais da consulta.
academia.alunos.find({ "matriculas.disciplinaId": bancoDeDadosId, sprint6: true })
  .explain("executionStats");
academia.disciplinas.find({ codigo: "BD101" })
  .explain("executionStats");
