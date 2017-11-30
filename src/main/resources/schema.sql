create table person(
id int auto_increment primary key,
name varchar(128),
birthday date,
creation datetime not null default now(),
last_modified datetime not null default now(),
version int not null default 0,
active bool default true,
removed bool default false
) charset=utf8;

create table employee(
id int auto_increment primary key,
name varchar(128),
birthday date,
creation datetime not null default now(),
last_modified datetime not null  default now(),
version int not null default 0,
active bool default true,
removed bool default false
) charset=utf8;

create table nation(
id int auto_increment primary key,
name varchar(128),
code varchar(128),
quantity int,
found_date date,
minor boolean,
creation datetime not null default now(),
last_modified datetime not null default now(),
version int not null default 0,
active bool default true,
removed bool default false
) charset=utf8;

create table company(
id int auto_increment primary key,
name varchar(128),
code varchar(128),
income decimal(20,6),
found_date date,
minor boolean,
creation datetime not null default now(),
last_modified datetime not null default now(),
version int not null default 0,
active bool default true,
removed bool default false
) charset=utf8;

