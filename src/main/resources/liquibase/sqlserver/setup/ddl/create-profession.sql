create table profession (
    id bigint identity not null,
    name varchar(30) not null unique,
    primary key (id)
)