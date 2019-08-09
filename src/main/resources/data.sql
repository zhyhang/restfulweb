-- security
insert into security_user(username,password,name,code,email,phone,creator_id,last_modifier_id) values('admin@admin.com','{bcrypt}$2a$10$mSgsATqL8jh49rDerTP.xefpePNvRU2InJ/1Nk7g9W66My3eP16M2','Admin','001','admin@admin.com','13800138000',1,1);

-- test
insert into person(name,birthday,creator_id,last_modifier_id) values('z','1988-12-20',1,1),('l','1990-07-26',1,1),('z','2016-02-21',1,1);
insert into employee(name,birthday,creator_id,last_modifier_id) values('yu','1988-12-20',1,1),('yan','1990-07-26',1,1),('ran','2019-02-21',1,1);
insert into nation(name,code,quantity,found_date,minor,creator_id,last_modifier_id) values('汉族','han',1000000000,'0001-01-01',false,1,1),('满族','man',150000000,'0088-02-28',true,1,1),('壮族','zhuang',80000000,'1002-03-15',true,1,1);
insert into company(name,code,income,found_date,minor,creator_id,last_modifier_id) values('品','pin',1000000000.00,'1998-01-01',false,1,1),('别样','beyond',150000000.00,'2015-02-28',true,1,1),('中国移动','China mobile',500000000000.00,'1995-03-15',true,1,1);
