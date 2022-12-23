CREATE TABLE banco(
    id bigint not null auto_increment,
    nome varchar(255),
    primary key (id)
);

CREATE TABLE pessoa(
    id bigint not null auto_increment,
    nome varchar(255),
    primary key (id)
);

create table registro_investimento (
    id bigint not null auto_increment,
    data varchar(255),
    investimento varchar(255),
    rendimento varchar(255),
    valor double precision,
    vencimento varchar(255),
    banco bigint,
    investidor bigint,
    primary key (id)
);

alter table registro_investimento
    add constraint FKde53brt4jg1grxr5a2nl9splx
        foreign key (banco) references banco (id);

alter table registro_investimento
    add constraint FK8bo7owyd2p32tnolnqejfv7bg
        foreign key (investidor) references pessoa (id);

insert into pessoa (nome) values ('Fábio Fernandes da Silva');
insert into pessoa (nome) values ('Paloma Machado Dias');
insert into banco (nome) values ('Banco Inter');