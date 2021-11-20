create sequence pedido_seq;
create table pedido(
	id bigint not null default nextval('pedido_seq'),
    cliente_id bigint not null,
    numero varchar(15) not null,
    forma_pagamento varchar(20) not null,
    status_pedido int not null,
    data_cadastro timestamp with time zone not null,
	ultima_alteracao timestamp with time zone not null,
    data_finalizacao timestamp with time zone not null,
    data_cancelamento timestamp with time zone not null,
    
    primary key(id)
);
alter sequence pedido_seq owned by pedido.id;

alter table pedido add constraint fk_pedido_cliente
foreign key (cliente_id) references cliente(id);
