create schema if not exists contacts_schema;

create table if not exists contacts_schema.contact
(
    id BIGINT primary key,
    firstname VARCHAR(255) not null,
    lastname VARCHAR(255) not null,
    email VARCHAR(255) not null,
    phone VARCHAR(20) not null
);