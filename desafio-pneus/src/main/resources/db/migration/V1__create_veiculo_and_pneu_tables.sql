-- V1__create_veiculo_and_pneu_tables.sql

CREATE SCHEMA IF NOT EXISTS prologdb;
SET search_path TO prologdb;

CREATE TABLE veiculos (
  id SERIAL PRIMARY KEY,
  placa VARCHAR(20) UNIQUE NOT NULL,
  marca VARCHAR(50),
  quilometragem DECIMAL(10,2),
  status BOOLEAN DEFAULT TRUE
);

CREATE TABLE pneus (
   id SERIAL PRIMARY KEY,
   numero_fogo INTEGER NOT NULL,
   marca VARCHAR(50),
   pressao_atual DECIMAL(5,2),
   status BOOLEAN DEFAULT TRUE,
   veiculo_id INTEGER REFERENCES veiculos(id)
);

CREATE TABLE veiculo_pneu_posicoes (
   id SERIAL PRIMARY KEY,
   veiculo_id INTEGER REFERENCES veiculos(id),
   pneu_id INTEGER REFERENCES pneus(id),
   posicao VARCHAR(20)
);
