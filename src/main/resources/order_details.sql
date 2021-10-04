use posapp;


SHOW TABLES;

create table order_details(
    orderId int not null,
    productId int not null,
    quantity int(10),
    PRIMARY KEY(orderId, productId)
) engine=InnoDB;

drop table order_details;
desc order_detail;



select * from order_detail;

INSERT INTO order_detail(id, orderId, productId) values(?,?,?);
