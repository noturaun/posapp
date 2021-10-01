package com.noturaun.posapp.entity;

public class OrderDetail {
    Integer orderId;
    Integer productId;
    Integer qty;

    public OrderDetail() {
    }

    public OrderDetail(Integer orderId, Integer productId, Integer qty) {
        this.orderId = orderId;
        this.productId = productId;
        this.qty = qty;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
