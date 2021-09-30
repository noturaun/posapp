use posapp;

create table employees(
	id INT NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(30) NOT NULL,
    lastName VARCHAR(20),
    phone VARCHAR(20) NOT NULL,
    address TEXT NOT NULL,
    PRIMARY KEY (id)
) engine=InnoDB;

drop table employees;

desc employees;

select * from employees;