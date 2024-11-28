create table project(
       id bigint identity not null,
       name varchar(30) not null,
       budget decimal(18,2) not null,
       constraint ck_budget_limit check (budget > 0),
       primary key (id)
)