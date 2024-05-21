create table cliente (
    id bigserial primary key,
    cpf varchar(255) not null,
    nome varchar(255) not null,
    email varchar(255) null,
    telefone varchar(255) null,
    criado_em timestamp not null default current_timestamp,
    modificado_em timestamp not null default current_timestamp
);

create unique index cliente_cpf_idx on cliente (cpf);