package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Order;

public class OrderRepositoryImpl implements OrderRepository{
    @Override
    public Order[] showAll() {
        return new Order[0];
    }

    @Override
    public void createOrder(Order order) {

    }

    @Override
    public void getOrder(Integer orderId) {

    }

    @Override
    public void deleteOrder(Integer orderId) {

    }

    @Override
    public void editOrder(Integer orderId, Order changes) {

    }


}
