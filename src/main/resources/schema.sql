-- security
create table security_user(
id int auto_increment primary key,
username varchar(128) not null,
password varchar(256) not null,
name varchar(128),
code varchar(64),
email varchar(128),
phone varchar(30) not null,
locked bool default false,
expired bool default false,
pass_expired bool default false,
creation datetime not null default now(),
last_modified datetime not null default now(),
version int not null default 0,
active bool default true,
removed bool default false,
creator_id int not null,
last_modifier_id int not null
) charset=utf8;

create table security_login_log(
id int auto_increment primary key,
userid int,
username varchar(128),
session varchar(128),
action_type varchar(16),
action_time datetime not null,
ipv4 varchar(19),
ipv6 varchar(36),
ua varchar(512),
refer_url varchar(2048),
device_type varchar(16),
device_os varchar(16),
device_osv varchar(16),
device_brand varchar(32),
device_model varchar(16),
app varchar(32),
app_version varchar(16),
cookie varchar(32),
did varchar(64),
did_type varchar(8),
did_encode_type varchar(12),
latitude double,
longtitude double,
creation datetime not null default now(),
last_modified datetime not null default now(),
version int not null default 0,
active bool default true,
removed bool default false
) charset=utf8;


-- test
create table person(
id int auto_increment primary key,
name varchar(128),
birthday date,
creation datetime not null default now(),
last_modified datetime not null default now(),
version int not null default 0,
active bool default true,
removed bool default false,
creator_id int not null,
last_modifier_id int not null
) charset=utf8;

create table employee(
id int auto_increment primary key,
name varchar(128),
birthday date,
creation datetime not null default now(),
last_modified datetime not null  default now(),
version int not null default 0,
active bool default true,
removed bool default false,
creator_id int not null,
last_modifier_id int not null
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
removed bool default false,
creator_id int not null,
last_modifier_id int not null
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
removed bool default false,
creator_id int not null,
last_modifier_id int not null
) charset=utf8;

