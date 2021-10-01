use posapp;

SHOW TABLES;

create table products(
	`id` int(10) not null auto_increment,
    `name` varchar(30) not null,
    `price` int(30) not null,
    `description` TEXT,
    PRIMARY KEY(id)
) engine=InnoDB;

drop table products;
desc products;



select * from products;

INSERT INTO products(name, price, description) values(?,?,?);

