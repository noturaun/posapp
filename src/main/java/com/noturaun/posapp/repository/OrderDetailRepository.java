package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.OrderDetail;

public interface OrderDetailRepository {
    OrderDetail[] getAll();
    OrderDetail getByOrderId(Integer orderId);
    OrderDetail getByProductId(Integer productId);
    void create(OrderDetail orderDetail);
    void update(Integer orderId, Integer productId, Integer qty);
    Boolean delete(Integer orderId);
}
