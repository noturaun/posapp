package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.OrderDetail;

public interface OrderDetailRepository {
    OrderDetail[] getAll();
    void create(OrderDetail orderDetail);
    void update(Integer orderDetailId,OrderDetail  changes);
    void delete(Integer orderDetailId);
}
