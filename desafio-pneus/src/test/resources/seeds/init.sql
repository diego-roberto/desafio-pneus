DROP SCHEMA IF EXISTS prologdb_test CASCADE;
CREATE SCHEMA prologdb_test;
SET search_path TO prologdb_test;

DROP TABLE IF EXISTS veiculos;
CREATE SEQUENCE veiculos_seq;
CREATE TABLE veiculos (
    id SERIAL PRIMARY KEY,
    placa VARCHAR(20) UNIQUE NOT NULL,
    marca VARCHAR(50),
    quilometragem DECIMAL(10,2),
    status BOOLEAN DEFAULT TRUE,
    tipo_veiculo VARCHAR(30) NOT NULL
);

DROP TABLE IF EXISTS pneus;
CREATE SEQUENCE pneus_seq;
CREATE TABLE pneus (
    id SERIAL PRIMARY KEY,
    numero_fogo INTEGER UNIQUE NOT NULL,
    marca VARCHAR(50),
    pressao_atual DECIMAL(5,2),
    status BOOLEAN DEFAULT TRUE,
    tipo_pneu VARCHAR(30) NOT NULL
);

DROP TABLE IF EXISTS veiculo_pneu_posicoes;
CREATE TABLE veiculo_pneu_posicoes (
    id SERIAL PRIMARY KEY,
    veiculo_id INTEGER REFERENCES veiculos(id) ON DELETE CASCADE,
    pneu_id INTEGER REFERENCES pneus(id) ON DELETE CASCADE,
    posicao VARCHAR(30)
);