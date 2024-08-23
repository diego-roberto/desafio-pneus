DROP SCHEMA IF EXISTS prologdb_test CASCADE;
CREATE SCHEMA prologdb_test;
SET search_path TO prologdb_test;

DROP TABLE IF EXISTS veiculos;
CREATE TABLE veiculos (
    id SERIAL PRIMARY KEY,
    placa VARCHAR(20) NOT NULL,
    marca VARCHAR(50),
    quilometragem DECIMAL(10,2),
    status BOOLEAN DEFAULT TRUE
);

DROP TABLE IF EXISTS pneus;
CREATE TABLE IF NOT EXISTS pneus (
   id SERIAL PRIMARY KEY,
   numero_fogo INTEGER NOT NULL,
   marca VARCHAR(50),
   pressao_atual DECIMAL(5,2),
   status BOOLEAN DEFAULT TRUE,
   veiculo_id INTEGER REFERENCES veiculos(id)
);

DROP TABLE IF EXISTS veiculo_pneu_posicoes;
CREATE TABLE IF NOT EXISTS veiculo_pneu_posicoes (
   id SERIAL PRIMARY KEY,
   veiculo_id INTEGER REFERENCES veiculos(id),
   pneu_id INTEGER REFERENCES pneus(id),
   posicao VARCHAR(20)
);