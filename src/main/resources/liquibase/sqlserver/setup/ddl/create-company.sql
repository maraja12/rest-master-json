
create table company(
    pib int not null,
    constraint ck_pib_limit check ( pib between 100000000 and 999999999),
    name varchar(30) collate Latin1_General_CS_AS not null,
    constraint ck_capitalized check (name LIKE '[A-Ž]%'),
    address nvarchar(50) not null,
    email varchar(30) not null unique,
    invoices nvarchar(max),
    constraint ck_email_formatting check ( email like '%_@_%._%' ),
    primary key (pib)
);