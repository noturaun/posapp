use posapp;

create table outlets(
	id int(10) not null auto_increment,
    name varchar(50) not null,
    address text not null,
    phone varchar(20) not null,
    primary key(id)
    )engine=InnoDB;

drop table outlets;

desc outlets;

select * from outlets;