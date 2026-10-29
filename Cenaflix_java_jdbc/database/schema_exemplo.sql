-- MODELO COMPATÍVEL COM O CÓDIGO; NÃO É O SCRIPT ORIGINAL FORNECIDO PELO SENAC.
-- Usar apenas para iniciar um banco de TESTES vazio. O curso forneceu outro script,
-- e os tipos/tamanhos daquela tabela não foram confirmados nesta revisão.
CREATE DATABASE IF NOT EXISTS cenaflix CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cenaflix;

CREATE TABLE IF NOT EXISTS filmes (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    datalancamento DATE NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);
