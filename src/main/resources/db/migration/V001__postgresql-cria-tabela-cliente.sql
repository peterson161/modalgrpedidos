create sequence cliente_seq;
create table cliente (
	id bigint not null default nextval('cliente_seq'),
    nome varchar(100) not null,
    tipo_documento varchar(1) not null,
    documento varchar(14) not null,
    endereco varchar(100) not null,
    numero int not null,
    complemento varchar(30),
    bairro varchar(30) not null,
    cidade varchar(30) not null,
    uf varchar(2) not null,
    cep varchar(8) not null,
    telefone varchar(12) not null,
    email varchar(100) not null,
    data_cadastro timestamp with time zone not null,
	ultima_alteracao timestamp with time zone not null,
    
    primary key (id)
    );
	
	alter sequence cliente_seq owned by cliente.id;
