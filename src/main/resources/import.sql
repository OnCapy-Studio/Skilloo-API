INSERT INTO tb_user(nome, email, senha, role, area, pontuacao, descricao, contrato) VALUES ('Etec de Itaquera', 'etecdeitaquera@etec.sp.gov.br', '$2a$12$1TbfyGlg.Eb.qwwFPAg2E.9efzxhIR2ZHaigyLDdLB5pJFLzUtEDO', 'ADMIN', null, null, 'Coordenação da Etec de Itaquera', null);
INSERT INTO tb_user(nome, email, senha, role, area, pontuacao, descricao, contrato) VALUES ('Djalma Espedito de Lima', 'djalma@etec.sp.gov.br', '$2a$12$vQEq6qZPOcM4QwWjj0moFumMsv4ZQZqnOo.JymPY18NOynjyvyOsS', 'PROF', 'BASE_COMUM', 1500.0, 'Doutorado em Letras', 'CLT');
INSERT INTO tb_user(nome, email, senha, role, area, pontuacao, descricao, contrato) VALUES ('Alexandre Crivelaro da Fonseca', 'alexandre@etec.sp.gov.br', '$2a$12$rLkTHHduHqhw.DubIsSrwepfABp48JAOW8M7w7NuQF.0yLDtsCp0K', 'PROF', 'DS', 1500.0, 'Doutorado em Tecnologia', 'CLT');
INSERT INTO tb_user(nome, email, senha, role, area, pontuacao, descricao, contrato) VALUES ('Wellington Gomes', 'wellington@etec.sp.gov.br', '$2a$12$IR7zNkzc9sjemfasO78kvugqUetMxQNfk3u41ws2YLmNeiZjBdvki', 'PROF', 'DS', 1500.0, 'Doutorado em Tecnologia', 'CLT');


INSERT INTO turma(nome, periodo) VALUES('1º ADM-A', 'MANHA');
INSERT INTO turma(nome, periodo) VALUES('2º ADM-A', 'MANHA');
INSERT INTO turma(nome, periodo) VALUES('3º ADM-A', 'MANHA');
INSERT INTO turma(nome, periodo) VALUES('1º DS-A', 'MANHA');
INSERT INTO turma(nome, periodo) VALUES('1º DS-B', 'MANHA');
INSERT INTO turma(nome, periodo) VALUES('2º DS-A', 'MANHA');
INSERT INTO turma(nome, periodo) VALUES('2º DS-B', 'MANHA');
INSERT INTO turma(nome, periodo) VALUES('3º DS-A', 'MANHA');
INSERT INTO turma(nome, periodo) VALUES('3º DS-B', 'MANHA');

INSERT INTO materia(nome) VALUES('PDTCC');
INSERT INTO materia(nome) VALUES('LPL');
INSERT INTO materia(nome) VALUES('BANCO DE DADOS II');
INSERT INTO materia(nome) VALUES('DESENVOLVIMENTO DE SISTEMAS');
INSERT INTO materia(nome) VALUES('TECNICAS DE PROGRAMAÇÃO E ALGORITMOS');

INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(1, 'MONDAY', '09:50:00', 2, 8);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(1, 'MONDAY', '10:40:00', 2, 8);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(1, 'MONDAY', '11:30:00', 2, 8);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(2, 'TUESDAY', '07:00:00', 2, 1);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(2, 'TUESDAY', '07:50:00', 2, 1);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(2, 'TUESDAY', '08:40:00', 2, 9);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(2, 'TUESDAY', '09:50:00', 2, 3);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(2, 'TUESDAY', '10:40:00', 2, 5);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(2, 'TUESDAY', '11:30:00', 2, 3);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(2, 'WEDNESDAY', '07:00:00', 2, 9);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(2, 'WEDNESDAY', '08:40:00', 2, 4);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(1, 'WEDNESDAY', '09:50:00', 2, 9);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(1, 'WEDNESDAY', '10:40:00', 2, 9);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(1, 'WEDNESDAY', '11:30:00', 2, 9);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(2, 'THURSDAY', '08:40:00', 2, 5);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(2, 'THURSDAY', '09:50:00', 2, 1);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(2, 'THURSDAY', '10:40:00', 2, 4);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(2, 'FRIDAY', '08:40:00', 2, 4);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(2, 'FRIDAY', '09:50:00', 2, 3);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(2, 'FRIDAY', '11:30:00', 2, 5);

INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(3, 'FRIDAY', '10:40:00', 3, 6);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(4, 'FRIDAY', '11:30:00', 3, 7);

INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(5, 'WEDNESDAY', '09:50:00', 4, 5);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(5, 'WEDNESDAY', '10:40:00', 4, 5);
INSERT INTO aula(materia, dia, horario, professor, turma) VALUES(5, 'WEDNESDAY', '11:30:00', 4, 5);


INSERT INTO commit(titulo, descricao, data, turma, materia, autor) VALUES('Primeira Reunião', 'Foi orientado aos alunos de como será feito o TCC, mostrando o calendário com as datas de entregas', '2023-02-08 08:40:00', 9, 1, 2);
INSERT INTO commit(titulo, descricao, data, turma, materia, autor) VALUES('Definição de Grupos e Temas', 'Foi conversado com os alunos para formarem grupos e decidirem os temas', '2023-02-15 08:40:00', 9, 1, 2);

INSERT INTO commit(titulo, descricao, data, turma, materia, autor) VALUES('Romantismo', 'Livro Pag 77', '2023-02-07 08:40:00', 9, 2, 2);
INSERT INTO commit(titulo, descricao, data, turma, materia, autor) VALUES('Barroco', 'Livro Pag 80', '2023-02-08 07:50:00', 9, 2, 2);

INSERT INTO commit(titulo, descricao, data, turma, materia, autor) VALUES('Introdução ao MySQL', 'SELECT, INSERT, DELETE', '2023-02-10 10:40:00', 6, 3, 3);
INSERT INTO commit(titulo, descricao, data, turma, materia, autor) VALUES('Introdução ao MySQL', 'CREATE TABLE, DATABASE', '2023-02-17 10:40:00', 6, 3, 3);

INSERT INTO commit(titulo, descricao, data, turma, materia, autor) VALUES('Introdução ao Java', 'System.out.print(), metodo main()', '2023-02-10 11:30:00', 7, 4, 3);
INSERT INTO commit(titulo, descricao, data, turma, materia, autor) VALUES('Introdução ao Java', 'Estrutura de Decisão', '2023-02-17 11:30:00', 7, 4, 3);



INSERT INTO ticket_suporte(titulo, lab, descricao, status, professor) VALUES('PC sem Funcionar', 'Lab 3', 'Processador do PC parou de funcionar. 2 fileira, PC 3', 'PARA_RESOLVER', 2);
INSERT INTO ticket_suporte(titulo, lab, descricao, status, professor) VALUES('Monitor sem Funcionar', 'Lab 3', 'Monitor parou de funcionar. 2 fileira, PC 3', 'EM_ANDAMENTO', 2);
INSERT INTO ticket_suporte(titulo, lab, descricao, status, professor) VALUES('Datashow pifou', 'Sala 6', 'Datashow foi de submarino, de comes e bebes', 'PARA_RESOLVER', 3);
INSERT INTO ticket_suporte(titulo, lab, descricao, status, professor) VALUES('Tomada explodiu', 'Sala 2', 'Ligaram e explodiu', 'PARA_RESOLVER', 3);


INSERT INTO lab(nome) VALUES('LABORATÓRIO 1');
INSERT INTO lab(nome) VALUES('LABORATÓRIO 2');
INSERT INTO lab(nome) VALUES('LABORATÓRIO 3');
INSERT INTO lab(nome) VALUES('LABORATÓRIO AUDIOVISUAL');
INSERT INTO lab(nome) VALUES('SALA MAKER');

INSERT INTO task(titulo, prazo, anotacao, status, autor) VALUES('Corrigir redações enem', '2023-02-28 13:00:00', 'Tema: Seria Abel Ferreira o maior português que pisou no brasil?','TO_DO', 2);
INSERT INTO task(titulo, prazo, anotacao, status, autor) VALUES('Ler machado de assis', '2023-02-28 12:00:00', 'Estou na Pag 80', 'DOING', 2);
INSERT INTO task(titulo, prazo, anotacao, status, autor) VALUES('Trocar Ryzen do meu pc', '2023-03-18 20:00:00', 'O meu R3 ta horrivel', 'TO_DO', 3);
INSERT INTO task(titulo, prazo, anotacao, status, autor) VALUES('Corrigir atv do Tinkercad', '2023-03-30 13:00:00', 'Atv do Serial Monitor', 'DONE', 3);

INSERT INTO reserva(aula, horario, dia, lab) VALUES(12,'09:50:00', 'WEDNESDAY', 3);
INSERT INTO reserva(aula, horario, dia, lab) VALUES(13,'10:40:00', 'WEDNESDAY', 3);
--INSERT INTO reserva(aula, horario, dia, lab) VALUES(14,'11:30:00', 'WEDNESDAY', 3);
INSERT INTO reserva(aula, horario, dia, lab) VALUES(21,'10:40:00', 'FRIDAY', 2);
INSERT INTO reserva(aula, horario, dia, lab) VALUES(22,'11:30:00', 'FRIDAY', 1);

INSERT INTO reserva(aula, horario, dia, lab) VALUES(23,'09:50:00', 'WEDNESDAY', 2);
INSERT INTO reserva(aula, horario, dia, lab) VALUES(24,'10:40:00', 'WEDNESDAY', 2);
INSERT INTO reserva(aula, horario, dia, lab) VALUES(25,'11:30:00', 'WEDNESDAY', 2);
