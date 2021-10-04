use posapp;

create table payments(
	id int not null auto_increment,
    status varchar(30) default "uncomplete",
    payment_method_id tinyint,
    primary key(id),
    constraint fk_payment_method foreign key(payment_method_id)
    references payment_methods(id)
) engine = InnoDB;

drop table payments;

desc payments;

insert into payments(payment_method_id) values (1);
select * from payments;