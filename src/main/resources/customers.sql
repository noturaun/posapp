use posapp;

create table customers(
	id int(20) not null auto_increment,
    firstName varchar(20),
    lastName varchar(30),
    address text,
    primary key(id)
)engine=InnoDB;


desc customers;

select * from customers;