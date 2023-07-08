INSERT INTO tb_user(nome, email, senha, role, area, pontuacao, descricao, contrato) VALUES ('Etec de Itaquera', 'etecdeitaquera@etec.sp.gov.br', '$2a$12$1TbfyGlg.Eb.qwwFPAg2E.9efzxhIR2ZHaigyLDdLB5pJFLzUtEDO', 'ADMIN', null, null, 'Coordenação da Etec de Itaquera', null);
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

INSERT INTO ticket_suporte(titulo, lab, descricao, status, professor) VALUES('PC sem Funcionar', 'Lab 3', 'Processador do PC parou de funcionar. 2 fileira, PC 3', 'PARA_RESOLVER', 2);
INSERT INTO ticket_suporte(titulo, lab, descricao, status, professor) VALUES('Monitor sem Funcionar', 'Lab 3', 'Monitor parou de funcionar. 2 fileira, PC 3', 'EM_ANDAMENTO', 2);
INSERT INTO ticket_suporte(titulo, lab, descricao, status, professor) VALUES('Datashow pifou', 'Sala 6', 'Datashow foi de submarino, de comes e bebes', 'PARA_RESOLVER', 3);
INSERT INTO ticket_suporte(titulo, lab, descricao, status, professor) VALUES('Tomada explodiu', 'Sala 2', 'Ligaram e explodiu', 'PARA_RESOLVER', 3);

INSERT INTO sala(nome) VALUES('SALA 3');
INSERT INTO sala(nome) VALUES('SALA 6');
INSERT INTO sala(nome) VALUES('SALA 9');

INSERT INTO lab(nome) VALUES('LABORATÓRIO 1');
INSERT INTO lab(nome) VALUES('LABORATÓRIO 2');
INSERT INTO lab(nome) VALUES('LABORATÓRIO 3');