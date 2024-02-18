create table if not exists cliente
(
    id serial primary key,
    limite int not null,
    saldo  int not null
);

create table if not exists transacao
(
    id  serial   primary key,
    id_cliente   integer not null references cliente,
    valor        int       not null,
    tipo         varchar(1)   not null,
    descricao    varchar(255) not null,
    data   timestamp    not null
);

INSERT INTO cliente (limite, saldo) VALUES (100000, 0);
INSERT INTO cliente (limite, saldo) VALUES (80000, 0);
INSERT INTO cliente (limite, saldo) VALUES (1000000, 0);
INSERT INTO cliente (limite, saldo) VALUES (10000000, 0);
INSERT INTO cliente (limite, saldo) VALUES (500000, 0);