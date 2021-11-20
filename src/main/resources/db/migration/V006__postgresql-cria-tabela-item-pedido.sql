create sequence item_pedido_seq;
create table item_pedido(
	id bigint not null default nextval('item_pedido_seq'),
    inicio_entrega date not null,
    fim_entrega date not null,
    pedido_id bigint not null,
    referencia_id bigint not null,
    quantidade int not null,
    valor_unitario decimal(10, 2) not null,
    valor_total decimal(10, 2) not null,
    data_cadastro timestamp with time zone not null,
	ultima_alteracao timestamp with time zone not null,
    
    primary key(id)
);
alter sequence item_pedido_seq owned by item_pedido.id;

alter table item_pedido add constraint fk_item_pedido_pedido
foreign key (pedido_id) references pedido(id);

alter table item_pedido add constraint fk_item_pedido_referencia
foreign key (referencia_id) references referencia(id);
