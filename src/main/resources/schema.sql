create table user_info(
                      id UUID PRIMARY KEY NOT NULL UNIQUE,
                      name varchar NOT NULL,
                      creation_date TIMESTAMP
);


create type ticket_type as enum('DAY', 'WEEK', 'MONTH', 'YEAR');


create table ticket_info(
                        id UUID PRIMARY KEY NOT NULL UNIQUE,
                        user_id UUID REFERENCES user_info(id) ON DELETE CASCADE,
                        ticket_type ticket_type NOT NULL,
                        creation_date TIMESTAMP
);
