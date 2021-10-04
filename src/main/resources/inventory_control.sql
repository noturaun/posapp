use posapp;

create table inventory_control(
	product_id int(20),
    outlet_id int(20),
    quantity int(10) not null default 0,
    constraint PK_inventory_control primary key(product_id, outlet_id)
) engine = InnoDB;

drop table inventory_control;

desc inventory_control;

select * from inventory_control;