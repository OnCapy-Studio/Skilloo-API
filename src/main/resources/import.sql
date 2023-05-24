INSERT INTO tb_user(nome, email, senha, role, area, pontuacao, descricao, contrato) VALUES ('Djalma Espedito de Lima', 'djalma@etec.sp.gov.br', '$2a$12$vQEq6qZPOcM4QwWjj0moFumMsv4ZQZqnOo.JymPY18NOynjyvyOsS', 'PROF', 'BASE_COMUM', 1500.0, 'Doutorado em Letras', 'CLT');
INSERT INTO tb_user(nome, email, senha, role, area, pontuacao, descricao, contrato) VALUES ('Alexandre Crivelaro da Fonseca', 'alexandre@etec.sp.gov.br', '$2a$12$rLkTHHduHqhw.DubIsSrwepfABp48JAOW8M7w7NuQF.0yLDtsCp0K', 'PROF', 'DS', 1500.0, 'Doutorado em Tecnologia', 'CLT');

INSERT INTO turma(nome, periodo) VALUES('1º ADM-A', 'MANHA');
INSERT INTO turma(nome, periodo) VALUES('2º ADM-A', 'MANHA');
INSERT INTO turma(nome, periodo) VALUES('3º ADM-A', 'MANHA');
INSERT INTO turma(nome, periodo) VALUES('1º DS-A', 'MANHA');
INSERT INTO turma(nome, periodo) VALUES('1º DS-B', 'MANHA');
INSERT INTO turma(nome, periodo) VALUES('2º DS-A', 'MANHA');
INSERT INTO turma(nome, periodo) VALUES('2º DS-B', 'MANHA');
INSERT INTO turma(nome, periodo) VALUES('3º DS-A', 'MANHA');
INSERT INTO turma(nome, periodo) VALUES('3º DS-B', 'MANHA');


INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('PDTCC', 'MONDAY', '09:50:00', 1, 8);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('PDTCC', 'MONDAY', '10:40:00', 1, 8);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('PDTCC', 'MONDAY', '11:30:00', 1, 8);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('LPL', 'TUESDAY', '07:00:00', 1, 1);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('LPL', 'TUESDAY', '07:50:00', 1, 1);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('LPL', 'TUESDAY', '08:40:00', 1, 9);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('LPL', 'TUESDAY', '09:50:00', 1, 3);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('LPL', 'TUESDAY', '10:40:00', 1, 5);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('LPL', 'TUESDAY', '11:30:00', 1, 3);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('LPL', 'WEDNESDAY', '07:00:00', 1, 9);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('LPL', 'WEDNESDAY', '08:40:00', 1, 4);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('PDTCC', 'WEDNESDAY', '09:50:00', 1, 9);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('PDTCC', 'WEDNESDAY', '10:40:00', 1, 9);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('PDTCC', 'WEDNESDAY', '11:30:00', 1, 9);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('LPL', 'THURSDAY', '08:40:00', 1, 5);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('LPL', 'THURSDAY', '09:50:00', 1, 1);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('LPL', 'THURSDAY', '10:40:00', 1, 4);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('LPL', 'FRIDAY', '08:40:00', 1, 4);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('LPL', 'FRIDAY', '09:50:00', 1, 3);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES('LPL', 'FRIDAY', '11:30:00', 1, 5);