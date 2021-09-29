use posapp;
create table products(
	`id` int(10) not null auto_increment,
    `name` varchar(30) not null,
    `price` int(10) not null,
    `desc` text
) engine=InnoDB;