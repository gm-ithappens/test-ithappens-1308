alter table pedido add id_filial bigint not null after id_tipo_pedido;

alter table pedido add constraint fk_pedido_filial foreign key (id_filial) references filial (id);