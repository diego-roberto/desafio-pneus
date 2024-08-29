-- V1__create_veiculo_and_pneu_tables.sql

CREATE SCHEMA IF NOT EXISTS prologdb;
SET search_path TO prologdb;

CREATE TABLE veiculos (
  id SERIAL PRIMARY KEY,
  placa VARCHAR(20) UNIQUE NOT NULL,
  marca VARCHAR(50),
  quilometragem DECIMAL(10,2),
  status BOOLEAN DEFAULT TRUE,
  tipo_veiculo VARCHAR(30) NOT NULL
);

CREATE TABLE pneus (
   id SERIAL PRIMARY KEY,
   numero_fogo INTEGER UNIQUE NOT NULL,
   marca VARCHAR(50),
   pressao_atual DECIMAL(5,2),
   status BOOLEAN DEFAULT TRUE,
   tipo_pneu VARCHAR(30) NOT NULL
);

CREATE TABLE veiculo_pneu_posicoes (
   id SERIAL PRIMARY KEY,
   veiculo_id INTEGER REFERENCES veiculos(id) ON DELETE CASCADE,
   pneu_id INTEGER REFERENCES pneus(id) ON DELETE CASCADE,
   posicao VARCHAR(30)
);
