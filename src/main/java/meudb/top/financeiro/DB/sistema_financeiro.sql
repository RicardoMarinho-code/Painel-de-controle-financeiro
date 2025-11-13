CREATE DATABASE IF NOT EXISTS sistema_financeiro;

USE sistema_financeiro;

CREATE TABLE Usuario (
    id_usuario VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE Ativo (
    id_ativo VARCHAR(36) PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    id_usuario VARCHAR(36) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);


CREATE TABLE Passivo (
    id_passivo VARCHAR(36) PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    id_usuario VARCHAR(36) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Orcamento (
    id_orcamento VARCHAR(36) PRIMARY KEY,
    mes INT NOT NULL,
    ano INT NOT NULL,
    id_usuario VARCHAR(36) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    UNIQUE KEY uk_usuario_mes_ano (id_usuario, mes, ano) 
);

CREATE TABLE CategoriaOrcamento (
    id_categoria VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    valor_planejado DECIMAL(10, 2) NOT NULL,
    valor_realizado DECIMAL(10, 2) DEFAULT 0.00,
    id_orcamento VARCHAR(36) NOT NULL,
    FOREIGN KEY (id_orcamento) REFERENCES Orcamento(id_orcamento) ON DELETE CASCADE
);

CREATE TABLE Historico (
    id_historico VARCHAR(36) PRIMARY KEY,
    mes INT NOT NULL,
    ano INT NOT NULL,
    patrimonio_liquido DECIMAL(10, 2) NOT NULL,
    id_usuario VARCHAR(36) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

-- Insere o usuário de teste que é usado pela Main.java
INSERT INTO Usuario (id_usuario, nome, email, senha) 
VALUES ('1a2b3c4d-5e6f-7g8h-9i0j-1k2l3m4n5o6p', 'Usuario Teste', 'teste@email.com', '123');