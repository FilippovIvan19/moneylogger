create table categories
(
    id        integer      not null
        constraint categories_pk
            primary key,
    name      varchar(500) not null,
    parent_id integer
        constraint categories_categories_id_fk
            references categories
            on delete set default
);

alter table categories
    owner to money_logger_user;

create sequence categories_sequence start 1000000;


create table records
(
    id          integer          not null
        constraint records_pk
            primary key,
    category_id integer
        constraint records_categories_id_fk
            references categories
            on delete set null,
    date        date             not null,
    spending    boolean          not null,
    amount      double precision not null,
    currency    varchar(10)      not null,
    cash_back   double precision not null
);

alter table records
    owner to money_logger_user;

create sequence records_sequence start 2000000;


create table users
(
    id        integer      not null
        constraint users_pk
            primary key,
    login     varchar(500) not null unique,
    password  varchar(500) not null,
    email     varchar(500) not null
);

alter table users
    owner to money_logger_user;

create sequence users_sequence start 3000000;


create sequence hibernate_sequence start 10000000;
