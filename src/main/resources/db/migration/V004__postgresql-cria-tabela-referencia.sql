create sequence referencia_seq;
create table referencia(
	id bigint not null default nextval('referencia_seq'),
    cliente_id bigint not null,
    cod_ref varchar(20) not null,
    cod_interno varchar(20) not null,
    grupo_modelo_id bigint not null,
    modelo_id bigint not null,
    descricao varchar(120) not null,
    data_cadastro timestamp with time zone not null,
	ultima_alteracao timestamp with time zone not null,
    
    primary key(id)
);
alter sequence referencia_seq owned by referencia.id;

alter table referencia add constraint fk_referencia_pedido
foreign key (cliente_id) references cliente(id);

alter table referencia add constraint fk_referencia_grupo_modelo
foreign key (grupo_modelo_id) references grupo_modelo(id);

alter table referencia add constraint fk_referencia_modelo
foreign key (modelo_id) references modelo(id);
