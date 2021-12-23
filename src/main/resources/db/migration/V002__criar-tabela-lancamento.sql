create table lancamento (
	id bigint not null auto_increment,
    nome varchar(60) not null,
    descricao varchar(255),
    tipo_lancamento int not null,
    valor decimal(15,2) not null,
    data_lancamento timestamp not null,
    status_lancamento boolean not null,
    
    primary key (id)
);