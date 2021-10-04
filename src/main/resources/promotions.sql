use posapp;

create table promotions(
	id int(10) not null auto_increment,
    code varchar(20) not null,
    status varchar(30) not null,
    discount double(10, 1) not null,
    primary key(id)
) engine = InnoDB;

drop table promotions;

desc promotions;


select * from promotions;
