-- V2__insert_initial_data.sql

-- Inserção de veículos
INSERT INTO veiculos (placa, marca, quilometragem, status)
VALUES ('ABC1234', 'Ford', 12000.5, TRUE),
       ('XYZ5678', 'Chevrolet', 5000.0, TRUE),
       ('JKL9101', 'Toyota', 3000.75, TRUE),
       ('DEF4567', 'Mercedes', 15000.0, TRUE); -- exemplo de veículo com carroceria maior

-- Inserção de pneus para o carro com placa 'ABC1234' (Ford)
INSERT INTO pneus (numero_fogo, marca, pressao_atual, status, veiculo_id)
VALUES (111, 'Michelin', 32.5, TRUE, 1),
       (112, 'Michelin', 32.5, TRUE, 1),
       (113, 'Michelin', 32.5, TRUE, 1),
       (114, 'Michelin', 32.5, TRUE, 1);

-- Inserção de pneus para o carro com placa 'XYZ5678' (Chevrolet)
INSERT INTO pneus (numero_fogo, marca, pressao_atual, status, veiculo_id)
VALUES (211, 'Pirelli', 30.0, TRUE, 2),
       (212, 'Pirelli', 30.0, TRUE, 2),
       (213, 'Pirelli', 30.0, TRUE, 2),
       (214, 'Pirelli', 30.0, TRUE, 2);

-- Inserção de pneus para o carro com placa 'JKL9101' (Toyota)
INSERT INTO pneus (numero_fogo, marca, pressao_atual, status, veiculo_id)
VALUES (311, 'Goodyear', 35.0, TRUE, 3),
       (312, 'Goodyear', 35.0, TRUE, 3),
       (313, 'Goodyear', 35.0, TRUE, 3),
       (314, 'Goodyear', 35.0, TRUE, 3);

-- Inserção de pneus para o carro com placa 'DEF4567' (Mercedes) - carro com mais eixos
INSERT INTO pneus (numero_fogo, marca, pressao_atual, status, veiculo_id)
VALUES (411, 'Bridgestone', 33.5, TRUE, 4),
       (412, 'Bridgestone', 33.5, TRUE, 4),
       (413, 'Bridgestone', 33.5, TRUE, 4),
       (414, 'Bridgestone', 33.5, TRUE, 4),
       (415, 'Bridgestone', 33.5, TRUE, 4), -- Pneus adicionais para eixo traseiro
       (416, 'Bridgestone', 33.5, TRUE, 4);

-- Inserção de posições dos pneus para o carro com placa 'ABC1234' (Ford)
INSERT INTO veiculo_pneu_posicoes (veiculo_id, pneu_id, posicao)
VALUES (1, 1, 'Dianteiro Esquerdo'),
       (1, 2, 'Dianteiro Direito'),
       (1, 3, 'Traseiro Esquerdo'),
       (1, 4, 'Traseiro Direito');

-- Inserção de posições dos pneus para o carro com placa 'XYZ5678' (Chevrolet)
INSERT INTO veiculo_pneu_posicoes (veiculo_id, pneu_id, posicao)
VALUES (2, 5, 'Dianteiro Esquerdo'),
       (2, 6, 'Dianteiro Direito'),
       (2, 7, 'Traseiro Esquerdo'),
       (2, 8, 'Traseiro Direito');

-- Inserção de posições dos pneus para o carro com placa 'JKL9101' (Toyota)
INSERT INTO veiculo_pneu_posicoes (veiculo_id, pneu_id, posicao)
VALUES (3, 9, 'Dianteiro Esquerdo'),
       (3, 10, 'Dianteiro Direito'),
       (3, 11, 'Traseiro Esquerdo'),
       (3, 12, 'Traseiro Direito');

-- Inserção de posições dos pneus para o carro com placa 'DEF4567' (Mercedes) - carro com mais eixos
INSERT INTO veiculo_pneu_posicoes (veiculo_id, pneu_id, posicao)
VALUES (4, 13, 'Dianteiro Esquerdo'),
       (4, 14, 'Dianteiro Direito'),
       (4, 15, 'Eixo Esquerdo'),
       (4, 16, 'Eixo Direito'),
       (4, 17, 'Traseiro Esquerdo'),
       (4, 18, 'Traseiro Direito');
