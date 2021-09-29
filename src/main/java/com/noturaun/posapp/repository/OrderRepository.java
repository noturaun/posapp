package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Order;

public interface OrderRepository {
    Order[] showAll();
    void createOrder(Order order);
    void getOrder(Integer orderId);
    void deleteOrder(Integer orderId);
    void editOrder(Integer orderId, Order changes);
}
