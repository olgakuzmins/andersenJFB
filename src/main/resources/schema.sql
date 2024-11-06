create type ticket_type as enum ('DAY', 'WEEK', 'MONTH', 'YEAR');
create type status as enum ('ACTIVATED', 'MUTED');

create table user_info
(
    id            UUID PRIMARY KEY NOT NULL UNIQUE,
    name          varchar          NOT NULL,
    creation_date TIMESTAMP        NOT NULL,
    status        status           NOT NULL
);

create table ticket_info
(
    id            UUID PRIMARY KEY NOT NULL UNIQUE,
    user_id       UUID REFERENCES user_info (id) ON DELETE CASCADE ON UPDATE CASCADE,
    ticket_type   ticket_type      NOT NULL,
    creation_date TIMESTAMP        NOT NULL
);

