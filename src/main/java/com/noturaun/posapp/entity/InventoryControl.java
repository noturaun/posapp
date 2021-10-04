package com.noturaun.posapp.entity;

public class InventoryControl {
    Integer productId;
    Integer outletId;
    Integer qty;

    public InventoryControl() {
    }

    public InventoryControl(Integer productId, Integer outletId, Integer qty) {
        this.productId = productId;
        this.outletId = outletId;
        this.qty = qty;
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

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
