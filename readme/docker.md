[readme.md](../README.md)

```aidl
version: '3'

services:
  postgres:
    image: postgres:12.3
    ports:
      - "5432:5432"
    environment:
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=postgres
      - POSTGRES_DB=contact
    volumes:
      - ./init.sql:/docker-entrypoint-initdb.d/init.sql
```

```aidl
create schema if not exists contacts_schema;

create table if not exists contacts_schema.contact
(
    id BIGINT primary key,
    firstname VARCHAR(255) not null,
    lastname VARCHAR(255) not null,
    email VARCHAR(255) not null,
    phone VARCHAR(20) not null
);
```