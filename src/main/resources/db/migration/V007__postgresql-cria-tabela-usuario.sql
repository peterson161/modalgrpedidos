create sequence usuario_seq;
create table usuario(
	id bigint not null default nextval ('usuario_seq'),
	nome varchar(100) not null,
	email varchar(100) not null,
	senha varchar(50) not null,
    tipo_usuario varchar(30) not null,
	data_cadastro timestamp with time zone not null,
	
	primary key(id)
);
alter sequence usuario_seq owned by usuario.id;
