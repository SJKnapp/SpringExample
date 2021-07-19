drop table if exists duck CASCADE;
create table duck 
	(id integer primary key AUTO_INCREMENT, 
	age integer not null, 
	full_name varchar(255));
