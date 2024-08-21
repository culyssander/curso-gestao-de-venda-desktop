CREATE DATABASE gestaovenda;

USE gestaovenda;

CREATE TABLE usuario(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
email VARCHAR(150) NOT NULL UNIQUE,
senha VARCHAR(200) NOT NULL,
perfil VARCHAR(10) NOT NULL,
estado TINYINT NOT NULL DEFAULT 0,
dataCriacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
ultimoLogin TIMESTAMP
);

CREATE TABLE permissao(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(75) NOT NULL UNIQUE,
descricao VARCHAR(200)
);

CREATE TABLE permissaousuario(
permisaoId BIGINT NOT NULL,
usuarioId BIGINT NOT NULL,
PRIMARY KEY(permisaoid, usuarioid),
CONSTRAINT fk_permissaousuario_permissaoid foreign key(permisaoId) references permissao(id),
CONSTRAINT fk_permissaousuario_usuarioid foreign key(usuarioId) references usuario(id)
);

CREATE TABLE categoria(
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(75) NOT NULL UNIQUE,
descricao VARCHAR(200)
);

CREATE TABLE produto(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL UNIQUE,
descricao VARCHAR(200),
preco DECIMAL(10, 2) NOT NULL,
datacriacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
categoriaid INT NOT NULL,
usuarioid BIGINT NOT NULL,
CONSTRAINT fk_produto_usuarioid foreign key(usuarioId) references usuario(id),
CONSTRAINT fk_produto_categoria foreign key(categoriaid) references categoria(id)
);

CREATE TABLE estoque (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
produtoid BIGINT NOT NULL UNIQUE,
quantidade INT NOT NULL,
estado TINYINT NOT NULL default 0,
usuarioid BIGINT NOT NULL,
datacriacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT fk_estoque_produto foreign key(produtoid) references produto(id),
CONSTRAINT fk_estoque_usuarioid foreign key(usuarioId) references usuario(id)
);

CREATE TABLE estoquehistorico (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
produto VARCHAR(100) NOT NULL,
quantidade INT NOT NULL,
estado VARCHAR(10) NOT NULL,
usuarioid VARCHAR(100) NOT NULL,
observacao VARCHAR(100) NOT NULL,
datacriacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cliente(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(75),
cfp VARCHAR(15) NOT NULL UNIQUE,
moroda VARCHAR(100)
);


CREATE TABLE venda(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
totalvenda DECIMAL(10, 2) NOT NULL,
valorpago DECIMAL(10, 2) NOT NULL,
desconto DECIMAL(10, 2) NOT NULL,
troco DECIMAL(10, 2) NOT NULL,
clienteid BIGINT NOT NULL,
usuarioid BIGINT NOT NULL,
datacriacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
dataalteracao TIMESTAMP
);

CREATE TABLE vendaitem(
vendaid BIGINT NOT NULL,
produtoid BIGINT NOT NULL,
quantidade INT NOT NULL,
desconto DECIMAL(10, 2) NOT NULL,
preco DECIMAL(10, 2) NOT NULL,
total DECIMAL(10, 2) NOT NULL,
PRIMARY KEY(vendaid)
);