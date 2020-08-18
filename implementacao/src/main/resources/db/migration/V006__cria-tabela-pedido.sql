create table pedido
(
    id                 bigint         not null auto_increment,
    valor_pedido       decimal(10, 2) not null,
    data_criacao       datetime       not null,
    id_forma_pagamento bigint,
    id_cliente         bigint         not null,
    id_tipo_pedido     bigint         not null,

    primary key (id),

    constraint fk_pedido_forma_pagamento foreign key (id_forma_pagamento) references forma_pagamento (id),
    constraint fk_pedido_cliente foreign key (id_cliente) references cliente (id),
    constraint fk_pedido_tipo_pedido foreign key (id_tipo_pedido) references tipo_pedido (id)
)