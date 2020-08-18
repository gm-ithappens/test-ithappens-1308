create table produto (
    id bigint not null auto_increment,
    codigo_barras varchar(15) not null,
    descricao varchar (50) not null,
    valor decimal (10, 2) not null,

    primary key (id)
)