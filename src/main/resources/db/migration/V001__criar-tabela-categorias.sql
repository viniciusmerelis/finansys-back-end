create table categoria(
	id bigint not null auto_increment,
    nome varchar(60) not null,
    descricao varchar(255) not null,
    
    primary key (id)
);