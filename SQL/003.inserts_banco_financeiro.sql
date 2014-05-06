/* Efetue o cadastro do primeiro usuário pela aplicação e depois rode este script */
INSERT INTO usuario_permissao (usuario,permissao) 
VALUES (1,'ROLE_ADMINISTRADOR'), (1,'ROLE_USUARIO_VIP');

