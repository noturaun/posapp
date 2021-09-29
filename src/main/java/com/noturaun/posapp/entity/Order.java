package com.noturaun.posapp.entity;

public class Order {
    Integer id;
    Integer employeeId;
    Integer outletId;
    Integer customerId;
    Integer paymentId;
    Integer promotionId;


    public Order() {
    }

    public Order(Integer id, Integer employeeId, Integer outletId, Integer customerId, Integer paymentId, Integer promotionId) {
        this.id = id;
        this.employeeId = employeeId;
        this.outletId = outletId;
        this.customerId = customerId;
        this.paymentId = paymentId;
        this.promotionId = promotionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getOutletId() {
        return outletId;
    }

    public void setOutletId(Integer outletId) {
        this.outletId = outletId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }
}
