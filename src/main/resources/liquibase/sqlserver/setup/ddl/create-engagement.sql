create table engagement(
    project_id bigint not null,
    employee_id bigint not null,
    month varchar(10) not null,
    year int not null,
    constraint ck_year_limit check ( year > 2020 ),
    role varchar(30) not null,
    num_of_hours int not null,
    constraint ck_hours_limit check (num_of_hours > 0),
    primary key (project_id, employee_id, month, year),
    constraint project_fk foreign key (project_id) references project(id),
    constraint employee_fk foreign key (employee_id) references employee(id)
)