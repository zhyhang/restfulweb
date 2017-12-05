-- security
insert into security_user(username,password,name,code,email,phone) values('admin@admin.com','{bcrypt}$2a$10$mSgsATqL8jh49rDerTP.xefpePNvRU2InJ/1Nk7g9W66My3eP16M2','Admin','001','admin@admin.com','13800138000');

-- test
insert into person(name,birthday) values('z','1988-12-20'),('l','1990-07-26'),('z','2016-02-21');
insert into employee(name,birthday) values('yu','1988-12-20'),('yan','1990-07-26'),('ran','2019-02-21');
insert into nation(name,code,quantity,found_date,minor) values('汉族','han',1000000000,'0001-01-01',false),('满族','man',150000000,'0088-02-28',true),('壮族','zhuang',80000000,'1002-03-15',true);
insert into company(name,code,income,found_date,minor) values('品友','ipinyou',1000000000.00,'1998-01-01',false),('别样','beyond',150000000.00,'2015-02-28',true),('中国移动','China mobile',500000000000.00,'1995-03-15',true);
