show databases;

create table payment_methods(
	`payment_method_id` tinyint(5) not null auto_increment,
    `name` varchar(30)  not null,
    primary key(`payment_method_id`)
) engine=InnoDB auto_increment=4;

drop table payment_methods;

desc payment_methods;

insert into payment_methods values(1, 'Cash');
insert into payment_methods values(2, 'Credit Card');
insert into payment_methods values(3, 'Bank Transfer');
insert into payment_methods values(4, "Fintech Credit");

select * from payment_methods;