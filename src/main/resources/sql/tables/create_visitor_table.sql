use odalybr_register;
create table visitor(	id int not null auto_increment, 
						ip varchar(30) not null,
						count int not null,
						access_date date not null,
						primary key(id));