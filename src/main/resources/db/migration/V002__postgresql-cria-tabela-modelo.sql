create sequence modelo_seq;
create table modelo(
	id bigint not null default nextval('modelo_seq'),
    genero varchar(1) not null,
    nome_modelo varchar(100) not null,
    data_cadastro timestamp with time zone not null,
	ultima_alteracao timestamp with time zone not null,
    
    primary key(id)
);
alter sequence modelo_seq owned by modelo.id;
