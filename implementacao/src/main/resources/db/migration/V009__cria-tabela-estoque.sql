create table estoque
(
    id_produto bigint not null,
    id_filial  bigint not null,
    quantidade int    not null,

    constraint fk_estoque_produto foreign key (id_produto) references produto (id),
    constraint fk_estoque_filial foreign key (id_filial) references filial (id)
)