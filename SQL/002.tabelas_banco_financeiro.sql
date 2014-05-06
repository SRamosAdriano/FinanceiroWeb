CREATE TABLE usuario(
  codigo INT(11) NOT NULL AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL,
  login VARCHAR(15) NOT NULL,
  email VARCHAR(100) NOT NULL,
  senha VARCHAR(10) NOT NULL,
  nascimento DATE NOT NULL,
  celular VARCHAR(25),
  idioma VARCHAR(5) NOT NULL,
  ativo TINYINT(1) NOT NULL,
  PRIMARY KEY(codigo),
  UNIQUE KEY login(login)
);

CREATE TABLE usuario_permissao (
  usuario int(11) NOT NULL,
  permissao varchar(50) DEFAULT NULL,
  UNIQUE KEY usuario (usuario,permissao),
  KEY FK_usuario_permissao (usuario),
  CONSTRAINT FK_usuario_permissao FOREIGN KEY (usuario) REFERENCES usuario (codigo)
);

CREATE TABLE conta_bancaria(
  cod_conta INT(11) NOT NULL AUTO_INCREMENT,
  cod_usuario INT(11) NOT NULL,
  des_conta VARCHAR(255) DEFAULT NULL,
  dat_cadastro DATETIME NOT NULL,
  saldo_inicial FLOAT DEFAULT NULL,
  favorita BIT(1) DEFAULT NULL,
  PRIMARY KEY (cod_conta),
  KEY FK_conta_usuario(cod_usuario),
  CONSTRAINT FK_conta_usuario FOREIGN KEY(cod_usuario) REFERENCES usuario(codigo) ON DELETE CASCADE
);
