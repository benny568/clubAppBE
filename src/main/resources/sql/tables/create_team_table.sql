use odalybr_register;
create table team(	id int not null auto_increment, 
					name varchar(30) not null,
                    lrcode int default 0,
                    lrFixturesCode int default 0,
                    lrResultsCode int default 0,
                    noticeboard TEXT,
					primary key(id));