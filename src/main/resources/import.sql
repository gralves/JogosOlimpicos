-- Modalidades

INSERT INTO modalidade (id,nome) VALUES ('1', 'Futebol');
INSERT INTO modalidade (id,nome) VALUES ('2', 'Natação');
INSERT INTO modalidade (id,nome) VALUES ('3', 'Judô');
INSERT INTO modalidade (id,nome) VALUES ('4', 'Ciclismo');
INSERT INTO modalidade (id,nome) VALUES ('5', 'Vôlei');
INSERT INTO modalidade (id,nome) VALUES ('6', 'Atletismo');
INSERT INTO modalidade (id,nome) VALUES ('7', 'Basquete');
INSERT INTO modalidade (id,nome) VALUES ('8', 'Levantamento de Peso');
INSERT INTO modalidade (id,nome) VALUES ('9', 'Tênis');
INSERT INTO modalidade (id,nome) VALUES ('10', 'Arco e Flecha');


--Local
INSERT INTO local (id,nome) VALUES ('1', 'Ginásio Nacional de Yoyogi');
INSERT INTO local (id,nome) VALUES ('2', 'Ginásio de Tóquio');
INSERT INTO local (id,nome) VALUES ('3', 'Ginásio Olímpico de Hiroshima');



--paises
INSERT INTO competidor (id,nome) VALUES (1,'Brasil');
INSERT INTO competidor (id,nome) VALUES (2,'Holanda');
INSERT INTO competidor (id,nome) VALUES (3,'Italia');
INSERT INTO competidor (id,nome) VALUES (4,'Inglaterra');
INSERT INTO competidor (id,nome) VALUES (5,'Canadá');
INSERT INTO competidor (id,nome) VALUES (6,'França');
INSERT INTO competidor (id,nome) VALUES (7,'Japão');
INSERT INTO competidor (id,nome) VALUES (8,'Suiça');
INSERT INTO competidor (id,nome) VALUES (9,'Argentina');
INSERT INTO competidor (id,nome) VALUES (10,'Espanha');
INSERT INTO competidor (id,nome) VALUES (11,'Alemanha');
INSERT INTO competidor (id,nome) VALUES (12,'México');


--Competições
INSERT INTO competicao (id, idmodalidade, idlocal, pais1_id, pais2_id, etapa, data_inicio, data_termino) VALUES (1, 5, 3, 1, 5, 'OITAVAS', '2020-03-10 12:00:00', '2020-03-10 13:30:00');
INSERT INTO competicao (id, idmodalidade, idlocal, pais1_id, pais2_id, etapa, data_inicio, data_termino) VALUES (2, 3, 2, 3, 6, 'SEMIFINAL', '2020-03-11 10:30:00', '2020-03-11 12:30:00');
