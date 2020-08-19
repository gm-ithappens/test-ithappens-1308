set foreign_key_checks = 0;

delete from cliente;
delete from estoque;
delete from filial;
delete from forma_pagamento;
delete from itens_pedido;
delete from pedido;
delete from tipo_pedido;
delete from usuario;
delete from produto;

set foreign_key_checks = 1;

alter table cliente auto_increment = 1;
alter table estoque auto_increment = 1;
alter table filial auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table itens_pedido auto_increment = 1;
alter table pedido auto_increment = 1;
alter table tipo_pedido auto_increment = 1;
alter table usuario auto_increment = 1;
alter table produto auto_increment = 1;

insert into usuario (id, nome) values (1, 'Steve Rogers');

insert into cliente (id, nome) values (1, 'Bruce Wayne');

insert into forma_pagamento (id, descricao) values (1, 'CARTÃO DE CRÉDITO');
insert into forma_pagamento (id, descricao) values (2, 'CARTÃO DE DÉBITO');
insert into forma_pagamento (id, descricao) values (3, 'AVISTA');

insert into produto (id, codigo_barras, descricao, valor) values (1, '123456789123', 'Arroz', 17.9);
insert into produto (id, codigo_barras, descricao, valor) values (2, '457965123546', 'Extrato de Tomate', 5.5);
insert into produto (id, codigo_barras, descricao, valor) values (3, '435971238756', 'Feijão', 7.8);
insert into produto (id, codigo_barras, descricao, valor) values (4, '897464136595', 'Macarrão', 3.2);
insert into produto (id, codigo_barras, descricao, valor) values (5, '697461389463', 'Leite', 6.5);

insert into tipo_pedido (id, descricao) values (1, 'ENTRADA');
insert into tipo_pedido (id, descricao) values (2, 'SAÍDA');

insert into pedido (id, valor_pedido, data_criacao, id_forma_pagamento, id_cliente, id_tipo_pedido, obs_entrega) values (1, 7.8, CURRENT_TIMESTAMP(), 3, 1, 2, 'Minha casa tem um portão branco');

insert into itens_pedido (id_pedido, id_produto, quantidade, status, valor_total) values (1, 3, 1, 'PROCESSADO', 7.8);

insert into filial (id, descricao) values (1, 'Unidade do Cohatrac');
insert into filial (id, descricao) values (2, 'Unidade do Centro');

insert into estoque (id_produto, id_filial, quantidade) values (1, 1, 1);