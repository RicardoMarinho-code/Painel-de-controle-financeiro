CREATE DATABASE seu_banco_de_dados;
USE seu_banco_de_dados;

CREATE TABLE Usuario (
    id_usuario VARCHAR(255) PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE Orcamento (
    id_orcamento VARCHAR(255) PRIMARY KEY,
    mes INT NOT NULL,
    ano INT NOT NULL,
    id_usuario VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE CategoriaOrcamento (
    id_categoria VARCHAR(255) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    valor_planejado DECIMAL(10, 2) NOT NULL,
    valor_realizado DECIMAL(10, 2),
    id_orcamento VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_orcamento) REFERENCES Orcamento(id_orcamento)
);

CREATE TABLE Historico (
    id_historico VARCHAR(255) PRIMARY KEY,
    mes INT NOT NULL,
    ano INT NOT NULL,
    patrimonio_liquido DECIMAL(12, 2) NOT NULL,
    id_usuario VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Passivo (
    id_passivo VARCHAR(255) PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    id_usuario VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Ativo (
    id_ativo VARCHAR(255) PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    id_usuario VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);