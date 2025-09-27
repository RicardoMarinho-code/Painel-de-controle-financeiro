create database sistema_financeiro;
USE sistema_financeiro;

create table if not exists Usuario (
    id_usuario varchar(36) primary key,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    senha varchar(255) not null
);

create table if not exists Ativo (
    id_ativo varchar(36) primary key,
    descricao varchar(255) not null,
    valor decimal(15, 2) not null,
    id_usuario varchar(36) not null,
    foreign key (id_usuario) references Usuario(id) on delete cascade
);

create table if not exists Passivo (
    id_passivo varchar(36) primary key,
    descricao varchar(255) not null,
    valor decimal(15, 2) not null,
    id_usuario varchar(36) not null,
    foreign key (id_usuario) references Usuario(id) on delete cascade
);

create table if not exists Orcamento (
    id_orcamento varchar(36) primary key,
    mes INT not null,
    ano INT not null,
    id_usuario varchar(36) not null,
    foreign key (id_usuario) references Usuario(id) on delete cascade,
    unique(mes, ano, id_usuario) --faz com que só exista um roçamento por mes
);

create table if not exists CategoriaOrcamento (
    id_categoria varchar(36) primary key,
    nome varchar(100) not null,
    valor_planejado decimal(15, 2) not null,
    valor_realizado decimal(15, 2) DEFAULT 0.00,
    id_orcamento varchar(36) not null,
    foreign key (id_orcamento) references Orcamento(id) on delete cascade
);

create table if not exists Historico (
    id_historico varchar(36) primary key,
    mes INT not null,
    ano INT not null,
    patrimonio_liquido decimal(15, 2) not null,
    id_usuario varchar(36) not null,
    foreign key (id_usuario) references Usuario(id) on delete cascade,
    unique(mes, ano, id_usuario)
);