create table person(
id int auto_increment primary key,
name varchar(128),
birthday date,
creation datetime not null,
last_modified datetime default now()
) charset=utf8;

create table employee(
id int auto_increment primary key,
name varchar(128),
birthday date,
creation datetime not null,
last_modified datetime default now()
) charset=utf8;

create table nation(
id int auto_increment primary key,
name varchar(128),
code varchar(128),
quantity int,
found_date date,
minor boolean,
creation datetime not null,
last_modified datetime default now()
) charset=utf8;

create table company(
id int auto_increment primary key,
name varchar(128),
code varchar(128),
income decimal(20,6),
found_date date,
minor boolean,
creation datetime not null,
last_modified datetime default now()
) charset=utf8;

