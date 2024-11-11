create type ticket_type as enum ('DAY', 'WEEK', 'MONTH', 'YEAR');
create type status as enum ('ACTIVATED', 'MUTED');

create table user_info
(
    id            UUID PRIMARY KEY NOT NULL UNIQUE,
    name          varchar          NOT NULL UNIQUE,
    creation_date TIMESTAMP        NOT NULL,
    status        status           NOT NULL,
    password      varchar          NOT NULL
);

create table ticket_info
(
    id            UUID PRIMARY KEY NOT NULL UNIQUE,
    user_id       UUID REFERENCES user_info (id) ON DELETE CASCADE ON UPDATE CASCADE,
    ticket_type   ticket_type      NOT NULL,
    creation_date TIMESTAMP        NOT NULL
);

INSERT INTO user_info (id, name, creation_date, status, password) VALUES ('e8a5c1fc-d533-4309-ae63-624e98a2bf69', 'olga_kuzmina', '2019-01-21T05:47:26.853Z', 'ACTIVATED', '12345678');
INSERT INTO ticket_info (id, user_id, ticket_type, creation_date) VALUES ('a8a5c1fc-d533-4309-ae63-624e98a2bf69', 'e8a5c1fc-d533-4309-ae63-624e98a2bf69', 'DAY', '2024-11-06T22:01:24.000000');