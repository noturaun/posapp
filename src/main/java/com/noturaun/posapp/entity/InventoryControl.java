package com.noturaun.posapp.entity;

public class InventoryControl {
    Integer id;
    Integer productId;
    Integer outletId;

    public InventoryControl() {
    }

    public InventoryControl(Integer id, Integer productId, Integer outletId) {
        this.id = id;
        this.productId = productId;
        this.outletId = outletId;
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

    public Integer getOutletId() {
        return outletId;
    }

    public void setOutletId(Integer outletId) {
        this.outletId = outletId;
    }
}
