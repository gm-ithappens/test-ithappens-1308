create table itens_pedido
(
    id_pedido   bigint         not null,
    id_produto  bigint         not null,
    quantidade  int            not null,
    status      varchar(10)    not null,
    valor_total decimal(10, 2) not null,

    constraint fk_itens_pedido_pedido foreign key (id_pedido) references pedido (id),
    constraint fk_itens_pedido_produto foreign key (id_produto) references produto (id)
)