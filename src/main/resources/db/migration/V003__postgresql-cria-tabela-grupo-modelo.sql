create sequence grupo_modelo_seq;
create table grupo_modelo(
	id bigint not null default nextval('grupo_modelo_seq'),
    nome_grupo varchar(100) not null,
    data_cadastro timestamp with time zone not null,
	ultima_alteracao timestamp with time zone not null,
    
    primary key(id)
);
alter sequence grupo_modelo_seq owned by grupo_modelo.id;
