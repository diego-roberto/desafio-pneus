-- V2__insert_initial_data.sql

-- Inserção de veículos
INSERT INTO veiculos (placa, marca, quilometragem, status, tipo_veiculo)
VALUES ('ABC1234', 'Ford', 12000.5, TRUE, 'CARRO'),
       ('XYZ5678', 'Chevrolet', 5000.0, TRUE, 'CARRO'),
       ('JKL9101', 'Toyota', 3000.75, TRUE, 'CARRO'),
       ('DEF4567', 'Mercedes', 15000.0, TRUE, 'TRUCK'); -- Veículo com mais eixos

-- Inserção de pneus para o carro com placa 'ABC1234' (Ford)
INSERT INTO pneus (numero_fogo, marca, pressao_atual, status, tipo_pneu)
VALUES (111, 'Michelin', 32.5, TRUE, 'ALTA_SEVERIDADE'),
       (112, 'Michelin', 32.5, TRUE, 'ALTA_SEVERIDADE'),
       (113, 'Michelin', 32.5, TRUE, 'ALTA_SEVERIDADE'),
       (114, 'Michelin', 32.5, TRUE, 'ALTA_SEVERIDADE');

-- Inserção de pneus para o carro com placa 'XYZ5678' (Chevrolet)
INSERT INTO pneus (numero_fogo, marca, pressao_atual, status, tipo_pneu)
VALUES (211, 'Pirelli', 30.0, TRUE, 'MEDIA_SEVERIDADE'),
       (212, 'Pirelli', 30.0, TRUE, 'MEDIA_SEVERIDADE'),
       (213, 'Pirelli', 30.0, TRUE, 'MEDIA_SEVERIDADE'),
       (214, 'Pirelli', 30.0, TRUE, 'MEDIA_SEVERIDADE');

-- Inserção de pneus para o carro com placa 'JKL9101' (Toyota)
INSERT INTO pneus (numero_fogo, marca, pressao_atual, status, tipo_pneu)
VALUES (311, 'Goodyear', 35.0, TRUE, 'OFF_ROAD'),
       (312, 'Goodyear', 35.0, TRUE, 'OFF_ROAD'),
       (313, 'Goodyear', 35.0, TRUE, 'OFF_ROAD'),
       (314, 'Goodyear', 35.0, TRUE, 'OFF_ROAD');

-- Inserção de pneus para o veículo com placa 'DEF4567' (Mercedes) - TRUCK
INSERT INTO pneus (numero_fogo, marca, pressao_atual, status, tipo_pneu)
VALUES (411, 'Bridgestone', 33.5, TRUE, 'MISTO'),
       (412, 'Bridgestone', 33.5, TRUE, 'MISTO'),
       (413, 'Bridgestone', 33.5, TRUE, 'MISTO'),
       (414, 'Bridgestone', 33.5, TRUE, 'MISTO'),
       (415, 'Bridgestone', 33.5, TRUE, 'MISTO'),
       (416, 'Bridgestone', 33.5, TRUE, 'MISTO');

-- Inserção de posições dos pneus para o carro com placa 'ABC1234' (Ford)
INSERT INTO veiculo_pneu_posicoes (veiculo_id, pneu_id, posicao)
VALUES ((SELECT id FROM veiculos WHERE placa = 'ABC1234'), (SELECT id FROM pneus WHERE numero_fogo = 111), 'Dianteiro Esquerdo'),
       ((SELECT id FROM veiculos WHERE placa = 'ABC1234'), (SELECT id FROM pneus WHERE numero_fogo = 112), 'Dianteiro Direito'),
       ((SELECT id FROM veiculos WHERE placa = 'ABC1234'), (SELECT id FROM pneus WHERE numero_fogo = 113), 'Traseiro Esquerdo'),
       ((SELECT id FROM veiculos WHERE placa = 'ABC1234'), (SELECT id FROM pneus WHERE numero_fogo = 114), 'Traseiro Direito');

-- Inserção de posições dos pneus para o carro com placa 'XYZ5678' (Chevrolet)
INSERT INTO veiculo_pneu_posicoes (veiculo_id, pneu_id, posicao)
VALUES ((SELECT id FROM veiculos WHERE placa = 'XYZ5678'), (SELECT id FROM pneus WHERE numero_fogo = 211), 'Dianteiro Esquerdo'),
       ((SELECT id FROM veiculos WHERE placa = 'XYZ5678'), (SELECT id FROM pneus WHERE numero_fogo = 212), 'Dianteiro Direito'),
       ((SELECT id FROM veiculos WHERE placa = 'XYZ5678'), (SELECT id FROM pneus WHERE numero_fogo = 213), 'Traseiro Esquerdo'),
       ((SELECT id FROM veiculos WHERE placa = 'XYZ5678'), (SELECT id FROM pneus WHERE numero_fogo = 214), 'Traseiro Direito');

-- Inserção de posições dos pneus para o carro com placa 'JKL9101' (Toyota)
INSERT INTO veiculo_pneu_posicoes (veiculo_id, pneu_id, posicao)
VALUES ((SELECT id FROM veiculos WHERE placa = 'JKL9101'), (SELECT id FROM pneus WHERE numero_fogo = 311), 'Dianteiro Esquerdo'),
       ((SELECT id FROM veiculos WHERE placa = 'JKL9101'), (SELECT id FROM pneus WHERE numero_fogo = 312), 'Dianteiro Direito'),
       ((SELECT id FROM veiculos WHERE placa = 'JKL9101'), (SELECT id FROM pneus WHERE numero_fogo = 313), 'Traseiro Esquerdo'),
       ((SELECT id FROM veiculos WHERE placa = 'JKL9101'), (SELECT id FROM pneus WHERE numero_fogo = 314), 'Traseiro Direito');

-- Inserção de posições dos pneus para o veículo com placa 'DEF4567' (Mercedes) - TRUCK
INSERT INTO veiculo_pneu_posicoes (veiculo_id, pneu_id, posicao)
VALUES ((SELECT id FROM veiculos WHERE placa = 'DEF4567'), (SELECT id FROM pneus WHERE numero_fogo = 411), 'Dianteiro Esquerdo'),
       ((SELECT id FROM veiculos WHERE placa = 'DEF4567'), (SELECT id FROM pneus WHERE numero_fogo = 412), 'Dianteiro Direito'),
       ((SELECT id FROM veiculos WHERE placa = 'DEF4567'), (SELECT id FROM pneus WHERE numero_fogo = 413), 'Eixo Traseiro Esquerdo Interno'),
       ((SELECT id FROM veiculos WHERE placa = 'DEF4567'), (SELECT id FROM pneus WHERE numero_fogo = 414), 'Eixo Traseiro Esquerdo Externo'),
       ((SELECT id FROM veiculos WHERE placa = 'DEF4567'), (SELECT id FROM pneus WHERE numero_fogo = 415), 'Eixo Traseiro Direito Interno'),
       ((SELECT id FROM veiculos WHERE placa = 'DEF4567'), (SELECT id FROM pneus WHERE numero_fogo = 416), 'Eixo Traseiro Direito Externo');
