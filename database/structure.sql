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

create sequence categories_sequence start 10000;


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

create sequence records_sequence start 10000;


create sequence hibernate_sequence start 10000;
