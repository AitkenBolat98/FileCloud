CREATE TABLE  if not exists users
(
    id serial primary key,
    username varchar not null check( length(trim(username)) > 0) unique,
    password varchar
);

CREATE TABLE if not exists authorities (
    id serial primary key,
    authority varchar not null check( length(trim(authority)) > 0) unique
);

create table if not exists user_authorities (
    id serial primary key,
    id_user int not null references users(id),
    id_authority int not null references authorities(id)
);
