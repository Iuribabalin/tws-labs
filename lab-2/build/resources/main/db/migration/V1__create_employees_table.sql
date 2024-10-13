
create table Employees
(
    id         serial primary key,
    first_name varchar(100),
    last_name  varchar(100),
    position   varchar(100),
    department varchar(100),
    hire_date  date
);
