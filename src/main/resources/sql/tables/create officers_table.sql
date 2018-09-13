use odalybr_register;
create table officers(	id int not null auto_increment, 
						office varchar(30) not null,
						name varchar(30) not null,
                        phone varchar(30),
                        email varchar(30),
						primary key(id));