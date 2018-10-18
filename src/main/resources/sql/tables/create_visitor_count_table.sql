use odalybr_register;
create table visitor_count(	id int not null auto_increment, 
							count int not null,
							access_date timestamp not null,
							primary key(id));