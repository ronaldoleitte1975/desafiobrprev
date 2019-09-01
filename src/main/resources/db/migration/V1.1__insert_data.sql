INSERT INTO `brprev`.`categorias` (`categoria`) VALUES ('Bebidas');
INSERT INTO `brprev`.`categorias` (`categoria`) VALUES ('Doces');
INSERT INTO `brprev`.`categorias` (`categoria`) VALUES ('Salgados');

INSERT INTO `brprev`.`produtos`
(`descricao`,
`foto`,
`preco`,
`produto`,
`qtde`,
`idCategoria`)
VALUES
('Coca-Cola em Lata',
'/img/coca-lata.jpg',
5.0,
'COCALATA',
24,
1);

INSERT INTO `brprev`.`produtos`
(`descricao`,
`foto`,
`preco`,
`produto`,
`qtde`,
`idCategoria`)
VALUES
('Guraraná em Lata',
'/img/guarana-lata.jpg',
5.0,
'GUARANALATA',
12,
1);

INSERT INTO `brprev`.`produtos`
(`descricao`,
`foto`,
`preco`,
`produto`,
`qtde`,
`idCategoria`)
VALUES
('Água mineral em garrafa',
'/img/agua-garrafa.jpg',
3.0,
'AGUAGARRAFA',
100,
1);

INSERT INTO `brprev`.`produtos`
(`descricao`,
`foto`,
`preco`,
`produto`,
`qtde`,
`idCategoria`)
VALUES
('Cocada Caseira',
'/img/cocada-caseira.jpg',
5.0,
'COCADA',
24,
2);

INSERT INTO `brprev`.`produtos`
(`descricao`,
`foto`,
`preco`,
`produto`,
`qtde`,
`idCategoria`)
VALUES
('Paçoca doce',
'/img/pacoca-doce.jpg',
1.0,
'PACOCA',
100,
2);

INSERT INTO `brprev`.`produtos`
(`descricao`,
`foto`,
`preco`,
`produto`,
`qtde`,
`idCategoria`)
VALUES
('Batata Chips',
'/img/batata-chips.jpg',
7.5,
'BATATACHIPS',
140,
3);

INSERT INTO `brprev`.`clientes`
(`bairro`,
`cep`,
`cidade`,
`email`,
`estado`,
`nome`,
`rua`,
`senha`)
VALUES
('Tatuapé',
'03086050',
'São Paulo',
'roclei31@yahoo.com.br',
'SP',
'Ronaldo Cerqueira Leite',
'Rua Felipe Camarão, 187',
'abc123');
