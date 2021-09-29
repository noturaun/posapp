package com.noturaun.posapp.entity;

public class OrderDetail {
    Integer id;
    Integer productId;
    Integer qty;

    public OrderDetail() {
    }

    public OrderDetail(Integer id, Integer productId, Integer qty) {
        this.id = id;
        this.productId = productId;
        this.qty = qty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
